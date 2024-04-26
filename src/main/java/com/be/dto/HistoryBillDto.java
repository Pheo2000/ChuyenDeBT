package com.be.dto;

import com.be.common_api.Bill;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@ApiModel()
@Getter
@Setter
public class HistoryBillDto extends BaseDto {
    private Long idBill;
    private Bill bill;
    private Short status;

    public HistoryBillDto() {
    }
}