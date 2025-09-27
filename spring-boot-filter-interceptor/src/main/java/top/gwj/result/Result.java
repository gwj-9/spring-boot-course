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
        return result;
    }

    public Result<T> data(T data) {
        this.data = data;
        return this;
    }
}
//public static <T> Result<T> ok(T data) {
//    return new Result<>(200, "操作成功", data);
//}