package com.example.demo.service;

import com.example.demo.dto.AddCountryDTO;
import com.example.demo.entity.Country;
import com.example.demo.entity.User;
import com.example.demo.entity.UserType;
import com.example.demo.repository.CountryRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final  CountryRepository countryRepository;

    public UserService(UserRepository userRepository, CountryRepository countryRepository) {
        this.userRepository = userRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void delete(Long id) {
        User user = userRepository.findById(id).get();
        List<Country> countries = user.getCountries();
        // Removing userId from all the Countries
        for(Country c : countries) {
            c.setUser(null);
        }
        // Setting users country list to null
        user.setCountries(null);
        userRepository.deleteById(id);
    }
    public void addCountry(AddCountryDTO dto) {
        User user = userRepository.findById(dto.getIdUser()).get();
        Country country = countryRepository.findById(dto.getIdCountry()).get();
        List<Country> countries = null;
        if (user.getUserType().equals(UserType.COUNTRY)) {
            countries = new ArrayList<>();
            countries.add(country);
        }
        if (user.getUserType().equals(UserType.REGION)) {
            countries = user.getCountries();
            countries.add(country);
        }
        user.setCountries(countries);
        country.setUser(user);
        userRepository.save(user);
        countryRepository.save(country);
    }

    public List<Country> getUserCountries(String email) {
        List<Country> countries = new ArrayList<>();
        Optional<User> user = userRepository.findByEmail(email);
        countries = user.get().getCountries();
        return countries;
    }
}
