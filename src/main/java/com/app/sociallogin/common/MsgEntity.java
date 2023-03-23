package com.app.sociallogin.common;

import lombok.Data;

@Data
public class MsgEntity {

    private String id;
    private Object result;

    public MsgEntity(String id, Object result) {
        this.id = id;
        this.result  = result;
    }
}
