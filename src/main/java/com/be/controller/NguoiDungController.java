package com.be.controller;

import com.be.authenticate.JwtUtil;
import com.be.authenticate.UserPrincipal;
import com.be.common_api.HethongNguoidungToken;
import com.be.common_api.NguoiDung;
import com.be.common_api.UserLogin;
import com.be.dto.NguoiDungDto;
import com.be.handler.Utils;
import com.be.service.NguoiDungService;
import com.be.service.TokenService;
import com.llq.springfilter.boot.Filter;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.nio.file.FileSystemAlreadyExistsException;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.HashMap;
import java.util.Map;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/nguoi-dung")
@RestController
@Slf4j
@Api("nguoi-dung")
public class NguoiDungController {
    private final NguoiDungService nguoiDungService;
    private final TokenService tokenService;
    private final JwtUtil jwtUtil;
    public NguoiDungController(NguoiDungService nguoiDungService, TokenService tokenService, JwtUtil jwtUtil) {
        this.nguoiDungService = nguoiDungService;
        this.tokenService = tokenService;
        this.jwtUtil = jwtUtil;
    }


    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginPass(@RequestBody @Validated UserLogin userLogin) {
        Map<String, Object> result =new HashMap<String, Object>();
        try {
            NguoiDungDto nguoiDung = nguoiDungService.findByEmail(userLogin.getUsername());
            Boolean checkPass = BCrypt.checkpw(userLogin.getPassword(), nguoiDung.getPassword());
            if(checkPass){
//                UserPrincipal userPrincipal = new UserPrincipal();
//                userPrincipal.setUserId(nguoiDung.getId());
//                userPrincipal.setUsername(nguoiDung.getEmail());
//                userPrincipal.setPassword(nguoiDung.getPassword());
//                userPrincipal.setAdmin(nguoiDung.getRole()==0);
//                //
//                HethongNguoidungToken hethongNguoidungToken = new HethongNguoidungToken();
//                hethongNguoidungToken.setCreatedUser(nguoiDung.getId());
//                String tokenGen = jwtUtil.generateToken(userPrincipal);
//                hethongNguoidungToken.setToken(tokenGen);
//                hethongNguoidungToken.setTokenexpdate(Timestamp.valueOf("2999-03-12 20:45:00"));
//                HethongNguoidungToken heThongNguoidungTokenSave = tokenService.createToken(hethongNguoidungToken);
//                result.put("result", heThongNguoidungTokenSave.getToken());


                if(nguoiDung.getStatus()==1){
                    result.put("result", nguoiDung);
                    result.put("success", true);
                }else {
                    result.put("result", "tai khoan bi khoa ");
                    result.put("success", false);
                }

            }else {
                result.put("result", "Tài khoản / mật khẩu không đúng");
                result.put("success", false);
            }
        }catch (Exception e){
            result.put("result",e.getMessage());
            result.put("success", false);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> save(@RequestBody @Validated NguoiDungDto nguoiDungDto) {
        Map<String, Object> result =new HashMap<String, Object>();
        NguoiDungDto tblUser = nguoiDungService.findByEmail(nguoiDungDto.getEmail());
        if(tblUser == null){
            try{
                String passConvert = Utils.getBCryptedPassword(nguoiDungDto.getPassword());
                nguoiDungDto.setPassword(passConvert);
                NguoiDungDto userLogin=  nguoiDungService.save(nguoiDungDto);
                result.put("result", userLogin);
                result.put("success", true);
            }catch (Exception e){
                result.put("result",e.getMessage());
                result.put("success", false);
            }
        }
        else{
            result.put("result", "Email đã tồm tại");
            result.put("success", false);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable("id") Long id) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            NguoiDungDto nguoiDung = nguoiDungService.findById(id);
            result.put("result",nguoiDung);
            result.put("success",true);
        } catch (Exception e) {
            result.put("result",e.getMessage());
            result.put("success",false);
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        try {
            NguoiDungDto nguoiDung = nguoiDungService.findById(id);
            nguoiDung.setDeleted(true);
            nguoiDungService.update(nguoiDung, id);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/get/page")
    public ResponseEntity<Page<NguoiDungDto>> pageQuery(@Filter Specification<NguoiDung> spec, Pageable pageable) {
        Map<String, Object> result = new HashMap<String, Object>();
        Page<NguoiDungDto> nguoiDungPage = nguoiDungService.findByCondition(spec, pageable);
        return ResponseEntity.ok(nguoiDungPage);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Map<String, Object>> update(@RequestBody NguoiDungDto nguoiDungDto, @PathVariable("id") Long id) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            NguoiDungDto nguoiDung = nguoiDungService.findById(id);
            if(nguoiDungDto.getAvatar() !=null && nguoiDungDto.getAvatar() != ""){
                nguoiDung.setAvatar(nguoiDungDto.getAvatar());
            }
            if(nguoiDungDto.getFullName() !=null && nguoiDungDto.getFullName() != ""){
                nguoiDung.setFullName(nguoiDungDto.getFullName());
            }
            if(nguoiDungDto.getGioiTinh() !=null ){
                nguoiDung.setGioiTinh(nguoiDungDto.getGioiTinh());
            }
            if(nguoiDungDto.getSdt() !=null && nguoiDungDto.getSdt() != ""){
                nguoiDung.setSdt(nguoiDungDto.getSdt());
            }
            if(nguoiDungDto.getAddress() !=null && nguoiDungDto.getAddress() != ""){
                nguoiDung.setAddress(nguoiDungDto.getAddress());
            }
            if(nguoiDungDto.getStatus() !=null ){
                nguoiDung.setStatus(nguoiDungDto.getStatus());
            }
            NguoiDungDto item=  nguoiDungService.update(nguoiDung, id);
            result.put("result",nguoiDung);
            result.put("success",true);
        } catch (Exception e) {
            result.put("result",e.getMessage());
            result.put("success",false);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/change-password/{id}")
    public ResponseEntity<Map<String, Object>> changePass(@RequestBody @Validated NguoiDungDto nguoiDungDto, @PathVariable("id") Long id) {
        Map<String, Object> result =new HashMap<String, Object>();
        NguoiDungDto tblUser = nguoiDungService.findByEmail(nguoiDungDto.getEmail());
        if(tblUser == null){
            try{
                String passConvert = Utils.getBCryptedPassword(nguoiDungDto.getPassword());
                nguoiDungDto.setPassword(passConvert);
                NguoiDungDto userLogin=  nguoiDungService.save(nguoiDungDto);
                result.put("result", userLogin);
                result.put("success", true);
            }catch (Exception e){
                result.put("result",e.getMessage());
                result.put("success", false);
            }
        }
        else{
            result.put("result", "Email đã tồm tại");
            result.put("success", false);
        }
        return ResponseEntity.ok(result);
    }
}