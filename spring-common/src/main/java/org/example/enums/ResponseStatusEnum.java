package org.example.enums;

import lombok.Getter;

/**
 * @author xym
 */

public enum ResponseStatusEnum {

    /**
     * 成功的标志
     */
    SUCCESS(200, "成功"),


    /**
     * 失败的标志
     */
    FAIL(500, "失败");

    @Getter
    private Integer code;

    @Getter
    private String message;

    ResponseStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}
