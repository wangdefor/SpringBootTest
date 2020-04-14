package org.example.enums;

import com.sun.org.apache.bcel.internal.classfile.Code;

public enum CodeStatus {

    SUCCESS(200,"成功");

    private Integer code;

    private String message;

    CodeStatus(Integer code,String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
