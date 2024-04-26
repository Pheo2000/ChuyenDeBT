package com.be.mapper;

import com.be.common_api.Decor;
import com.be.dto.DecorDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DecorMapper extends EntityMapper<DecorDto, Decor> {
}