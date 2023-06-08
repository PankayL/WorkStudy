package com.database.pojo;

/**
 * @author pankay
 * @create 2022-12-06-18:38
 */
public class Result {
    private int code;   // 编码 200 / 400
    private String msg; // 成功 / 失败
    private int total; // 总记录数
    private Object data;    // 数据

    public static Result fail() {
        return new Result(400, "失败", 0, null);
    }

    public static Result suc() {
        return new Result(200, "成功", 0, null);
    }

    public static Result suc(Object data) {
        return new Result(200, "成功", 0, data);
    }

    public static Result suc(Object data, int total) {
        return new Result(200, "成功", total, data);
    }

    private static Result result(int code, String msg, int total, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setTotal(total);
        result.setData(data);

        return result;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", total=" + total +
                ", data=" + data +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Result(int code, String msg, int total, Object data) {
        this.code = code;
        this.msg = msg;
        this.total = total;
        this.data = data;
    }

    public Result() {
    }
}
