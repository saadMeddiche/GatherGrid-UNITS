package com.gathergrid.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Setter
@Getter
public class Event {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String location;
    private LocalDate date;
    private LocalTime time;
    @ManyToOne
    private User user;
    private Integer vip_price;
    private Integer regular_price;
    private Integer basic_price;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "event", cascade = CascadeType.ALL)
    private List<Comment> comments;
    @ManyToOne
    private Categorie categorie;

    public Event(String name, String description, String location, LocalDate date, LocalTime time, Categorie categorie,User user, int vip_price, int regular_price, int basic_price, List<Comment> comments) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.date = date;
        this.time = time;
        this.categorie = categorie;
        this.user = user;
        this.vip_price = vip_price;
        this.regular_price = regular_price;
        this.basic_price = basic_price;
        this.comments = comments;
    }

    public Event(String name, String description, String location, LocalDate date, LocalTime time, Categorie categorie,User user, int vip_price, int regular_price, int basic_price) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.date = date;
        this.time = time;
        this.categorie = categorie;
        this.user = user;
        this.vip_price = vip_price;
        this.regular_price = regular_price;
        this.basic_price = basic_price;
    }

    public Event(String name, String description, String location, LocalDate date, LocalTime time, int vip_price, int regular_price, int basic_price) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.date = date;
        this.time = time;
        this.vip_price = vip_price;
        this.regular_price = regular_price;
        this.basic_price = basic_price;
    }

    public Event() {
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", categorie=" + categorie +
                ", vip_price=" + vip_price +
                ", regular_price=" + regular_price +
                ", basic_price=" + basic_price +
                ", comments=" + comments +
                '}';
    }
}
