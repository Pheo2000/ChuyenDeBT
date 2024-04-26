package com.be.repository;

import com.be.common_api.HistoryFind;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryFindRepository extends JpaRepository<HistoryFind, Long>, JpaSpecificationExecutor<HistoryFind> {
}