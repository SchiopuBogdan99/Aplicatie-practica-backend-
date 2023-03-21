package com.example.demo.repository;

import com.example.demo.entity.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CountryRepository extends CrudRepository<Country,Long> {
    Optional<Country> findByName(String name);
}
