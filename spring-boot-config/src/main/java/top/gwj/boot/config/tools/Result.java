package top.gwj.boot.config.tools;

public class Result<T> {
    private int code;
    private String msg;
    private T data;

    // 成功返回的静态方法
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.code = 200;  // 成功状态码
        result.msg = "success";
        result.data = data;
        return result;
    }

    // 可以添加失败返回的方法
    public static <T> Result<T> error(int code, String msg) {
        Result<T> result = new Result<>();
        result.code = code;
        result.msg = msg;
        return result;
    }

    // getter和setter方法
    public int getCode() { return code; }
    public void setCode(int code) { this.code = code; }
    public String getMsg() { return msg; }
    public void setMsg(String msg) { this.msg = msg; }
    public T getData() { return data; }
    public void setData(T data) { this.data = data; }
}