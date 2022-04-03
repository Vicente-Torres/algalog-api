package com.algaworks.algalog.repository;

import com.algaworks.algalog.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Optional<Address> findByDistrictAndStreetAndNumberAndComplement(String district, String street, Integer number, String complement);

}
