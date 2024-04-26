package com.be.controller;

import com.be.common_api.BillDetail;
import com.be.dto.BillDetailDto;
import com.be.mapper.BillDetailMapper;
import com.be.service.BillDetailService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/api/bill-detail")
@RestController
@Slf4j
@Api("bill-detail")
public class BillDetailController {
    private final BillDetailService billDetailService;

    public BillDetailController(BillDetailService billDetailService) {
        this.billDetailService = billDetailService;
    }

    @PostMapping("/post")
    public ResponseEntity<BillDetailDto> save(@RequestBody @Validated BillDetailDto billDetailDto) {
        BillDetailDto billDetailDto1 = billDetailService.save(billDetailDto);
        return ResponseEntity.ok(billDetailDto1);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<BillDetailDto> findById(@PathVariable("id") Long id) {
        BillDetailDto billDetail = billDetailService.findById(id);
        return ResponseEntity.ok(billDetail);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Optional.ofNullable(billDetailService.findById(id)).orElseThrow(() -> {
            log.error("Unable to delete non-existent data！");
            return new FileSystemAlreadyExistsException();
        });
        billDetailService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/page")
    public ResponseEntity<Page<BillDetailDto>> pageQuery(@Filter Specification<BillDetail> spec, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 100000) Pageable pageable) {
        Page<BillDetailDto> billDetailPage = billDetailService.findByCondition(spec, pageable);
        return ResponseEntity.ok(billDetailPage);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity< Map<String, Object>> update(@RequestBody @Validated BillDetailDto billDetailDto, @PathVariable("id") Long id) {
        Map<String, Object> result =new HashMap<String, Object>();
        try {
            BillDetailDto billDetail = billDetailService.findById(id);
            if(billDetailDto.getIdBill() != null && billDetailDto.getIdDecor() != null && billDetailDto.getNumber()!=null && billDetailDto.getNumber() > 0){
                billDetail.setNumber(billDetailDto.getNumber());
                BillDetailDto billDetailDto1 = billDetailService.update(billDetail, id);
                result.put("result", billDetailDto1);
                result.put("success",true);
            }else {
                result.put("result", "Data erorr");
                result.put("success",false);
            }

        }catch (Exception e){
            result.put("result", e.getMessage());
            result.put("success", false);
        }

        return ResponseEntity.ok(result);
    }
}