package com.be.service;

import com.be.common_api.DecorType;
import com.be.dto.DecorTypeDto;
import com.be.handler.Utils;
import com.be.mapper.DecorTypeMapper;
import com.be.repository.DecorTypeRepository;
import com.llq.springfilter.boot.Filter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Slf4j
@Service
@Transactional
public class DecorTypeService {
    private final DecorTypeRepository repository;
    private final DecorTypeMapper decorTypeMapper;

    public DecorTypeService(DecorTypeRepository repository, DecorTypeMapper decorTypeMapper) {
        this.repository = repository;
        this.decorTypeMapper = decorTypeMapper;
    }

    public DecorTypeDto save(DecorTypeDto decorTypeDto) {
        DecorType entity = decorTypeMapper.toEntity(decorTypeDto);
        return decorTypeMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public DecorTypeDto findById(Long id) {
        return decorTypeMapper.toDto(repository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Item Not Found! ID: " + id)
        ));
    }
    public Page<DecorTypeDto> findByCondition(@Filter Specification<DecorType> spec, Pageable pageable) {
        Page<DecorType> entityPage = repository.findAll(pageable);
        List<DecorType> entities = entityPage.getContent();
        return new PageImpl<>(decorTypeMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public DecorTypeDto update(DecorTypeDto decorTypeDto, Long id) {
        DecorTypeDto data = findById(id);
        DecorType entity = decorTypeMapper.toEntity(decorTypeDto);
        BeanUtils.copyProperties(entity, data, Utils.getNullPropertyNames(entity));
        return save(decorTypeMapper.toDto(entity));
    }
}