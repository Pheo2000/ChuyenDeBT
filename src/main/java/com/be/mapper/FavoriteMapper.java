package com.be.mapper;

import com.be.common_api.Favorite;
import com.be.dto.FavoriteDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FavoriteMapper extends EntityMapper<FavoriteDto, Favorite> {
}