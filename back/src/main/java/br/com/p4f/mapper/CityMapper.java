package br.com.p4f.mapper;

import br.com.p4f.dto.CityDTO;
import br.com.p4f.entity.City;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {StateMapper.class})
public interface CityMapper {

    CityMapper INSTANCE = Mappers.getMapper( CityMapper.class );
    CityDTO toDto(City address);
    List<CityDTO> toDtoList(List<City> cities);
}
