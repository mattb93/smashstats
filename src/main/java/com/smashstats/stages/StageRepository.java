package com.smashstats.stages;

import org.springframework.data.repository.CrudRepository;

public interface StageRepository extends CrudRepository<Stage, Integer> {
    Stage findByName(String name);
}
