package com.be.dto;

import com.be.annotation.CheckEmail;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@ApiModel()
@Getter
@Setter
public class NguoiDungDto extends BaseDto {
    @CheckEmail
    @Size(max = 255)
    private String email;
    @Size(max = 255)
    private String password;
    private Short role;
    @Size(max = 255)
    private String fullName;
    private Boolean gioiTinh;
    private String address;
    @Size(max = 255)
    private String sdt;
    @Size(max = 255)
    private String avatar;
    private Short status;
    public NguoiDungDto() {
    }
}