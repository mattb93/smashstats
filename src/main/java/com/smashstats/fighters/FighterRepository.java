package com.smashstats.fighters;

import org.springframework.data.repository.CrudRepository;

public interface FighterRepository extends CrudRepository<Fighter, Integer> {
    Fighter findByName(String name);
}
