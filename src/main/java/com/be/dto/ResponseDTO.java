package com.be.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseDTO<T> {
    private boolean status;

    private String msg;

    private List<T> data;

    public ResponseDTO(boolean status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ResponseDTO(boolean status, String msg, List<T> data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
}
