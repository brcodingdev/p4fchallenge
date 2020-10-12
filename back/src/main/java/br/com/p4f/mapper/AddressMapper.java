package br.com.p4f.mapper;

import br.com.p4f.dto.AddressDTO;
import br.com.p4f.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {CityMapper.class, StateMapper.class})
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper( AddressMapper.class );
    @Mappings({
            @Mapping(source = "city.id", target = "city.id"),
            @Mapping(source = "city.name", target = "city.name"),
            @Mapping(source = "city.state.id", target = "state.id"),
            @Mapping(source = "city.state.name", target = "state.name")
    })
    AddressDTO toDto(Address address);

    @Mappings({
            @Mapping(source = "city.id", target = "city.id"),
            @Mapping(source = "city.name", target = "city.name")
    })
    List<AddressDTO> toDtoList(List<Address> addresses);

    Address toEntity(AddressDTO addressDTO);
}
