/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerce.ecommerce.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author ELCOT
 */
@Document(collection = "booking")
public class Book {
    int id;
    String name;
    String email;
    String arrival_date;
    String departure_date;
    String no_of_person;
    String no_of_room;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getArrival_date() {
        return arrival_date;
    }

    public void setArrival_date(String arrival_date) {
        this.arrival_date = arrival_date;
    }

    public String getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(String departure_date) {
        this.departure_date = departure_date;
    }

    public String getNo_of_person() {
        return no_of_person;
    }

    public void setNo_of_person(String no_of_person) {
        this.no_of_person = no_of_person;
    }

    public String getNo_of_room() {
        return no_of_room;
    }

    public void setNo_of_room(String no_of_room) {
        this.no_of_room = no_of_room;
    }
    
}
