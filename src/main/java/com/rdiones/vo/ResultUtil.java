package com.rdiones.vo;


/**
 * Created by CANONYANG on 2018/6/11.
 */
public class ResultUtil {
    private ResultUtil() {

    }

    public static Result success(Object data) {
        Result result = new Result();
        result.setCode(0);
        result.setMsg("请求成功");
        result.setData(data);
        return result;
    }

    public static Result successWithMessage(String message) {
        Result result = new Result();
        result.setCode(0);
        result.setMsg(message);
        return result;
    }

    public static Result successWithMessage(String message, Object data) {
        Result result = new Result();
        result.setCode(0);
        result.setMsg(message);
        result.setData(data);
        return result;
    }

    public static Result success() {
        Result result = new Result();
        result.setCode(0);
        result.setMsg("请求成功");
        return result;
    }

    public static Result success(Object data, Integer count) {
        Result result = new Result();
        result.setCode(0);
        result.setMsg("请求成功");
        result.setData(data);
        result.setCount(count);
        return result;
    }

    public static Result error(String msg) {
        Result result = new Result();
        result.setCode(1);
        result.setMsg(msg);
        return result;
    }
}
