package com.example.tekdev.userservice.repository;

import com.example.tekdev.userservice.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {
}
