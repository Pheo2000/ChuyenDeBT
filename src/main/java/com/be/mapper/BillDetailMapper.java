package com.be.mapper;

import com.be.common_api.BillDetail;
import com.be.dto.BillDetailDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BillDetailMapper extends EntityMapper<BillDetailDto, BillDetail> {
}