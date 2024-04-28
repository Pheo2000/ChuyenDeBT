package com.be.mapper;

import com.be.common_api.HistoryFind;
import com.be.dto.HistoryFindDto;
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
public class HistoryFindMapperImpl implements HistoryFindMapper {

    @Override
    public HistoryFind toEntity(HistoryFindDto dto) {
        if ( dto == null ) {
            return null;
        }

        HistoryFind historyFind = new HistoryFind();

        historyFind.setId( dto.getId() );
        historyFind.setModifiedDate( dto.getModifiedDate() );
        historyFind.setCreatedDate( dto.getCreatedDate() );
        historyFind.setDeleted( dto.isDeleted() );
        historyFind.setIdUser( dto.getIdUser() );
        historyFind.setValueText( dto.getValueText() );

        return historyFind;
    }

    @Override
    public HistoryFindDto toDto(HistoryFind entity) {
        if ( entity == null ) {
            return null;
        }

        HistoryFindDto historyFindDto = new HistoryFindDto();

        historyFindDto.setId( entity.getId() );
        if ( entity.getModifiedDate() != null ) {
            historyFindDto.setModifiedDate( new Timestamp( entity.getModifiedDate().getTime() ) );
        }
        if ( entity.getCreatedDate() != null ) {
            historyFindDto.setCreatedDate( new Timestamp( entity.getCreatedDate().getTime() ) );
        }
        historyFindDto.setDeleted( entity.isDeleted() );
        historyFindDto.setIdUser( entity.getIdUser() );
        historyFindDto.setValueText( entity.getValueText() );

        return historyFindDto;
    }

    @Override
    public List<HistoryFind> toEntity(List<HistoryFindDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<HistoryFind> list = new ArrayList<HistoryFind>( dtoList.size() );
        for ( HistoryFindDto historyFindDto : dtoList ) {
            list.add( toEntity( historyFindDto ) );
        }

        return list;
    }

    @Override
    public List<HistoryFindDto> toDto(List<HistoryFind> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<HistoryFindDto> list = new ArrayList<HistoryFindDto>( entityList.size() );
        for ( HistoryFind historyFind : entityList ) {
            list.add( toDto( historyFind ) );
        }

        return list;
    }

    @Override
    public Set<HistoryFindDto> toDto(Set<HistoryFind> entityList) {
        if ( entityList == null ) {
            return null;
        }

        Set<HistoryFindDto> set = new LinkedHashSet<HistoryFindDto>( Math.max( (int) ( entityList.size() / .75f ) + 1, 16 ) );
        for ( HistoryFind historyFind : entityList ) {
            set.add( toDto( historyFind ) );
        }

        return set;
    }
}
