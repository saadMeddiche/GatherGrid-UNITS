package com.gathergrid.Tests;

import com.gathergrid.entities.Response;
import com.gathergrid.repository.CategorieRepository;
import com.gathergrid.repository.EventRespository;
import com.gathergrid.repository.UserRepository;
import com.gathergrid.service.EventService;
import com.gathergrid.service.imp.EventServiceImp;

public class TestingEventService {

    public static void main(String[] args) {
        EventRespository eventRespository = new EventRespository();
        UserRepository userRepository = new UserRepository();
        CategorieRepository categorieRepository = new CategorieRepository();

        EventService eventService = new EventServiceImp(eventRespository, categorieRepository, userRepository);
        Response event = null;

        event = eventService.searchMyEvents(1, 1L, "Event");

        System.out.println(event);
    }
}
