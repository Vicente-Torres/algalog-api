package com.algaworks.algalog.repository;

import com.algaworks.algalog.model.entity.Occurrence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OccurrenceRepository extends JpaRepository<Occurrence, Long> {

     List<Occurrence> findByDeliveryId(Long deliveryId);
}
