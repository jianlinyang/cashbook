package com.shu.cashbook.common;

import lombok.Data;

/**
 * @Author: yang
 * @Date: 2019/2/4 16:04
 * @Version 1.0
 */
@Data
public class BaseResult {

    private int result;
    private Object data;
    private String message;

    public static BaseResult success() {
        return createResult(200, null, "success");
    }

    public static BaseResult success(Object data) {
        return createResult(200, data, "success");
    }

    public static BaseResult success(String message) {
        return createResult(200, null, message);
    }

    public static BaseResult failed(int result, String message) {
        return createResult(result, null, message);
    }

    /**
     * @param result
     * @param data
     * @param message
     * @return
     */
    private static BaseResult createResult(int result, Object data, String message) {
        BaseResult baseResult = new BaseResult();
        baseResult.setResult(result);
        baseResult.setData(data);
        baseResult.setMessage(message);
        return baseResult;
    }

}
