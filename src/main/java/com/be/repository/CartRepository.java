package com.be.repository;

import com.be.common_api.Cart;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>, JpaSpecificationExecutor<Cart> {

    Cart findOneByUserIdAndDecorId(Long userId, Long decorId);

    @Query(value = "select * from cart where deleted = false AND id_user = :userId", nativeQuery = true)
    List<Cart> findByUser(@Param("userId") Long userId);
}