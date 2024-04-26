package com.be.controller;

import com.be.common_api.Favorite;
import com.be.dto.FavoriteDto;
import com.be.mapper.FavoriteMapper;
import com.be.service.FavoriteService;
import com.llq.springfilter.boot.Filter;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/favorite")
@RestController
@Slf4j
@Api("favorite")
public class FavoriteController {
    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @PostMapping("/post")
    public ResponseEntity<FavoriteDto> save(@RequestBody @Validated FavoriteDto favoriteDto) {
        FavoriteDto favoriteDto1 = favoriteService.save(favoriteDto);
        return ResponseEntity.ok(favoriteDto1);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<FavoriteDto> findById(@PathVariable("id") Long id) {
        FavoriteDto favorite = favoriteService.findById(id);
        return ResponseEntity.ok(favorite);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        FavoriteDto favorite = favoriteService.findById(id);
        favorite.setDeleted(true);
        favoriteService.update(favorite, id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page")
    public ResponseEntity<Page<FavoriteDto>> pageQuery(@Filter Specification<Favorite> spec, @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<FavoriteDto> favoritePage = favoriteService.findByCondition(spec, pageable);
        return ResponseEntity.ok(favoritePage);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<FavoriteDto> update(@RequestBody @Validated FavoriteDto favoriteDto, @PathVariable("id") Long id) {
        FavoriteDto favorite = favoriteService.findById(id);
        if(favoriteDto.getIdDecor() != null){
            favorite.setIdDecor(favoriteDto.getIdDecor());
        }
        if(favoriteDto.getIdUser() != null){
            favorite.setIdUser(favoriteDto.getIdUser());
        }
        FavoriteDto favoriteDto1 = favoriteService.update(favorite, id);
        return ResponseEntity.ok(favoriteDto1);
    }
}