package com.be.service;

import com.be.common_api.Cart;
import com.be.dto.CartDto;
import com.be.handler.Utils;
import com.be.mapper.CartMapper;
import com.be.repository.CartRepository;
import com.llq.springfilter.boot.Filter;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class CartService {
    private final CartRepository repository;
    private final CartMapper cartMapper;

    public CartService(CartRepository repository, CartMapper cartMapper) {
        this.repository = repository;
        this.cartMapper = cartMapper;
    }

    public CartDto save(CartDto cartDto) {
        Cart entity = cartMapper.toEntity(cartDto);
        return cartMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public CartDto findById(Long id) {
        return cartMapper.toDto(repository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Item Not Found! ID: " + id)
        ));
    }
    public Page<CartDto> findByCondition(@Filter Specification<Cart> spec, Pageable pageable) {
        Page<Cart> entityPage = repository.findAll(spec, pageable);
        List<Cart> entities = entityPage.getContent();
        return new PageImpl<>(cartMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public CartDto update(CartDto cartDto, Long id) {
        CartDto data = findById(id);
        Cart entity = cartMapper.toEntity(cartDto);
        BeanUtils.copyProperties(entity, data, Utils.getNullPropertyNames(entity));
        return save(cartMapper.toDto(entity));
    }
}