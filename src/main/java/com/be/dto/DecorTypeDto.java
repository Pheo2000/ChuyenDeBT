package com.be.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@ApiModel()
@Getter
@Setter
public class DecorTypeDto extends BaseDto {
    @Size(max = 255)
    private String name;
    @Size(max = 255)
    private String image;
    private Short status;

    public DecorTypeDto() {
    }
}