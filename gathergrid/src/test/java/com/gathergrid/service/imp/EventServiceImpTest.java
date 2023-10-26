package com.gathergrid.service.imp;

import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.gathergrid.entities.Categorie;
import com.gathergrid.entities.Event;
import com.gathergrid.entities.Response;
import com.gathergrid.entities.User;
import com.gathergrid.repository.CategorieRepository;
import com.gathergrid.repository.EventRespository;
import com.gathergrid.repository.UserRepository;

import org.mockito.Mockito;

public class EventServiceImpTest {

    EventRespository eventRepository;
    UserRepository userRepository;
    CategorieRepository categorieRepository;
    EventServiceImp eventServiceImp;

    @BeforeEach
    public void setUpRepositoriesAndeventServiceImp() {

        eventRepository = Mockito.mock(EventRespository.class);
        userRepository = Mockito.mock(UserRepository.class);
        categorieRepository = Mockito.mock(CategorieRepository.class);

        eventServiceImp = new EventServiceImp(eventRepository, categorieRepository, userRepository);
    }

    public static Stream<Arguments> events() {

        User user = new User();
        return Stream.of(
                Arguments.of(1L, "Event Title", "Event Description", "Event Location", "2023-10-26T08:00", 50, 30, 20,
                        1L, 1L, new Response("Event Updated", 200), new Categorie(), user, new Event(),
                        user),
                Arguments.of(2L, "Invalid User", "Description", "Location", "2023-10-26T10:00", 50, 30, 20, 1L, 999L,
                        new Response("User Not Found", 404), new Categorie(), null, new Event(), user),
                Arguments.of(3L, "Valid User", "Description", "Location", "2023-10-26T12:00", 50, 30, 20, 999L, 1L,
                        new Response("Category Not Found", 404), null, user, new Event(), user),
                Arguments.of(4L, "Valid User", "Description", "Location", "2023-10-26T14:00", 50, 30, 20, 1L, 1L,
                        new Response("Event Not Found", 404), new Categorie(), user, null, user),
                Arguments.of(5L, "Valid User", "Description", "Location", "2023-10-26T16:00", 50, 30, 20, 1L, 2L,
                        new Response("You are not allowed to update this event", 403), new Categorie(), user,
                        new Event(), new User()));
    }

    @Test
    @ParameterizedTest
    @MethodSource(value = "events")
    public void updateEvent_whenSomeAttributesAreNull_thenShouldNotThrowNullExceptionWhen(Long id, String title,
            String description,
            String location, String dateTime, int vip_price, int regular_price, int basic_price, Long category,
            Long user, Response expectedResponse, Categorie eventCategorie, User loggedUser, Event event,
            User organizateur) {

        Mockito.when(categorieRepository.findById(category)).thenReturn(eventCategorie);

        Mockito.when(userRepository.findById(user)).thenReturn(loggedUser);

        Mockito.when(eventRepository.findById(id)).thenReturn(event);

        Mockito.when(event.getUser()).thenReturn(organizateur);

        Response resulta = eventServiceImp.updateEvent(id, title, description, location, dateTime, vip_price,
                regular_price,
                basic_price, category, user);

        Assertions.assertEquals(expectedResponse.getMessage(), resulta.getMessage());

        Assertions.assertEquals(expectedResponse.getStatus(), resulta.getStatus());

    }

}
