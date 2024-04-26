package com.be.service;

import com.be.common_api.HistoryFind;
import com.be.dto.HistoryFindDto;
import com.be.handler.Utils;
import com.be.mapper.HistoryFindMapper;
import com.be.repository.HistoryFindRepository;
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
public class HistoryFindService {
    private final HistoryFindRepository repository;
    private final HistoryFindMapper historyFindMapper;

    public HistoryFindService(HistoryFindRepository repository, HistoryFindMapper historyFindMapper) {
        this.repository = repository;
        this.historyFindMapper = historyFindMapper;
    }

    public HistoryFindDto save(HistoryFindDto historyFindDto) {
        HistoryFind entity = historyFindMapper.toEntity(historyFindDto);
        return historyFindMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public HistoryFindDto findById(Long id) {
        return historyFindMapper.toDto(repository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Item Not Found! ID: " + id)
        ));
    }

    public Page<HistoryFindDto> findByCondition(@Filter Specification<HistoryFind> spec, Pageable pageable) {
        Page<HistoryFind> entityPage = repository.findAll(spec, pageable);
        List<HistoryFind> entities = entityPage.getContent();
        return new PageImpl<>(historyFindMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public HistoryFindDto update(HistoryFindDto historyFindDto, Long id) {
        HistoryFindDto data = findById(id);
        HistoryFind entity = historyFindMapper.toEntity(historyFindDto);
        BeanUtils.copyProperties(entity, data, Utils.getNullPropertyNames(entity));
        return save(historyFindMapper.toDto(entity));
    }
}