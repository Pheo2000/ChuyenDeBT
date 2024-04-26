package com.be.service;

import com.be.common_api.BillDetail;
import com.be.dto.BillDetailDto;
import com.be.handler.Utils;
import com.be.mapper.BillDetailMapper;
import com.be.repository.BillDetailRepository;
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
public class BillDetailService {
    private final BillDetailRepository repository;
    private final BillDetailMapper billDetailMapper;

    public BillDetailService(BillDetailRepository repository, BillDetailMapper billDetailMapper) {
        this.repository = repository;
        this.billDetailMapper = billDetailMapper;
    }

    public BillDetailDto save(BillDetailDto billDetailDto) {
        BillDetail entity = billDetailMapper.toEntity(billDetailDto);
        return billDetailMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public BillDetailDto findById(Long id) {
        return billDetailMapper.toDto(repository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Item Not Found! ID: " + id)
        ));
    }
    public Page<BillDetailDto> findByCondition(@Filter Specification<BillDetail> spec, Pageable pageable) {
        Page<BillDetail> entityPage = repository.findAll(pageable);
        List<BillDetail> entities = entityPage.getContent();
        return new PageImpl<>(billDetailMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public BillDetailDto update(BillDetailDto billDetailDto, Long id) {
        BillDetailDto data = findById(id);
        BillDetail entity = billDetailMapper.toEntity(billDetailDto);
        BeanUtils.copyProperties(entity, data, Utils.getNullPropertyNames(entity));
        return save(billDetailMapper.toDto(entity));
    }
}