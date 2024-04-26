package com.be.controller;

import com.be.common_api.HistoryBill;
import com.be.dto.HistoryBillDto;
import com.be.mapper.HistoryBillMapper;
import com.be.service.HistoryBillService;
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

@RequestMapping("/api/history-bill")
@RestController
@Slf4j
@Api("history-bill")
public class HistoryBillController {
    private final HistoryBillService historyBillService;

    public HistoryBillController(HistoryBillService historyBillService) {
        this.historyBillService = historyBillService;
    }

    @PostMapping("/post")
    public ResponseEntity<HistoryBillDto> save(@RequestBody @Validated HistoryBillDto historyBillDto) {
        HistoryBillDto historyBillDto1 = historyBillService.save(historyBillDto);
        return ResponseEntity.ok(historyBillDto1);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<HistoryBillDto> findById(@PathVariable("id") Long id) {
        HistoryBillDto historyBill = historyBillService.findById(id);
        return ResponseEntity.ok(historyBill);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Optional.ofNullable(historyBillService.findById(id)).orElseThrow(() -> {
            log.error("Unable to delete non-existent data！");
            return new FileSystemAlreadyExistsException();
        });
        historyBillService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/page")
    public ResponseEntity<Page<HistoryBillDto>> pageQuery(@Filter Specification<HistoryBill> spec, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 100000) Pageable pageable) {
        Page<HistoryBillDto> historyBillPage = historyBillService.findByCondition(spec, pageable);
        return ResponseEntity.ok(historyBillPage);
    }
}