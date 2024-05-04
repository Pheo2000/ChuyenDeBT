package com.be.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.be.authenticate.JwtUtil;
import com.be.authenticate.SecurityUtil;
import com.be.authenticate.UserPrincipal;
import com.be.common_api.Cart;
import com.be.dto.CartDto;
import com.be.handler.Utils;
import com.be.mapper.CartMapper;
import com.be.repository.CartRepository;
import com.llq.springfilter.boot.Filter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class CartService {
    private final CartRepository repository;
    private final CartMapper cartMapper;
    private final JwtUtil jwtUtil;
    public CartService(CartRepository repository, CartMapper cartMapper, JwtUtil jwtUtil) {
        this.repository = repository;
        this.cartMapper = cartMapper;
        this.jwtUtil = jwtUtil;
    }

    public CartDto save(CartDto cartDto) {
        Cart entity = cartMapper.toEntity(cartDto);
        Long userId = SecurityUtil.getUserPrincipalId();
        Cart oldCart = repository.findOneByUserIdAndDecorId(userId, cartDto.getIdDecor());
        if (oldCart != null) {
            oldCart.setNumber(oldCart.getNumber() + cartDto.getNumber());
            return cartMapper.toDto(repository.save(oldCart));
        }
        entity.setIdUser(userId);
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

    public List<CartDto> updateCart(List<CartDto> cartDto) {
        UserPrincipal user = jwtUtil.getUser();
        if(user == null) return null;

        List<Cart> lst = repository.findByUser(user.getUserId());
        List<Cart> lstCart = new ArrayList<>();
        cartDto.forEach(t -> {
            Cart detail = lst.stream().filter(t1 -> t1.getIdDecor().equals(t.getIdDecor())).findAny().orElse(null);
            if(detail == null){ // khong ton tai trong db
                lstCart.add(cartMapper.toEntity(t));
            }else{ //da ton tai trong db - update number
                detail.setNumber(t.getNumber()==null ? 0: t.getNumber());
                lstCart.add(detail);
            }
        });

        lst.forEach(t -> {
            //cart trong db khong nam trong ds moi - xoa update deleted
            if(cartDto.stream().filter(t1 -> t1.getIdDecor().equals(t.getIdDecor())).findAny().orElse(null) == null){
                t.setDeleted(true);
                lstCart.add(t);
            }
        });

        return cartMapper.toDto(repository.saveAll(lstCart));
    }

    public List<CartDto> findByUser() {
        UserPrincipal user = jwtUtil.getUser();
        if(user == null) return null;
        return cartMapper.toDto(repository.findByUser(user.getUserId()));
    }
}