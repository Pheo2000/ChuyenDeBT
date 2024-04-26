package com.be.dto;

import com.be.common_api.NguoiDung;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@ApiModel()
@Getter
@Setter
public class BillDto extends BaseDto {
    private Long idUser;
    private NguoiDung user;

    private Integer total;
    private Short methodPayment;
    private Short status;

    public BillDto() {
    }
}