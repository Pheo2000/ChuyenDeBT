package com.be.mapper;

import com.be.common_api.HistoryFind;
import com.be.dto.HistoryFindDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HistoryFindMapper extends EntityMapper<HistoryFindDto, HistoryFind> {
}