package com.be.mapper;

import com.be.common_api.Cart;
import com.be.dto.CartDto;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-24T22:35:02+0700",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.22 (Oracle Corporation)"
)
@Component
public class CartMapperImpl implements CartMapper {

    @Override
    public Cart toEntity(CartDto dto) {
        if ( dto == null ) {
            return null;
        }

        Cart cart = new Cart();

        cart.setId( dto.getId() );
        cart.setModifiedDate( dto.getModifiedDate() );
        cart.setCreatedDate( dto.getCreatedDate() );
        cart.setDeleted( dto.isDeleted() );
        cart.setIdDecor( dto.getIdDecor() );
        cart.setDecor( dto.getDecor() );
        cart.setIdUser( dto.getIdUser() );
        cart.setUser( dto.getUser() );
        cart.setNumber( dto.getNumber() );

        return cart;
    }

    @Override
    public CartDto toDto(Cart entity) {
        if ( entity == null ) {
            return null;
        }

        CartDto cartDto = new CartDto();

        cartDto.setId( entity.getId() );
        if ( entity.getModifiedDate() != null ) {
            cartDto.setModifiedDate( new Timestamp( entity.getModifiedDate().getTime() ) );
        }
        if ( entity.getCreatedDate() != null ) {
            cartDto.setCreatedDate( new Timestamp( entity.getCreatedDate().getTime() ) );
        }
        cartDto.setDeleted( entity.isDeleted() );
        cartDto.setIdDecor( entity.getIdDecor() );
        cartDto.setDecor( entity.getDecor() );
        cartDto.setIdUser( entity.getIdUser() );
        cartDto.setUser( entity.getUser() );
        cartDto.setNumber( entity.getNumber() );

        return cartDto;
    }

    @Override
    public List<Cart> toEntity(List<CartDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Cart> list = new ArrayList<Cart>( dtoList.size() );
        for ( CartDto cartDto : dtoList ) {
            list.add( toEntity( cartDto ) );
        }

        return list;
    }

    @Override
    public List<CartDto> toDto(List<Cart> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<CartDto> list = new ArrayList<CartDto>( entityList.size() );
        for ( Cart cart : entityList ) {
            list.add( toDto( cart ) );
        }

        return list;
    }

    @Override
    public Set<CartDto> toDto(Set<Cart> entityList) {
        if ( entityList == null ) {
            return null;
        }

        Set<CartDto> set = new LinkedHashSet<CartDto>( Math.max( (int) ( entityList.size() / .75f ) + 1, 16 ) );
        for ( Cart cart : entityList ) {
            set.add( toDto( cart ) );
        }

        return set;
    }
}
