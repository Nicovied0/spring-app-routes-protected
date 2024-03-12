package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.requests.AuthRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/auth")
    public String authenticate(@RequestBody AuthRequest request) {
        // Busca el usuario por email en la base de datos
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Verifica si la contraseña coincide
            if (user.getPassword().equals(request.getPassword())) {
                // Genera y devuelve un token JWT
                return generateJWT(user.getEmail());
            }
        }
        // Devuelve un mensaje de error si las credenciales son incorrectas o el usuario no existe
        return "Credenciales incorrectas";
    }

    private String generateJWT(String email) {
        return Jwts.builder()
                .setSubject(email)
                .signWith(SignatureAlgorithm.HS512, "claveSecreta") // Cambia "claveSecreta" a tu propia clave secreta
                .compact();
    }
}