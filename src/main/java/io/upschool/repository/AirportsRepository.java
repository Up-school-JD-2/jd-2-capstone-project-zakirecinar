package io.upschool.repository;

import io.upschool.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportsRepository extends JpaRepository<Airport,Long> {
    // select * from airport p where p.name = ?
    List<Airport> findAllByNameIs(String name);
}
