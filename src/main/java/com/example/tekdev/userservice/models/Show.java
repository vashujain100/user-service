//package com.example.tekdev.userservice.models;
//import com.example.tekdev.userservice.models.Movie;
//import lombok.Data;
//
//import java.time.LocalDateTime;
//
//@Data
//public class Show {
//    private Movie movie;
//    private LocalDateTime dateTime;
//    private Theatre theatre;
//
//    public Movie getMovie() {
//        return movie;
//    }
//}
package com.example.tekdev.userservice.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
public class Show {
    private Movie movie;
    private String theatreName;
    private int totalSeatsBooked;
    private  String showId;

    public Show(Movie movie, String theatreName, int totalSeatsBooked, String showId) {
        this.movie = movie;
        this.theatreName = theatreName;
        this.totalSeatsBooked = totalSeatsBooked;
        this.showId = showId;
    }
}
