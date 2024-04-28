package com.be.dto;

import com.be.annotation.CheckEmail;
import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@ApiModel()
@Getter
@Setter
public class UserDto {
    private Long userId;
    private String username;
    private String address;
    private String sdt;
    private String email;
}