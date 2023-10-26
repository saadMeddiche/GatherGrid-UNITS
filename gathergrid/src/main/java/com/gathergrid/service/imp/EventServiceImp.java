package com.gathergrid.service.imp;

import com.gathergrid.entities.Categorie;
import com.gathergrid.entities.Event;
import com.gathergrid.entities.Response;
import com.gathergrid.entities.User;
import com.gathergrid.repository.CategorieRepository;
import com.gathergrid.repository.EventRespository;
import com.gathergrid.repository.UserRepository;
import com.gathergrid.service.EventService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class EventServiceImp implements EventService {
    private final CategorieRepository categorieRepository;
    private final UserRepository userRepository;
    public EventRespository eventRespository;

    public EventServiceImp(EventRespository eventRespository, CategorieRepository categorieRepository, UserRepository userRepository) {
        this.eventRespository = eventRespository;
        this.categorieRepository = categorieRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Response getEvents() {
        List<Event> events = eventRespository.findAll();
        if (events.isEmpty()) {
            return new Response("No Events Found", 404);
        } else {
            return new Response("Events Found", events, 200);
        }
    }

    @Override
    public Response getEvent(Long id) {
        Event event = eventRespository.findById(id);
        if (event == null) {
            return new Response("No Event Found", 404);
        } else {
            return new Response("Event Found", event, 200);
        }
    }

    @Override
    public Response SearchEvents(int page, String searchTerm) {
        int eventsPerPage = 1;
        int offset = (page - 1) * eventsPerPage;
        List<Event> events;
        Long totalEvents;
        if (searchTerm != null) {
            events = eventRespository.searchEvents(offset, eventsPerPage, searchTerm);
            totalEvents = eventRespository.searchEventsCount(searchTerm);
        } else {
            events = eventRespository.findByPagination(offset, eventsPerPage);
            totalEvents = eventRespository.findTotalEvents();
        }
        Integer totalPages = (int) Math.ceil((double) totalEvents / eventsPerPage);

        if (events.isEmpty()) {
            return new Response("No Events Found", 404);
        } else {
            return new Response("Events Found", List.of(events, totalPages), 200);
        }
    }

    @Override
    public Response searchMyEvents(int page, Long userId, String searchTerm) {
        User user = userRepository.findById(userId);
//        System.out.println(user);

        int eventsPerPage = 10;
        int offset = (page - 1) * eventsPerPage;
        List<Event> events;
        Long totalEvents;
        if (searchTerm != null) {
            events = eventRespository.searchMyEvents(offset, eventsPerPage, searchTerm, user);
            totalEvents = eventRespository.searchMyEventsCount(searchTerm, user);
        } else {
            events = eventRespository.findByPagination(offset, eventsPerPage, user);
            totalEvents = eventRespository.findTotalEvents(user);
        }
        Integer totalPages = (int) Math.ceil((double) totalEvents / eventsPerPage);

        if (events.isEmpty()) {
            return new Response("No Events Found", 404);
        } else {
            return new Response("Events Found", List.of(events, totalPages), 200);
        }
    }

    @Override
    public Response createEvent(String title, String description, String location, String dateTime, int vip_price, int regular_price, int basic_price, Long category, Long user) {
        Categorie eventCategory = categorieRepository.findById(category);
        User loggedUser = userRepository.findById(user);
        if (loggedUser == null) {
            return new Response("User Not Found", 403);
        }
        if (eventCategory == null) {
            return new Response("Category Not Found", 404);
        }

        String[] dateTimeArray = dateTime.split("T");
        LocalDate formattedDate = LocalDate.parse(dateTimeArray[0]);
        LocalTime formattedTime = LocalTime.parse(dateTimeArray[1]);

        eventRespository.save(new Event(title, description, location, formattedDate, formattedTime, eventCategory, loggedUser, vip_price, regular_price, basic_price));

        return new Response("Event Created", 200);
    }

    @Override
    public Response updateEvent(Long id, String title, String description, String location, String dateTime, int vip_price, int regular_price, int basic_price, Long category, Long user) {
        Categorie eventCategory = categorieRepository.findById(category);
        User loggedUser = userRepository.findById(user);
        if (loggedUser == null) {
            return new Response("User Not Found", 404);
        }
        if (eventCategory == null) {
            return new Response("Category Not Found", 404);
        }
        Event event = eventRespository.findById(id);
        if (event == null) {
            return new Response("Event Not Found", 404);
        }
        if (event.getUser() != loggedUser) {
            return new Response("You are not allowed to update this event", 403);
        }

        String[] dateTimeArray = dateTime.split("T");
        LocalDate formattedDate = LocalDate.parse(dateTimeArray[0]);
        LocalTime formattedTime = LocalTime.parse(dateTimeArray[1]);

        event.setId(id);
        event.setName(title);
        event.setDescription(description);
        event.setLocation(location);
        event.setDate(formattedDate);
        event.setTime(formattedTime);
        event.setCategorie(eventCategory);
        event.setVip_price(vip_price);
        event.setRegular_price(regular_price);
        event.setBasic_price(basic_price);

        eventRespository.update(event);

        return new Response("Event Updated", 200);
    }

    @Override
    public Response deleteEvent(Long id, Long userId) {
        User loggedUser = userRepository.findById(userId);
        if (loggedUser == null) {
            return new Response("User Not Found", 403);
        }
        Event event = eventRespository.findById(id);
        if (event == null) {
            return new Response("Event Not Found", 404);
        }
        if (event.getUser() != loggedUser) {
            return new Response("You are not allowed to delete this event", 403);
        }
        eventRespository.delete(event);
        return new Response("Event Deleted", 200);
    }

}
