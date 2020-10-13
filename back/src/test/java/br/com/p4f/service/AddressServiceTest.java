package br.com.p4f.service;

import br.com.p4f.dto.AddressDTO;
import br.com.p4f.entity.Address;
import br.com.p4f.entity.City;
import br.com.p4f.entity.State;
import br.com.p4f.exceptions.NotFoundException;
import br.com.p4f.repository.AddressRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class AddressServiceTest {

    @InjectMocks
    private AddressService addressService;

    @Mock
    private AddressRepository addressRepository;

    @Test
    public void shouldReturn_whenThereAreResults() {
        final String zipCode = "013";
        PageRequest pageRequest = PageRequest.of(0, 10);
        PageRequest pageResult = PageRequest.of(0, 2);
        List<Address> addresses = generateAddressList();
        Page<Address> addressPage = new PageImpl<>(addresses, pageResult, 2);
        when(addressRepository.findAddressByZipCodeContains(zipCode, pageRequest))
                .thenReturn(addressPage);
        when(addressRepository.countAddressByZipCodeContains(zipCode)).thenReturn(2L);

        Page<AddressDTO> addressReturned = addressService.getAllPaged(zipCode, pageRequest);

        assertEquals(2L, addressReturned.getTotalElements());
        assertEquals(2, addressReturned.getContent().size());
        assertEquals(1L, addressReturned.getContent().get(0).getId().longValue());
    }

    @Test
    public void shouldReturn_whenAddressAlreadyCreated() {
        final long ID = 1L;
        Address address = generateAddressList().get(0);
        Optional<Address> addressOptional = Optional.of(address);
        when(addressRepository.findById(ID)).thenReturn(addressOptional);
        AddressDTO addressDTO = addressService.getById(1L);
        assertNotNull(addressDTO);
        assertEquals(ID, addressDTO.getId().longValue());
    }

    @Test(expected = NotFoundException.class)
    public void shouldNotReturn_whenAddressWasNotCreated() {
        final long ID = 1L;
        Optional<Address> addressOptional = Optional.of(new Address());
        when(addressRepository.findById(ID)).thenReturn(addressOptional);
        addressService.getById(2L);
    }

    @Test(expected = NotFoundException.class)
    public void shouldRaiseError_whenDelete() {
        when(addressRepository.existsById(1L)).thenReturn(true);
        addressService.delete(2L);
    }

    private List<Address> generateAddressList() {
        List<Address> addresses = new ArrayList<>();
        Address address = new Address();
        address.setId(1L);
        address.setAddress2("ap. 409");
        City city = new City();
        city.setId(1L);
        city.setName("São Paulo");
        State state = new State();
        state.setId(1L);
        state.setCountry("BR");
        state.setName("São Paulo");
        city.setState(state);
        address.setCity(city);
        address.setDistrict("Bairro Test");
        address.setNumber(1L);
        address.setZipCode("01306010");

        addresses.add(address);

        address = new Address();
        address.setId(2L);
        address.setAddress2("ap. 409");
        city = new City();
        city.setId(2L);
        city.setName("Salvador");
        state = new State();
        state.setId(2L);
        state.setCountry("BR");
        state.setName("Bahia");
        city.setState(state);
        address.setCity(city);
        address.setDistrict("Bairro Test 2");
        address.setNumber(1L);
        address.setZipCode("01306011");

        addresses.add(address);

        return addresses;
    }


}
