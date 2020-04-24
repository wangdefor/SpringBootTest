package org.example.common;

import org.example.enums.CodeStatus;

/**
 * @ClassName ResponseModel
 * @Description ResponseModel
 * @Date 2020/4/14 10:59
 * @Author wangyong
 * @Version 1.0
 **/
public class ResponseModel<T> {

    private Integer code;

    private String message;

    private T data;

    public ResponseModel(Integer code,String message){
        this(code,message,null);
    }

    public ResponseModel(){
        super();
    }

    public ResponseModel(Integer code,String message,T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResponseModel ok(T data){
        return new ResponseModel(CodeStatus.SUCCESS.getCode(),CodeStatus.SUCCESS.getMessage(),data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
