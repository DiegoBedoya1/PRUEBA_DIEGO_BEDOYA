package com.example.demo.Domain.Service;

import com.example.demo.Domain.DTO.User;
import com.example.demo.Domain.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository repo;

    public User create(User user){
        return repo.create(user);
    }
}
