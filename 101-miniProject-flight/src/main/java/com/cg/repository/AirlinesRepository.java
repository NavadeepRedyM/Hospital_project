package com.cg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.model.Airlines;

@Repository
public interface AirlinesRepository extends JpaRepository<Airlines, Integer> {

}
