package com.be.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@ApiModel()
@Getter
@Setter
public class HistoryFindDto extends BaseDto {
    private Long idUser;
    private String valueText;
    public HistoryFindDto() {
    }
}