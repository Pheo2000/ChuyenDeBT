package com.be.mapper;

import com.be.common_api.Cart;
import com.be.dto.CartDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper extends EntityMapper<CartDto, Cart> {
}