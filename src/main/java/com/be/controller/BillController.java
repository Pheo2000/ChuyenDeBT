package com.be.controller;

import com.be.common_api.Bill;
import com.be.dto.BillDto;
import com.be.mapper.BillMapper;
import com.be.service.BillService;
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

@RequestMapping("/api/bill")
@RestController
@Slf4j
@Api("bill")
public class BillController {
    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @PostMapping("/post")
    public ResponseEntity<BillDto> save(@RequestBody @Validated BillDto billDto) {
        BillDto billDto1 = billService.save(billDto);
        return ResponseEntity.ok(billDto1);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<BillDto> findById(@PathVariable("id") Long id) {
        BillDto bill = billService.findById(id);
        return ResponseEntity.ok(bill);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        BillDto bill = billService.findById(id);
        bill.setDeleted(true);
        billService.update(bill, id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/page")
    public ResponseEntity<Page<BillDto>> pageQuery(@Filter Specification<Bill> spec, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 100000) Pageable pageable) {
        Page<BillDto> billPage = billService.findByCondition(spec, pageable);
        return ResponseEntity.ok(billPage);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<BillDto> update(@RequestBody @Validated BillDto billDto, @PathVariable("id") Long id) {
        BillDto bill = billService.findById(id);
        if(billDto.getIdUser() != null){
            bill.setIdUser(billDto.getIdUser());
        }
        if(billDto.getTotal() != null){
            bill.setTotal(billDto.getTotal());
        }
        if(billDto.getMethodPayment() != null){
            bill.setMethodPayment(billDto.getMethodPayment());
        }
        if(billDto.getStatus() != null){
            bill.setStatus(billDto.getStatus());
        }
        BillDto billDto1 = billService.update(bill, id);
        return ResponseEntity.ok(billDto1);
    }
}