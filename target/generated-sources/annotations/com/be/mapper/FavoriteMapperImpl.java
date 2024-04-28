package com.be.mapper;

import com.be.common_api.Favorite;
import com.be.dto.FavoriteDto;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-28T10:02:39+0700",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.22 (Oracle Corporation)"
)
@Component
public class FavoriteMapperImpl implements FavoriteMapper {

    @Override
    public Favorite toEntity(FavoriteDto dto) {
        if ( dto == null ) {
            return null;
        }

        Favorite favorite = new Favorite();

        favorite.setId( dto.getId() );
        favorite.setModifiedDate( dto.getModifiedDate() );
        favorite.setCreatedDate( dto.getCreatedDate() );
        favorite.setDeleted( dto.isDeleted() );
        favorite.setIdDecor( dto.getIdDecor() );
        favorite.setDecor( dto.getDecor() );
        favorite.setIdUser( dto.getIdUser() );
        favorite.setUser( dto.getUser() );

        return favorite;
    }

    @Override
    public FavoriteDto toDto(Favorite entity) {
        if ( entity == null ) {
            return null;
        }

        FavoriteDto favoriteDto = new FavoriteDto();

        favoriteDto.setId( entity.getId() );
        if ( entity.getModifiedDate() != null ) {
            favoriteDto.setModifiedDate( new Timestamp( entity.getModifiedDate().getTime() ) );
        }
        if ( entity.getCreatedDate() != null ) {
            favoriteDto.setCreatedDate( new Timestamp( entity.getCreatedDate().getTime() ) );
        }
        favoriteDto.setDeleted( entity.isDeleted() );
        favoriteDto.setIdDecor( entity.getIdDecor() );
        favoriteDto.setDecor( entity.getDecor() );
        favoriteDto.setIdUser( entity.getIdUser() );
        favoriteDto.setUser( entity.getUser() );

        return favoriteDto;
    }

    @Override
    public List<Favorite> toEntity(List<FavoriteDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Favorite> list = new ArrayList<Favorite>( dtoList.size() );
        for ( FavoriteDto favoriteDto : dtoList ) {
            list.add( toEntity( favoriteDto ) );
        }

        return list;
    }

    @Override
    public List<FavoriteDto> toDto(List<Favorite> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<FavoriteDto> list = new ArrayList<FavoriteDto>( entityList.size() );
        for ( Favorite favorite : entityList ) {
            list.add( toDto( favorite ) );
        }

        return list;
    }

    @Override
    public Set<FavoriteDto> toDto(Set<Favorite> entityList) {
        if ( entityList == null ) {
            return null;
        }

        Set<FavoriteDto> set = new LinkedHashSet<FavoriteDto>( Math.max( (int) ( entityList.size() / .75f ) + 1, 16 ) );
        for ( Favorite favorite : entityList ) {
            set.add( toDto( favorite ) );
        }

        return set;
    }
}
