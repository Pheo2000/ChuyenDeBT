package com.be.dto;

import com.be.common_api.Bill;
import com.be.common_api.Decor;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@ApiModel()
@Getter
@Setter
public class BillDetailDto extends BaseDto {
    private Long idDecor;
    private Decor decor;
    private Long idBill;
    private Bill bill;
    private Integer number;
    public BillDetailDto() {
    }
}