package com.be.repository;

import com.be.common_api.Decor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DecorRepository extends JpaRepository<Decor, Long>, JpaSpecificationExecutor<Decor> {
}