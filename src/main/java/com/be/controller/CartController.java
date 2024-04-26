package com.be.controller;

import com.be.common_api.Cart;
import com.be.dto.CartDto;
import com.be.mapper.CartMapper;
import com.be.service.CartService;
import com.llq.springfilter.boot.Filter;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/api/cart")
@RestController
@Slf4j
@Api("cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/post")
    public ResponseEntity<CartDto> save(@RequestBody @Validated CartDto cartDto) {
        CartDto cartDto1 = cartService.save(cartDto);
        return ResponseEntity.ok(cartDto1);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CartDto> findById(@PathVariable("id") Long id) {
        CartDto cart = cartService.findById(id);
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        CartDto cart = cartService.findById(id);
        cart.setDeleted(true);
        cartService.update(cart, id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/page")
    public ResponseEntity<Page<CartDto>> pageQuery(@Filter Specification<Cart> spec, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 100000) Pageable pageable) {
        Page<CartDto> cartPage = cartService.findByCondition(spec, pageable);
        return ResponseEntity.ok(cartPage);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated CartDto cartDto, @PathVariable("id") Long id) {
        CartDto cart = cartService.findById(id);
        if(cartDto.getIdDecor() != null){
            cart.setIdDecor(cartDto.getIdDecor());
        }
        if(cartDto.getIdUser() != null){
            cart.setIdUser(cartDto.getIdUser());
        }
        if(cartDto.getNumber() != null){
            cart.setNumber(cartDto.getNumber());
        }
        cartService.update(cart, id);
        return ResponseEntity.ok().build();
    }
}