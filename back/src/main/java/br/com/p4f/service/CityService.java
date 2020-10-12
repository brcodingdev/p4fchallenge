package br.com.p4f.service;

import br.com.p4f.dto.CityDTO;
import br.com.p4f.entity.City;
import br.com.p4f.exceptions.NotFoundException;
import br.com.p4f.mapper.CityMapper;
import br.com.p4f.repository.CityRepository;
import br.com.p4f.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;

    public List<CityDTO> getCitiesByState(Long stateId) {
        if (!stateRepository.existsById(stateId)) {
            throw new NotFoundException("State not found");
        }

        List<City> cities = cityRepository.findCitiesByStateId(stateId);
        if (cities.isEmpty()) {
            throw new NotFoundException("Cities not found with state id");
        }

        return CityMapper.INSTANCE.toDtoList(cities);
    }
}
