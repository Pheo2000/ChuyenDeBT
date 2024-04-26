package com.be.controller;

import com.be.common_api.DecorType;
import com.be.dto.DecorTypeDto;
import com.be.mapper.DecorTypeMapper;
import com.be.service.DecorTypeService;
import com.llq.springfilter.boot.Filter;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/api/decor-type")
@RestController
@Slf4j
@Api("decor-type")
public class DecorTypeController {
    private final DecorTypeService decorTypeService;

    public DecorTypeController(DecorTypeService decorTypeService) {
        this.decorTypeService = decorTypeService;
    }
    @PostMapping("/post")
    public ResponseEntity<DecorTypeDto> save(@RequestBody @Validated DecorTypeDto decorTypeDto) {
        DecorTypeDto decorTypeData =  decorTypeService.save(decorTypeDto);
        return ResponseEntity.ok(decorTypeData);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<DecorTypeDto> findById(@PathVariable("id") Long id) {
        DecorTypeDto decorType = decorTypeService.findById(id);
        return ResponseEntity.ok(decorType);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        DecorTypeDto decorType = decorTypeService.findById(id);
        decorType.setDeleted(true);
        decorTypeService.update(decorType, id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/page")
    public ResponseEntity<Page<DecorTypeDto>> pageQuery(@Filter Specification<DecorType> spec, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 100000) Pageable pageable) {
        Page<DecorTypeDto> decorTypePage = decorTypeService.findByCondition(spec, pageable);
        return ResponseEntity.ok(decorTypePage);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<DecorTypeDto> update(@RequestBody DecorTypeDto decorTypeDto, @PathVariable("id") Long id) {
        DecorTypeDto decorType = decorTypeService.findById(id);
        if(decorTypeDto.getName() !=null && decorTypeDto.getName() != ""){
            decorType.setName(decorTypeDto.getName());
        }
        if(decorTypeDto.getImage() !=null && decorTypeDto.getImage() != ""){
            decorType.setImage(decorTypeDto.getImage());
        }
        if(decorTypeDto.getStatus() !=null){
            decorType.setStatus(decorTypeDto.getStatus());
        }
        DecorTypeDto decorTypeData = decorTypeService.update(decorType, id);
        return ResponseEntity.ok(decorTypeData);
    }
}