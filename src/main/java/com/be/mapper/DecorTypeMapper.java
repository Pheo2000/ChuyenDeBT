package com.be.mapper;

import com.be.common_api.DecorType;
import com.be.dto.DecorTypeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DecorTypeMapper extends EntityMapper<DecorTypeDto, DecorType> {
}