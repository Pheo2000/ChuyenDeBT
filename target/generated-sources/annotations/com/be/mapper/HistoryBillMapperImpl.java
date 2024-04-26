package com.be.mapper;

import com.be.common_api.HistoryBill;
import com.be.dto.HistoryBillDto;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-24T22:35:05+0700",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.22 (Oracle Corporation)"
)
@Component
public class HistoryBillMapperImpl implements HistoryBillMapper {

    @Override
    public HistoryBill toEntity(HistoryBillDto dto) {
        if ( dto == null ) {
            return null;
        }

        HistoryBill historyBill = new HistoryBill();

        historyBill.setId( dto.getId() );
        historyBill.setModifiedDate( dto.getModifiedDate() );
        historyBill.setCreatedDate( dto.getCreatedDate() );
        historyBill.setDeleted( dto.isDeleted() );
        historyBill.setIdBill( dto.getIdBill() );
        historyBill.setBill( dto.getBill() );
        historyBill.setStatus( dto.getStatus() );

        return historyBill;
    }

    @Override
    public HistoryBillDto toDto(HistoryBill entity) {
        if ( entity == null ) {
            return null;
        }

        HistoryBillDto historyBillDto = new HistoryBillDto();

        historyBillDto.setId( entity.getId() );
        if ( entity.getModifiedDate() != null ) {
            historyBillDto.setModifiedDate( new Timestamp( entity.getModifiedDate().getTime() ) );
        }
        if ( entity.getCreatedDate() != null ) {
            historyBillDto.setCreatedDate( new Timestamp( entity.getCreatedDate().getTime() ) );
        }
        historyBillDto.setDeleted( entity.isDeleted() );
        historyBillDto.setIdBill( entity.getIdBill() );
        historyBillDto.setBill( entity.getBill() );
        historyBillDto.setStatus( entity.getStatus() );

        return historyBillDto;
    }

    @Override
    public List<HistoryBill> toEntity(List<HistoryBillDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<HistoryBill> list = new ArrayList<HistoryBill>( dtoList.size() );
        for ( HistoryBillDto historyBillDto : dtoList ) {
            list.add( toEntity( historyBillDto ) );
        }

        return list;
    }

    @Override
    public List<HistoryBillDto> toDto(List<HistoryBill> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<HistoryBillDto> list = new ArrayList<HistoryBillDto>( entityList.size() );
        for ( HistoryBill historyBill : entityList ) {
            list.add( toDto( historyBill ) );
        }

        return list;
    }

    @Override
    public Set<HistoryBillDto> toDto(Set<HistoryBill> entityList) {
        if ( entityList == null ) {
            return null;
        }

        Set<HistoryBillDto> set = new LinkedHashSet<HistoryBillDto>( Math.max( (int) ( entityList.size() / .75f ) + 1, 16 ) );
        for ( HistoryBill historyBill : entityList ) {
            set.add( toDto( historyBill ) );
        }

        return set;
    }
}
