package com.panfleto.panfleto.repositories;

import com.panfleto.panfleto.entities.Flyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlyerRepository extends JpaRepository<Flyer, Long> {
}
