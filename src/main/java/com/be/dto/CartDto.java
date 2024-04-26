package com.be.dto;

import com.be.common_api.Decor;
import com.be.common_api.NguoiDung;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@ApiModel()
@Getter
@Setter
public class CartDto extends BaseDto {
    private Long idDecor;
    private Decor decor;
    private Long idUser;
    private NguoiDung user;
    private Integer number;

    public CartDto() {
    }
}