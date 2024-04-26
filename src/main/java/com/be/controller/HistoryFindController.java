package com.be.controller;

import com.be.common_api.HistoryFind;
import com.be.dto.HistoryFindDto;
import com.be.mapper.HistoryFindMapper;
import com.be.service.HistoryFindService;
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

import java.nio.file.FileSystemAlreadyExistsException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/api/history-find")
@RestController
@Slf4j
@Api("history-find")
public class HistoryFindController {
    private final HistoryFindService historyFindService;

    public HistoryFindController(HistoryFindService historyFindService) {
        this.historyFindService = historyFindService;
    }

    @PostMapping("/post")
    public ResponseEntity<HistoryFindDto> save(@RequestBody @Validated HistoryFindDto historyFindDto) {
        HistoryFindDto historyFindDto1 = historyFindService.save(historyFindDto);
        return ResponseEntity.ok(historyFindDto1);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Optional.ofNullable(historyFindService.findById(id)).orElseThrow(() -> {
            log.error("Unable to delete non-existent data！");
            return new FileSystemAlreadyExistsException();
        });
        historyFindService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/page")
    public ResponseEntity<Page<HistoryFindDto>> pageQuery(@Filter Specification<HistoryFind> spec, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 100000) Pageable pageable) {
        Page<HistoryFindDto> historyFindPage = historyFindService.findByCondition(spec, pageable);
        return ResponseEntity.ok(historyFindPage);
    }
}