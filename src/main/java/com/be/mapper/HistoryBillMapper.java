package com.be.mapper;

import com.be.common_api.HistoryBill;
import com.be.dto.HistoryBillDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HistoryBillMapper extends EntityMapper<HistoryBillDto, HistoryBill> {
}