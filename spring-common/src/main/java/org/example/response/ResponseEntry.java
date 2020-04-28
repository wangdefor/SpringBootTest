package org.example.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.enums.ResponseStatusEnum;

import java.io.Serializable;

/**
 * @ClassName ResponseEntry
 * @Description ResponseEntry
 * @Date 2020/4/28 9:33
 * @Author wangyong
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseEntry<T> implements Serializable {

    private String message;

    private Integer code;

    private T data;

    /**
     * 成功的返回码
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseEntry<T> ok(T data) {
        ResponseEntry responseEntry = ResponseEntry.builder()
                .code(ResponseStatusEnum.SUCCESS.getCode())
                .message(ResponseStatusEnum.SUCCESS.getMessage())
                .data(data)
                .build();
        return responseEntry;
    }

    /**
     * 失败的返回码
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseEntry<T> fail(T data) {
        ResponseEntry responseEntry = ResponseEntry.builder()
                .code(ResponseStatusEnum.FAIL.getCode())
                .message(ResponseStatusEnum.FAIL.getMessage())
                .data(data)
                .build();
        return responseEntry;
    }
}
