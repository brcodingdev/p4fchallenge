package br.com.p4f.repository;

import br.com.p4f.entity.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Page<Address> findAddressByZipCodeContains(String zipCode, Pageable pageable);

    Long countAddressByZipCodeContains(String zipCode);

}
