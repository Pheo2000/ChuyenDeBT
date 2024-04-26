package com.be.service;

import com.be.common_api.Bill;
import com.be.dto.BillDto;
import com.be.handler.Utils;
import com.be.mapper.BillMapper;
import com.be.repository.BillRepository;
import com.llq.springfilter.boot.Filter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
public class BillService {
    private final BillRepository repository;
    private final BillMapper billMapper;

    public BillService(BillRepository repository, BillMapper billMapper) {
        this.repository = repository;
        this.billMapper = billMapper;
    }

    public BillDto save(BillDto billDto) {
        Bill entity = billMapper.toEntity(billDto);
        return billMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public BillDto findById(Long id) {
        return billMapper.toDto(repository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Item Not Found! ID: " + id)
        ));
    }
    public Page<BillDto> findByCondition(@Filter Specification<Bill> spec, Pageable pageable) {
        Page<Bill> entityPage = repository.findAll(spec, pageable);
        List<Bill> entities = entityPage.getContent();
        return new PageImpl<>(billMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public BillDto update(BillDto billDto, Long id) {
        BillDto data = findById(id);
        Bill entity = billMapper.toEntity(billDto);
        BeanUtils.copyProperties(entity, data, Utils.getNullPropertyNames(entity));
        return save(billMapper.toDto(entity));
    }
}