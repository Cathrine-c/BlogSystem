package org.example.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * http响应JSON数据前后端统一约定的JSON格式
 * 响应撞状态码都是200，借用ajax的success来使用
 * {success:true,data:xxx}
 * {success:false,code:xxx,message:xxx}
 */


@Getter
@Setter
@ToString

public class JSONResponse {

    //业务操作是否成功
    private boolean success;
    //业务操作消息码，一般来说，出现错误才有意义
    private String code;
    //业务操作的错误消息:给用户看的信息
    private String message;
    //业务数据：业务操作成功时，给前端ajax success方法使用。解析响应json数据，渲染网页
    private Object data;


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}
