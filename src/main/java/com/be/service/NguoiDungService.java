package com.be.service;

import com.be.common_api.NguoiDung;
import com.be.dto.NguoiDungDto;
import com.be.handler.Utils;
import com.be.mapper.NguoiDungMapper;
import com.be.repository.NguoiDungRepository;
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
public class NguoiDungService {
    private final NguoiDungRepository repository;
    private final NguoiDungMapper nguoiDungMapper;

    public NguoiDungService(NguoiDungRepository repository, NguoiDungMapper nguoiDungMapper) {
        this.repository = repository;
        this.nguoiDungMapper = nguoiDungMapper;
    }

    public NguoiDungDto save(NguoiDungDto nguoiDungDto) {
        NguoiDung entity = nguoiDungMapper.toEntity(nguoiDungDto);
        return nguoiDungMapper.toDto(repository.save(entity));
    }

    public NguoiDungDto findById(Long id) {
        return nguoiDungMapper.toDto(repository.findById(id).get());
//        return repository.findById(id).orElseThrow(()
//                -> new EntityNotFoundException("Item Not Found! ID: " + id)
//        );
    }

    public Page<NguoiDungDto> findByCondition(@Filter Specification<NguoiDung> spec, Pageable pageable) {
        Page<NguoiDung> entityPage = repository.findAll(spec, pageable);
        List<NguoiDung> entities = entityPage.getContent();
        return new PageImpl<>(nguoiDungMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public NguoiDungDto update(NguoiDungDto nguoiDungDto, Long id) {
        NguoiDungDto userNow = findById(id);
        NguoiDung entity = nguoiDungMapper.toEntity(nguoiDungDto);
        entity.setPassword(userNow.getPassword());
        BeanUtils.copyProperties(entity, userNow, Utils.getNullPropertyNames(entity));
        return save(nguoiDungMapper.toDto(entity));
    }

    public NguoiDungDto findByEmail(String email){
        NguoiDung entity = repository.findByEmail(email);
        NguoiDungDto tblUserDto = nguoiDungMapper.toDto(entity);
        return tblUserDto;
    }
}