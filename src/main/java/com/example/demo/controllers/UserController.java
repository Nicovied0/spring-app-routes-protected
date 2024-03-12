package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("personas")
    public List<User>
    getPersonas() {
        return userRepository.findAll();
    }

    @GetMapping("personas/{id}")
    public User getById(@PathVariable Long id) {
        return userRepository.findById(id).get();


    }


    @PostMapping("nueva")
    public String post(@RequestBody User user) {
        userRepository.save(user);
        return "Persona creada";
    }


    @PutMapping("editar/{id}")
    public String update(@PathVariable Long id, @RequestBody User user) {
        User updateUser = userRepository.findById(id).get();
        updateUser.setName(user.getName());
        updateUser.setPhone(user.getPhone());
        updateUser.setEmail(user.getEmail());
        updateUser.setPassword(user.getPassword());
        userRepository.save(updateUser);
        return "Editado correctamente";
    }

    @DeleteMapping("borrar/{id}")
    public String delete(@PathVariable Long id) {
        User deletePersona = userRepository.findById(id).get();
        userRepository.delete(deletePersona);

        return "borrado correctamente";
    }
}
