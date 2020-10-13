package br.com.p4f.service;

import br.com.p4f.dto.AddressDTO;
import br.com.p4f.entity.Address;
import br.com.p4f.exceptions.NotFoundException;
import br.com.p4f.mapper.AddressMapper;
import br.com.p4f.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Cacheable(value = "addresses")
    public synchronized Page<AddressDTO> getAllPaged(String zipCode, Pageable pageable) {
        Page<Address> addressesPage = addressRepository.findAddressByZipCodeContains(zipCode, pageable);
        List<Address> address = addressesPage.getContent();
        List<AddressDTO> addressDTOs = AddressMapper.INSTANCE.toDtoList(address);
        Long totalAddresses = addressRepository.countAddressByZipCodeContains(zipCode);
        return new PageImpl<>(addressDTOs, pageable, totalAddresses);
    }

    @CacheEvict(value = "addresses", allEntries = true)
    public void update(AddressDTO address) {
        if (!addressRepository.existsById(address.getId())) {
            throw new NotFoundException("address not found");
        }
        addressRepository.save(AddressMapper.INSTANCE.toEntity(address));
    }

    @CacheEvict(value = "addresses", allEntries = true)
    public AddressDTO create(AddressDTO address) {
      Address addressEntity
              =  addressRepository.save(AddressMapper.INSTANCE.toEntity(address));
      return AddressMapper.INSTANCE.toDto(addressEntity);
    }

    @Cacheable(value = "addresses", key = "#id")
    public AddressDTO getById(Long id) {
        Optional<Address> address = addressRepository.findById(id);
        if (!address.isPresent()) {
            throw new NotFoundException("address not found");
        }

        return AddressMapper.INSTANCE.toDto(address.get());
    }

    @CacheEvict(value = "addresses", allEntries = true)
    public void delete(Long id) {
        if (!addressRepository.existsById(id)) {
            throw new NotFoundException("address not found");
        }
        addressRepository.deleteById(id);
    }
}
