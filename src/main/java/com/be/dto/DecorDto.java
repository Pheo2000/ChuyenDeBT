package com.be.dto;

import com.be.common_api.DecorType;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@ApiModel()
@Getter
@Setter
public class DecorDto extends BaseDto {
    private Long idDecorType;
    private DecorType decorType;
    @Size(max = 255)
    private String name;
    @Size(max = 255)
    private String image;
    @Size(max = 255)
    private String descriptionDecor;
    private Integer price;
    private Integer quantity;
    private Short status;

    public DecorDto() {
    }
}