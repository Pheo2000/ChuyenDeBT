package com.be.service;

import com.be.common_api.HistoryBill;
import com.be.dto.HistoryBillDto;
import com.be.handler.Utils;
import com.be.mapper.HistoryBillMapper;
import com.be.repository.HistoryBillRepository;
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
public class HistoryBillService {
    private final HistoryBillRepository repository;
    private final HistoryBillMapper historyBillMapper;

    public HistoryBillService(HistoryBillRepository repository, HistoryBillMapper historyBillMapper) {
        this.repository = repository;
        this.historyBillMapper = historyBillMapper;
    }

    public HistoryBillDto save(HistoryBillDto historyBillDto) {
        HistoryBill entity = historyBillMapper.toEntity(historyBillDto);
        return historyBillMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public HistoryBillDto findById(Long id) {
        return historyBillMapper.toDto(repository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Item Not Found! ID: " + id)
        ));
    }
    public Page<HistoryBillDto> findByCondition(@Filter Specification<HistoryBill> spec, Pageable pageable) {
        Page<HistoryBill> entityPage = repository.findAll(spec, pageable);
        List<HistoryBill> entities = entityPage.getContent();
        return new PageImpl<>(historyBillMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public HistoryBillDto update(HistoryBillDto historyBillDto, Long id) {
        HistoryBillDto data = findById(id);
        HistoryBill entity = historyBillMapper.toEntity(historyBillDto);
        BeanUtils.copyProperties(entity, data, Utils.getNullPropertyNames(entity));
        return save(historyBillMapper.toDto(entity));
    }
}