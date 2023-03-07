package com.example.demo.controller;

import com.example.demo.dto.AddImageDTO;
import com.example.demo.dto.CountryDTO;
import com.example.demo.entity.Country;
import com.example.demo.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1/country")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }


    @GetMapping
    public ResponseEntity<List<Country>> getAll() {
        return ResponseEntity.ok(countryService.findAll());
    }

    @PostMapping()
    public ResponseEntity<?> save(@RequestBody CountryDTO dto) {
        try {
            Long id = countryService.save(dto);
            return ResponseEntity.ok(id);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

    }
    @DeleteMapping()
    public ResponseEntity<String> delete(@RequestParam Long id) {
        countryService.delete(id);
        return ResponseEntity.ok("Country with id " + id + " was deleted.");
    }

    @PutMapping("/add-image")
    public ResponseEntity<String> addImage(@RequestBody AddImageDTO dto) {
        return ResponseEntity.ok(  countryService.addPicture(dto));
    }

}
