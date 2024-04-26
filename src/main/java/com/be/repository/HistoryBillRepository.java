package com.be.repository;

import com.be.common_api.HistoryBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryBillRepository extends JpaRepository<HistoryBill, Long>, JpaSpecificationExecutor<HistoryBill> {
}