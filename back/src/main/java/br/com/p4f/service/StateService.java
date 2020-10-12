package br.com.p4f.service;

import br.com.p4f.dto.StateDTO;
import br.com.p4f.entity.State;
import br.com.p4f.mapper.StateMapper;
import br.com.p4f.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateService {

    @Autowired
    private StateRepository stateRepository;

    public List<StateDTO> getStates() {
        List<State> states = stateRepository.findAll();
        return StateMapper.INSTANCE.toDtoList(states);

    }

}
