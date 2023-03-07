package com.example.demo.service;

import com.example.demo.dto.AddImageDTO;
import com.example.demo.dto.CountryDTO;
import com.example.demo.entity.Country;
import com.example.demo.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {
    private CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public Long save(CountryDTO dto) throws Exception {
        Optional<Country> existingCountry = countryRepository.findByName(dto.getName());
        if (existingCountry.isPresent()) throw new Exception("A country with this name already exists.");
        else {
            Country country = new Country(null, dto.getCode(), dto.getName(), null, null);
            countryRepository.save(country);
            return country.getId();
        }
    }


    public void delete(Long id) {
        countryRepository.deleteById(id);
    }

    public List<Country> findAll() {
        return (List<Country>) countryRepository.findAll();
    }
    public String addPicture(AddImageDTO dto) {
        Country country = countryRepository.findById(dto.getCountryId()).orElse(null);
        if (country != null) {
            country.setImageId(dto.getImageId());
            countryRepository.save(country);
            return "Image with id " + dto.getImageId() + " was added to country with id " + dto.getCountryId();
        } else return "Country with id " + dto.getCountryId() + " does not exist.";
    }
}
