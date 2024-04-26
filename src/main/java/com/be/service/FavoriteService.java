package com.be.service;

import com.be.common_api.Favorite;
import com.be.dto.FavoriteDto;
import com.be.handler.Utils;
import com.be.mapper.FavoriteMapper;
import com.be.repository.FavoriteRepository;
import com.llq.springfilter.boot.Filter;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class FavoriteService {
    private final FavoriteRepository repository;
    private final FavoriteMapper favoriteMapper;

    public FavoriteService(FavoriteRepository repository, FavoriteMapper favoriteMapper) {
        this.repository = repository;
        this.favoriteMapper = favoriteMapper;
    }

    public FavoriteDto save(FavoriteDto favoriteDto) {
        Favorite entity = favoriteMapper.toEntity(favoriteDto);
        return favoriteMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public FavoriteDto findById(Long id) {
        return favoriteMapper.toDto(repository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Item Not Found! ID: " + id)
        ));
    }

    public Page<FavoriteDto> findByCondition(@Filter Specification<Favorite> spec, Pageable pageable) {
        Page<Favorite> entityPage = repository.findAll(spec, pageable);
        List<Favorite> entities = entityPage.getContent();
        return new PageImpl<>(favoriteMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public FavoriteDto update(FavoriteDto favoriteDto, Long id) {
        FavoriteDto data = findById(id);
        Favorite entity = favoriteMapper.toEntity(favoriteDto);
        BeanUtils.copyProperties(entity, data, Utils.getNullPropertyNames(entity));
        return save(favoriteMapper.toDto(entity));
    }
}