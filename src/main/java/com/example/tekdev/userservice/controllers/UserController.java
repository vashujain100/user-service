package com.example.tekdev.userservice.controllers;

import com.example.tekdev.userservice.models.Movie;
import com.example.tekdev.userservice.models.Show;
import com.example.tekdev.userservice.models.User;
import com.example.tekdev.userservice.services.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/{userEmail}")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public User getUser(@PathVariable String userEmail) {
        return userService.getUser(userEmail);
    }

    @PostMapping("/changeName")
    public void changeUsername(@PathVariable String userEmail, @RequestBody StringUnwrapper usernameUnwrapper) {
        userService.changeUserName(userEmail, usernameUnwrapper.string);
    }

    @RequestMapping("/watchlist")
    public List<Movie> getWatchlist(@PathVariable String userEmail) {
        return userService.showWatchlist(userEmail);
    }

    @PostMapping("/addToWatchList")
    public void addToWatchList(@PathVariable String userEmail, @RequestBody StringUnwrapper movieUnwrapper) {
        String url = "http://localhost:8081/" + movieUnwrapper.string;
        Movie movie = new RestTemplate().getForObject(url, Movie.class);
        userService.addMovieToWatchlist(userEmail, movie);
    }

    @DeleteMapping("/deleteFromWatchList")
    public void deleteFromWatchList(@PathVariable String userEmail, @RequestBody StringUnwrapper movieUnwrapper) {
        String url = "http://localhost:8081/" + movieUnwrapper.string;
        Movie movie = new RestTemplate().getForObject(url, Movie.class);
        userService.deleteMovieFromWatchlist(userEmail, movie);
    }

    @RequestMapping("/bookings")
    public List<Show> getBookings(@PathVariable String userEmail) {
        return userService.showBookings(userEmail);
    }

    @PostMapping("/bookShow")
    public void bookShow(@PathVariable String userEmail, @RequestBody ShowDTO showDTO) {
        String url = "http://localhost:8081/" + showDTO.movieName;
        Movie movie = new RestTemplate().getForObject(url, Movie.class);
        System.out.println(movie);
        userService.addShowToBooking(userEmail, new Show(movie, showDTO.theatreName, showDTO.numberOfSeats, showDTO.showId));
        url = "http://localhost:8082/" + showDTO.theatreName + "/bookShow";
        new RestTemplate().postForObject(url, new ReservationDTO(userEmail, showDTO.showId, showDTO.numberOfSeats), ReservationDTO.class);
    }

    @Data
    static class StringUnwrapper {
        private String string;
    }

    @Data
    static class ShowDTO {
        private String movieName;
        private String showId;
        private int numberOfSeats;
        private String theatreName;
    }

    @Data
    static class ReservationDTO {
        private String userId;
        private String showId;
        private int seats;

        public ReservationDTO(String userId, String showId, int seats) {
            this.userId = userId;
            this.showId = showId;
            this.seats = seats;
        }
    }


}
