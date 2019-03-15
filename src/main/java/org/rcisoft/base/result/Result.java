package org.rcisoft.base.result;

import com.alibaba.fastjson.JSON;

/**
 * Created by JiChao on 2018/5/29.
 * 返回值
 */
public class Result {

    private Integer code;
    private String msg;
    private Object data;

    public Result() {

    }

    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public Result setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Result setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    /**
     * 返回值
     * 返回成功的情况
     * 适用于查询
     * @param data
     * @return
     */
    public static Result result(Object data) {
        return new Result(ResultCode.SUCCESS.code, MessageConstant.MESSAGE_ALERT_SUCCESS.msg, data);
    }

    /**
     * 返回值
     * 适用于增删改
     * @param operate 增删改的操作结果，大于0时，返回成功，小于0时，返回失败
     * @param data 结果集
     * @return
     */
    public static Result result(int operate, Object data) {
        Result result = new Result(ResultCode.SUCCESS.code, MessageConstant.MESSAGE_ALERT_SUCCESS.msg, data);
        if (operate <= 0) {
            result.setCode(ResultCode.FAIL.code).setMsg(MessageConstant.MESSAGE_ALERT_ERROR.msg);
        }
        return result;
    }

    /**
     * 返回值
     * 适用于增删改
     * @param operate 增删改的操作结果，大于0时，返回成功，小于0时，返回失败
     * @param success 成功的提示信息
     * @param error 失败的提示信息
     * @return
     */
    public static Result result(int operate, String success, String error) {
        Result result = new Result(ResultCode.SUCCESS.code, success, null);
        if (operate <= 0) {
            result.setCode(ResultCode.FAIL.code).setMsg(error);
        }
        return result;
    }

    /**
     * 返回值
     * 适用于增删改
     * @param operate 增删改的操作结果，大于0时，返回成功，小于0时，返回失败
     * @param success 成功的提示信息
     * @param error 失败的提示信息
     * @param data 结果集
     * @return
     */
    public static Result result(int operate, String success, String error, Object data) {
        Result result = new Result(ResultCode.SUCCESS.code, success, data);
        if (operate <= 0) {
            result.setCode(ResultCode.FAIL.code).setMsg(error);
        }
        return result;
    }

    /**
     * 自定义返回内容
     * @param code 返回值代码
     * @param msg 返回值提示信息
     * @param data 返回值内容
     * @return
     */
    public static Result result(ResultCode code, String msg, Object data) {
        return new Result(code.code, msg, data);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
