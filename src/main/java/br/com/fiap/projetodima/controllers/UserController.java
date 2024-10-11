package br.com.fiap.projetodima.controllers;

import br.com.fiap.projetodima.model.User;
import br.com.fiap.projetodima.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<User> create(@RequestBody User user, UriComponentsBuilder uriBuilder) {
        userService.create(user);

        var uri = uriBuilder
                .path("/users/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(user);
    }
}
