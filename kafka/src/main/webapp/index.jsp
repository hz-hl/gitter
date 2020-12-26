<%--
  Created by IntelliJ IDEA.
  User: 86166
  Date: 2020/12/9
  Time: 19:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>百知科技</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login2.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"></script>

    <script type="text/javascript">
        function openverification(){
            $.ajax({
                url: "${pageContext.request.contextPath}/Kafka/verification",
                data: $("input[name='username']").val(),
                type: "post",
                contentType: "application/json",
                dataType: "json",
                success: function(result){
                    if (result.success == "True") {
                        alert("发送成功")
                    }else {
                        alert("网络异常")
                    }
                }
            });
        }

        function openregister(){
            alert($("form").serialize())
            $.ajax({
                url: "${pageContext.request.contextPath}/Kafka/register",
                data: /*$("input[name='username']").val()*/$("form").serialize(),
                type: "post",
                contentType: "application/json",
                dataType: "json",
                success: function(result){
                    alert(result.register)
                    if (result.register == "True") {
                        alert("注册成功")
                    }else if(result.register == "TrueF") {
                        alert("用户名或密码错误")
                    } else {
                        alert("网络异常")
                    }
                }
            });
        }
    </script>


</head>

<body>

<div id="wrap" >

    <div id="header">
        <div class="logo">
            <img src="images/logo.png" width='200px'/>
        </div>
    </div>


    <div id="mainer">

        <div class="login_main">

            <div class="login_img"><img src="images/bg2.jpg" width='550px'></div>

            <div class="login_box">
                <div class="login_tit">百知科技用户注册</div>
                <form method="post" id="form1">
                    <div class="item">
                        <input name="username" type="text" id="txtUserName" class="input" placeholder="手机号">
                    </div>
                    <div class="item">
                        <input name="password" type="password" id="txtPassword" class="input" placeholder="密码">
                    </div>
                    <div class="item">
                        <input name="code" type="text" class="input2" placeholder="验证码"/>
                        <%--$("form").serialize()--%>
                        <input type="button" class="send" value="发送" onclick="openverification()"/>
                    </div>
                    <div class="item">
                        <input type="button" name="btnLogin" value="注册" id="btnLogin" class="btn" style="margin-top: 10px;" onclick="openregister()">
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div id="footer">
        <div class="footbox">
            <a href="">北京百知科技集团</a>
        </div>
    </div>

</div>

</body>

</html>