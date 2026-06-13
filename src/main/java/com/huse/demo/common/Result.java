package com.huse.demo.common;


public class Result<T> { // [第2行] 定义一个通用的统一响应结果封装类，使用泛型 <T> 来兼容不同的数据类型
    private String code; // [第3行] 定义私有属性 code，用于存储状态码（例如："0" 表示成功，"-1" 表示失败）
    private String msg; // [第4行] 定义私有属性 msg，用于存储返回的提示信息（例如："成功" 或错误原因）
    private T data; // [第5行] 定义泛型属性 data，用于存储具体返回给前端的数据对象（如用户信息、列表等）

    public String getCode() { // [第6行] 获取状态码的公开方法
        return code; // [第7行] 返回当前对象的 code 值
    } // [第8行] getCode 方法结束

    public void setCode(String code) { // [第9行] 设置状态码的公开方法
        this.code = code; // [第10行] 将传入的状态码参数赋值给当前对象的 code 属性
    } // [第11行] setCode 方法结束

    public String getMsg() { // [第12行] 获取提示信息的公开方法
        return msg; // [第13行] 返回当前对象的 msg 值
    } // [第14行] getMsg 方法结束

    public void setMsg(String msg) { // [第15行] 设置提示信息的公开方法
        this.msg = msg; // [第16行] 将传入的提示信息参数赋值给当前对象的 msg 属性
    } // [第17行] setMsg 方法结束

    public T getData() { // [第18行] 获取返回数据的公开方法
        return data; // [第19行] 返回当前对象的 data 值
    } // [第20行] getData 方法结束

    public void setData(T data) { // [第21行] 设置返回数据的公开方法
        this.data = data; // [第22行] 将传入的数据参数赋值给当前对象的 data 属性
    } // [第23行] setData 方法结束

    public Result() { // [第24行] 定义无参构造方法，用于快捷实例化空对象
    } // [第25行] 无参构造方法结束

    public Result(T data) { // [第26行] 定义带有数据参数的构造方法
        this.data = data; // [第27行] 在创建对象的同时，直接将传入的数据赋值给 data 属性
    } // [第28行] 带参构造方法结束

    public static Result success() { // [第29行] 定义静态方法 success，用于返回不带具体数据的成功结果（如：删除成功）
        Result result = new Result<>(); // [第30行] 实例化一个全新的 Result 对象
        result.setCode("0"); // [第31行] 将状态码设置为成功标识 "0"
        result.setMsg("成功"); // [第32行] 将提示信息设置为 "成功"
        return result; // [第33行] 返回这个配置好的响应对象
    } // [第34行] success 无参静态方法结束

    public static <T> Result<T> success(T data) { // [第35行] 定义静态泛型方法 success，用于返回带有具体数据的成功结果（如：查询、登录成功）
        Result<T> result = new Result<>(data); // [第36行] 实例化一个将传入数据塞入其中的 Result 对象
        result.setCode("0"); // [第37行] 将状态码设置为成功标识 "0"
        result.setMsg("成功"); // [第38行] 将提示信息设置为 "成功"
        return result; // [第39行] 返回这个带有数据的响应对象
    } // [第40行] success 带参静态方法结束

    public static Result error(String code, String msg) { // [第41行] 定义静态方法 error，用于返回业务失败或系统错误的结果
        Result result = new Result(); // [第42行] 实例化一个全新的 Result 对象
        result.setCode(code); // [第43行] 将传入的自定义错误码（如 "-1"）赋值给对象的 code 属性
        result.setMsg(msg); // [第44行] 将传入的自定义错误提示（如 "密码错误"）赋值给对象的 msg 属性
        return result; // [第45行] 返回这个配置好的错误响应对象
    } // [第46行] error 静态方法结束
} // [第47行] Result 类结束