package br.com.p4f.repository;

import br.com.p4f.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

    List<City> findCitiesByStateId(Long stateId);
}
