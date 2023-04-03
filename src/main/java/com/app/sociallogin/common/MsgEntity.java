package com.app.sociallogin.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MsgEntity {

    private String msg;
    private Object result;

    public MsgEntity(String msg, Object result) {
        this.msg = msg;
        this.result  = result;
    }
}
