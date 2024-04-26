package com.be.mapper;

import com.be.common_api.BillDetail;
import com.be.dto.BillDetailDto;
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
public class BillDetailMapperImpl implements BillDetailMapper {

    @Override
    public BillDetail toEntity(BillDetailDto dto) {
        if ( dto == null ) {
            return null;
        }

        BillDetail billDetail = new BillDetail();

        billDetail.setId( dto.getId() );
        billDetail.setModifiedDate( dto.getModifiedDate() );
        billDetail.setCreatedDate( dto.getCreatedDate() );
        billDetail.setDeleted( dto.isDeleted() );
        billDetail.setIdDecor( dto.getIdDecor() );
        billDetail.setDecor( dto.getDecor() );
        billDetail.setIdBill( dto.getIdBill() );
        billDetail.setBill( dto.getBill() );
        billDetail.setNumber( dto.getNumber() );

        return billDetail;
    }

    @Override
    public BillDetailDto toDto(BillDetail entity) {
        if ( entity == null ) {
            return null;
        }

        BillDetailDto billDetailDto = new BillDetailDto();

        billDetailDto.setId( entity.getId() );
        if ( entity.getModifiedDate() != null ) {
            billDetailDto.setModifiedDate( new Timestamp( entity.getModifiedDate().getTime() ) );
        }
        if ( entity.getCreatedDate() != null ) {
            billDetailDto.setCreatedDate( new Timestamp( entity.getCreatedDate().getTime() ) );
        }
        billDetailDto.setDeleted( entity.isDeleted() );
        billDetailDto.setIdDecor( entity.getIdDecor() );
        billDetailDto.setDecor( entity.getDecor() );
        billDetailDto.setIdBill( entity.getIdBill() );
        billDetailDto.setBill( entity.getBill() );
        billDetailDto.setNumber( entity.getNumber() );

        return billDetailDto;
    }

    @Override
    public List<BillDetail> toEntity(List<BillDetailDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<BillDetail> list = new ArrayList<BillDetail>( dtoList.size() );
        for ( BillDetailDto billDetailDto : dtoList ) {
            list.add( toEntity( billDetailDto ) );
        }

        return list;
    }

    @Override
    public List<BillDetailDto> toDto(List<BillDetail> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<BillDetailDto> list = new ArrayList<BillDetailDto>( entityList.size() );
        for ( BillDetail billDetail : entityList ) {
            list.add( toDto( billDetail ) );
        }

        return list;
    }

    @Override
    public Set<BillDetailDto> toDto(Set<BillDetail> entityList) {
        if ( entityList == null ) {
            return null;
        }

        Set<BillDetailDto> set = new LinkedHashSet<BillDetailDto>( Math.max( (int) ( entityList.size() / .75f ) + 1, 16 ) );
        for ( BillDetail billDetail : entityList ) {
            set.add( toDto( billDetail ) );
        }

        return set;
    }
}
