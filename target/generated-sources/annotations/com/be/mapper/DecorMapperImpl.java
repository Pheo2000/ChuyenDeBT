package com.be.mapper;

import com.be.common_api.Decor;
import com.be.dto.DecorDto;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-24T22:35:04+0700",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.22 (Oracle Corporation)"
)
@Component
public class DecorMapperImpl implements DecorMapper {

    @Override
    public Decor toEntity(DecorDto dto) {
        if ( dto == null ) {
            return null;
        }

        Decor decor = new Decor();

        decor.setId( dto.getId() );
        decor.setModifiedDate( dto.getModifiedDate() );
        decor.setCreatedDate( dto.getCreatedDate() );
        decor.setDeleted( dto.isDeleted() );
        decor.setIdDecorType( dto.getIdDecorType() );
        decor.setDecorType( dto.getDecorType() );
        decor.setName( dto.getName() );
        decor.setImage( dto.getImage() );
        decor.setDescriptionDecor( dto.getDescriptionDecor() );
        decor.setPrice( dto.getPrice() );
        decor.setQuantity( dto.getQuantity() );
        decor.setStatus( dto.getStatus() );

        return decor;
    }

    @Override
    public DecorDto toDto(Decor entity) {
        if ( entity == null ) {
            return null;
        }

        DecorDto decorDto = new DecorDto();

        decorDto.setId( entity.getId() );
        if ( entity.getModifiedDate() != null ) {
            decorDto.setModifiedDate( new Timestamp( entity.getModifiedDate().getTime() ) );
        }
        if ( entity.getCreatedDate() != null ) {
            decorDto.setCreatedDate( new Timestamp( entity.getCreatedDate().getTime() ) );
        }
        decorDto.setDeleted( entity.isDeleted() );
        decorDto.setIdDecorType( entity.getIdDecorType() );
        decorDto.setDecorType( entity.getDecorType() );
        decorDto.setName( entity.getName() );
        decorDto.setImage( entity.getImage() );
        decorDto.setDescriptionDecor( entity.getDescriptionDecor() );
        decorDto.setPrice( entity.getPrice() );
        decorDto.setQuantity( entity.getQuantity() );
        decorDto.setStatus( entity.getStatus() );

        return decorDto;
    }

    @Override
    public List<Decor> toEntity(List<DecorDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Decor> list = new ArrayList<Decor>( dtoList.size() );
        for ( DecorDto decorDto : dtoList ) {
            list.add( toEntity( decorDto ) );
        }

        return list;
    }

    @Override
    public List<DecorDto> toDto(List<Decor> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<DecorDto> list = new ArrayList<DecorDto>( entityList.size() );
        for ( Decor decor : entityList ) {
            list.add( toDto( decor ) );
        }

        return list;
    }

    @Override
    public Set<DecorDto> toDto(Set<Decor> entityList) {
        if ( entityList == null ) {
            return null;
        }

        Set<DecorDto> set = new LinkedHashSet<DecorDto>( Math.max( (int) ( entityList.size() / .75f ) + 1, 16 ) );
        for ( Decor decor : entityList ) {
            set.add( toDto( decor ) );
        }

        return set;
    }
}
