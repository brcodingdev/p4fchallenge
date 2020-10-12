package br.com.p4f.mapper;

import br.com.p4f.dto.StateDTO;
import br.com.p4f.entity.State;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface StateMapper {

    StateMapper INSTANCE = Mappers.getMapper( StateMapper.class );
    StateDTO toDto(State state);
    List<StateDTO> toDtoList(List<State> states);

}
