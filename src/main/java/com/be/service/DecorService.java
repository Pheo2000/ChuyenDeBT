package com.be.service;

import com.be.common_api.Decor;
import com.be.dto.DecorDto;
import com.be.handler.Utils;
import com.be.mapper.DecorMapper;
import com.be.repository.DecorRepository;
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
public class DecorService {
    private final DecorRepository repository;
    private final DecorMapper decorMapper;

    public DecorService(DecorRepository repository, DecorMapper decorMapper) {
        this.repository = repository;
        this.decorMapper = decorMapper;
    }

    public DecorDto save(DecorDto decorDto) {
        Decor entity = decorMapper.toEntity(decorDto);
        return decorMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public DecorDto findById(Long id) {
        return decorMapper.toDto(repository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Item Not Found! ID: " + id)
        ));
    }

    public Page<DecorDto> findByCondition(@Filter Specification<Decor> spec, Pageable pageable) {
        Page<Decor> entityPage = repository.findAll(spec, pageable);
        List<Decor> entities = entityPage.getContent();
        return new PageImpl<>(decorMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public DecorDto update(DecorDto decorDto, Long id) {
        DecorDto data = findById(id);
        Decor entity = decorMapper.toEntity(decorDto);
        BeanUtils.copyProperties(entity, data, Utils.getNullPropertyNames(entity));
        return save(decorMapper.toDto(entity));
    }
}