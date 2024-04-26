package com.be.mapper;

import com.be.common_api.Bill;
import com.be.dto.BillDto;
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
public class BillMapperImpl implements BillMapper {

    @Override
    public Bill toEntity(BillDto dto) {
        if ( dto == null ) {
            return null;
        }

        Bill bill = new Bill();

        bill.setId( dto.getId() );
        bill.setModifiedDate( dto.getModifiedDate() );
        bill.setCreatedDate( dto.getCreatedDate() );
        bill.setDeleted( dto.isDeleted() );
        bill.setIdUser( dto.getIdUser() );
        bill.setUser( dto.getUser() );
        bill.setTotal( dto.getTotal() );
        bill.setMethodPayment( dto.getMethodPayment() );
        bill.setStatus( dto.getStatus() );

        return bill;
    }

    @Override
    public BillDto toDto(Bill entity) {
        if ( entity == null ) {
            return null;
        }

        BillDto billDto = new BillDto();

        billDto.setId( entity.getId() );
        if ( entity.getModifiedDate() != null ) {
            billDto.setModifiedDate( new Timestamp( entity.getModifiedDate().getTime() ) );
        }
        if ( entity.getCreatedDate() != null ) {
            billDto.setCreatedDate( new Timestamp( entity.getCreatedDate().getTime() ) );
        }
        billDto.setDeleted( entity.isDeleted() );
        billDto.setIdUser( entity.getIdUser() );
        billDto.setUser( entity.getUser() );
        billDto.setTotal( entity.getTotal() );
        billDto.setMethodPayment( entity.getMethodPayment() );
        billDto.setStatus( entity.getStatus() );

        return billDto;
    }

    @Override
    public List<Bill> toEntity(List<BillDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Bill> list = new ArrayList<Bill>( dtoList.size() );
        for ( BillDto billDto : dtoList ) {
            list.add( toEntity( billDto ) );
        }

        return list;
    }

    @Override
    public List<BillDto> toDto(List<Bill> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<BillDto> list = new ArrayList<BillDto>( entityList.size() );
        for ( Bill bill : entityList ) {
            list.add( toDto( bill ) );
        }

        return list;
    }

    @Override
    public Set<BillDto> toDto(Set<Bill> entityList) {
        if ( entityList == null ) {
            return null;
        }

        Set<BillDto> set = new LinkedHashSet<BillDto>( Math.max( (int) ( entityList.size() / .75f ) + 1, 16 ) );
        for ( Bill bill : entityList ) {
            set.add( toDto( bill ) );
        }

        return set;
    }
}
