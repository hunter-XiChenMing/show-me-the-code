package com.starlib.model.response;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestResponse {
    private boolean status;
    private String resultCode;
    private String resultMsg;
    private Object extendData;
}
