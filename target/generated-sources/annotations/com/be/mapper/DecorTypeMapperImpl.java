package com.be.mapper;

import com.be.common_api.DecorType;
import com.be.dto.DecorTypeDto;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-28T10:02:40+0700",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.22 (Oracle Corporation)"
)
@Component
public class DecorTypeMapperImpl implements DecorTypeMapper {

    @Override
    public DecorType toEntity(DecorTypeDto dto) {
        if ( dto == null ) {
            return null;
        }

        DecorType decorType = new DecorType();

        decorType.setModifiedDate( dto.getModifiedDate() );
        decorType.setCreatedDate( dto.getCreatedDate() );
        decorType.setDeleted( dto.isDeleted() );
        decorType.setId( dto.getId() );
        decorType.setName( dto.getName() );
        decorType.setImage( dto.getImage() );
        decorType.setStatus( dto.getStatus() );

        return decorType;
    }

    @Override
    public DecorTypeDto toDto(DecorType entity) {
        if ( entity == null ) {
            return null;
        }

        DecorTypeDto decorTypeDto = new DecorTypeDto();

        decorTypeDto.setId( entity.getId() );
        if ( entity.getModifiedDate() != null ) {
            decorTypeDto.setModifiedDate( new Timestamp( entity.getModifiedDate().getTime() ) );
        }
        if ( entity.getCreatedDate() != null ) {
            decorTypeDto.setCreatedDate( new Timestamp( entity.getCreatedDate().getTime() ) );
        }
        decorTypeDto.setDeleted( entity.isDeleted() );
        decorTypeDto.setName( entity.getName() );
        decorTypeDto.setImage( entity.getImage() );
        decorTypeDto.setStatus( entity.getStatus() );

        return decorTypeDto;
    }

    @Override
    public List<DecorType> toEntity(List<DecorTypeDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<DecorType> list = new ArrayList<DecorType>( dtoList.size() );
        for ( DecorTypeDto decorTypeDto : dtoList ) {
            list.add( toEntity( decorTypeDto ) );
        }

        return list;
    }

    @Override
    public List<DecorTypeDto> toDto(List<DecorType> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<DecorTypeDto> list = new ArrayList<DecorTypeDto>( entityList.size() );
        for ( DecorType decorType : entityList ) {
            list.add( toDto( decorType ) );
        }

        return list;
    }

    @Override
    public Set<DecorTypeDto> toDto(Set<DecorType> entityList) {
        if ( entityList == null ) {
            return null;
        }

        Set<DecorTypeDto> set = new LinkedHashSet<DecorTypeDto>( Math.max( (int) ( entityList.size() / .75f ) + 1, 16 ) );
        for ( DecorType decorType : entityList ) {
            set.add( toDto( decorType ) );
        }

        return set;
    }
}
