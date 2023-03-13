package com.example.tekdev.userservice.models;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class User {
    @MongoId
    private String email;
    private String name;
    private List<Movie> watchlist;
    private List<Show> bookings;
    private Map<Movie, Review> reviews;

    public User( String email,String name) {
        this.email = email;
        this.name = name;
        watchlist = new ArrayList<>();
        bookings = new ArrayList<>();
        reviews = new HashMap<>();
    }
    public User(){

    }
    public Map<Movie, Review> getReviews() {
        return reviews;
    }

    public void setReviews(Map<Movie, Review> reviews) {
        this.reviews = reviews;
    }

    public List<Movie> getWatchlist() {
        return watchlist;
    }


    public void addToWatchlist(Movie movie) {
        watchlist.add(movie);
        System.out.println(movie.getMovieName() + " added to watchlist!!");
    }

    public void deleteFromWatchlist(Movie movie) {
        watchlist.remove(movie);
        System.out.println(movie.getMovieName() + " deleted from Watchlist!!");
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

    public List<Show> getBookings() {
        return bookings;
    }

    public void setBookings(List<Show> bookings) {
        this.bookings = bookings;
    }

    public void addShow(Show show) {
        bookings.add(show);
        System.out.println(show.getMovie().getMovieName() + " added successfully to bookings!!");
    }

    public void deleteShow(Show show) {
        bookings.remove(show);
        System.out.println(show.getMovie().getMovieName() + " deleted successfully from bookings!!");
    }
}
