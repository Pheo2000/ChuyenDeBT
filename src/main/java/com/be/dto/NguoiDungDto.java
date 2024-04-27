package com.be.dto;

import com.be.annotation.CheckEmail;
import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Description;

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

    @Schema(description = "status = 0 : đã xóa, status = 1 : đang active, default : status = 1")
    private Short status;
    public NguoiDungDto() {
    }
}