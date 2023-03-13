package com.example.tekdev.userservice.services;

import com.example.tekdev.userservice.models.Movie;
import com.example.tekdev.userservice.models.Review;
import com.example.tekdev.userservice.models.Show;
import com.example.tekdev.userservice.models.User;
import com.example.tekdev.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void addUser(User newUser) {
        userRepository.save(newUser);
    }

    public void deleteUser(String userName) {
        userRepository.deleteById(userName);
        System.out.println("user deleted");
    }

    public User getUser(String userEmail) {
        return userRepository.findById(userEmail).get();
        //risky code
    }

    public void changeUserName(String userEmail, String newName) {
        if (userRepository.findById(userEmail).isPresent()) {
            User user = userRepository.findById(userEmail).get();
            user.setName(newName);
            userRepository.save(user);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public List<Movie> showWatchlist(String userEmail) {
        if (userRepository.findById(userEmail).isPresent()) {
            User user = userRepository.findById(userEmail).get();
            return user.getWatchlist();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public void addMovieToWatchlist(String userEmail, Movie movie) {
        if (userRepository.findById(userEmail).isPresent()) {
            User user = userRepository.findById(userEmail).get();
            //I think this may not work.
            user.getWatchlist().add(movie);
            userRepository.save(user);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public void deleteMovieFromWatchlist(String userEmail, Movie movie) {
        if (userRepository.findById(userEmail).isPresent()) {
            User user = userRepository.findById(userEmail).get();
            //I think this may not work.
            user.getWatchlist().remove(movie);
            userRepository.save(user);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public List<Show> showBookings(String userEmail) {
        if (userRepository.findById(userEmail).isPresent()) {
            User user = userRepository.findById(userEmail).get();
            return user.getBookings();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public void addShowToBooking(String userEmail, Show show) {
        if (userRepository.findById(userEmail).isPresent()) {
            User user = userRepository.findById(userEmail).get();
            user.getBookings().add(show);
            userRepository.save(user);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public void deleteShowFromBooking(String userEmail, Show show) {
        if (userRepository.findById(userEmail).isPresent()) {
            User user = userRepository.findById(userEmail).get();
            user.getBookings().remove(show);
            userRepository.save(user);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public void giveReview(Review review, Movie movie) {
    }
}
