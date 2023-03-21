package com.example.demo.controller;

import com.example.demo.dto.AddCountryDTO;
import com.example.demo.entity.Country;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1/user")
@CrossOrigin
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping()
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.findAll());
    }
    @DeleteMapping()
    public ResponseEntity<String> delete(@RequestParam Long id) {
        userService.delete(id);
        return ResponseEntity.ok("User with id " + id + " was deleted.");
    }
    @PostMapping("/add-country")
    public ResponseEntity<String> addCountry(@RequestBody AddCountryDTO dto) {
        userService.addCountry(dto);
        return ResponseEntity.ok("Country with id " + dto.getIdCountry() + " was added to user with id " + dto.getIdUser());
    }
    @GetMapping("/get-countries")
    public ResponseEntity<List<Country>> getUserCountries(@RequestParam String email) {
        return ResponseEntity.ok(userService.getUserCountries(email));
    }

}
