package top.gwj.result;


import lombok.Data;

@Data
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result<>();
        result.code = 200;
        result.message = "success";
        result.data = data;
        return result;
    }

    public Result<T> data(T data) {
        this.data = data;
        return this;
    }

    public static <T> Result<T> error(String message) {
        Result<T> result = new Result<>();
        result.code = 400; // 假设错误码是 400
        result.message = message;
        return result;
    }


}
