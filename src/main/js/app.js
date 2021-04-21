//页面加载完成以后执行function代码
$(function () {
    //jquery，使用$("#id")通过元素id获取某个页面元素
    $("#login_form").submit(function () {

        //发送ajax请求
        $.ajax({
            uri : "../login",//请求路径
            type : "POST",//请求方法
            //contentType : "",//请求数据格式：默认表单的格式，json需要指定为json
            data : $("#login_form").serialize(),//请求数据：序列化表单的数据
            dataType : "json",//响应的数据类型：使用json需要指定
            success : function (r) {//回调函数，r是后端响应的数据
                if (r.success){

                    //操作成功,跳转
                    window.location.href = "../jsp/articleList.jsp";

                }else{

                    //操作失败
                    alert("错误码：" + r.code + "\n错误信息：" + r.message);

                }

            }
        });
        //统一不执行默认的表单提交
        return false;
    });

});