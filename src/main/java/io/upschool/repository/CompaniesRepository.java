package io.upschool.repository;

import io.upschool.entity.Airport;
import io.upschool.entity.Companies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompaniesRepository extends JpaRepository<Companies,Long> {
    List<Companies> findAllByName(String name);


}
