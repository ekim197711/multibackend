package com.example.multispring.space;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SpaceShipRepository extends CrudRepository<SpaceShipEntity, Integer> {
    @Query("SELECT T FROM SpaceShipEntity T")
    public List<SpaceShipEntity> all();
}
