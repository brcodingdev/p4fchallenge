package br.com.p4f.controller;

import br.com.p4f.dto.CityDTO;
import br.com.p4f.dto.StateDTO;
import br.com.p4f.service.CityService;
import br.com.p4f.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/states")
public class StateController {

    @Autowired
    private StateService stateService;

    @Autowired
    private CityService cityService;

    @GetMapping
    private List<StateDTO> getStates() {
        return stateService.getStates();
    }

    @GetMapping(path = "{id}/cities")
    private List<CityDTO> getCitiesByState(
            @PathVariable(name="id") Long stateId)  {
        return cityService.getCitiesByState(stateId);
    }

}
