package com.gathergrid.service;

import com.gathergrid.entities.Response;

public interface EventService {

    Response getEvents();

    Response getEvent(Long id);

    Response SearchEvents(int page, String searchTerm);

    Response searchMyEvents(int page, Long userId, String searchTerm);

    Response updateEvent(Long id, String title, String description, String location, String dateTime, int vip_price, int regular_price, int basic_price, Long category, Long user);

    Response createEvent(String title, String description, String location, String dateTime, int vip_price, int regular_price, int basic_price, Long category, Long user);

    Response deleteEvent(Long id, Long userId);
}
