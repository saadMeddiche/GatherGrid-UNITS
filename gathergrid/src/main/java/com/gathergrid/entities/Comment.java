package com.gathergrid.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
@Entity
@Setter@Getter
public class Comment {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String text;
    @ManyToOne
    private User user;
    @ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
    private Event event;
    private Date date;
    public Comment(String text, User user, Event event, Date date) {
        this.text = text;
        this.user = user;
        this.event = event;
        this.date = date;
    }
    public Comment() {
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", user=" + user +
                ", date=" + date +
                '}';
    }

}
