package com.gathergrid.service.imp;

import com.gathergrid.entities.Comment;
import com.gathergrid.entities.Event;
import com.gathergrid.entities.Response;
import com.gathergrid.entities.User;
import com.gathergrid.factory.DbEntityManagerFactory;
import com.gathergrid.repository.EventRespository;

import java.sql.Date;

public class CommentServiceImp {

    private final EventRespository eventRespository;
    public CommentServiceImp(EventRespository eventRespository) {
        this.eventRespository = eventRespository;
    }
    public Response addComment(String text, Long event_id, User user){
        if(text == null || text.isEmpty()){
            return new Response("Comment Text is Empty",400);
        }else if(event_id == null){
            return new Response("Event Id is Empty",400);
        }else if(!DbEntityManagerFactory.getEntityManager().contains(user)){
            return new Response("User is not Existing ",400);
        }else {
            Event event = eventRespository.findById(event_id);
            if(event== null){
                return new Response("Event is not Existing ",400);
            }
            else{
                Comment comment = new Comment(text,user,event,new Date(System.currentTimeMillis()));
                event.getComments().add(comment);
                try {
                    eventRespository.update(event);
                    return new Response("Comment Added",200);
                }catch (Exception e){
                    return new Response("Error: " + e.getMessage(),400);
                }
            }
        }
    }
}
