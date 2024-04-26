package com.be.mapper;

import com.be.common_api.Bill;
import com.be.dto.BillDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BillMapper extends EntityMapper<BillDto, Bill> {
}