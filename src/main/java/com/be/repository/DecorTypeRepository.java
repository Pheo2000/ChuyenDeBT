package com.be.repository;

import com.be.common_api.DecorType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DecorTypeRepository extends JpaRepository<DecorType, Long>, JpaSpecificationExecutor<DecorType> {
}