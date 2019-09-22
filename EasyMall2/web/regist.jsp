<%--
  Created by IntelliJ IDEA.
  User: 魏蜀禹
  Date: 2019/9/10
  Time: 9:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>欢迎注册EasyMall</title>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="css/regist.css"/>
    <script type="text/javascript" src="js/jquery-1.4.2.js"></script>
    <script type="text/javascript">
        var formObj={
            "checkData":function () {
                var canSub=true;
                //检查各项是不是为真
                canSub=this.checkNull("username","用户名不能为空")&& canSub;
                canSub=this.checkNull("password","密码不能为空")&& canSub;
                canSub=this.checkNull("password2","确认密码不能为空")&& canSub;
                canSub=this.checkNull("nickname","昵称不能为空")&& canSub;
                canSub=this.checkNull("email","邮箱不能为空")&& canSub;
                canSub=this.checkNull("valistr","验证码不能为空")&& canSub;
                canSub=this.checkPassword()&& canSub;
                canSub=this.checkEmail()&& canSub;
                return canSub;
            },
            "checkNull":function (name,msg) {
                //获取name的值是不是对的
                var tag=$("input[name='"+name+"']").val();
                //非空
                $("input[name='"+name+"']").nextAll("span").text("");//重置为空的效果
                if (tag==""){
                    $("input[name='"+name+"']").nextAll("span").text(msg);
                    return false;
                }
                return  true;
            },
            "checkPassword":function () {
                //获取两个密码框中的数据
                var password=$("input[name='password']").val();
                var password2=$("input[name='password2']").val();
                if (password !=password2){
                    $("input[name='password2']").nextAll("span").text("两次密码不一致");
                    return false;
                }
                return true;
            },
            "checkEmail":function () {
                var reg=/\w+@\w+(\.\w+)+/;
                var email=$("input[name='email']").val();
                if (email!=null&&!reg.test(email)){
                    $("input[name='email']").nextAll("span").text("邮箱格式不正确");
                    return false;
                }
                return true;
            }
        };
        //文档就绪事件
        $(function () {
            $("input[name='username']").blur(function () {
                formObj.checkNull("username","用户名不能为空");
            });
            $("input[name='password']").blur(function () {
                formObj.checkNull("password","密码不能为空");
            });
            $("input[name='password2']").blur(function () {
                formObj.checkNull("password2","确认密码不能为空");
            });
            $("input[name='nickname']").blur(function () {
                formObj.checkNull("nickname","昵称不能为空");
            });
            $("input[name='email']").blur(function () {
                formObj.checkNull("email","邮箱不能为空");
            });
            $("input[name='valistr']").blur(function () {
                formObj.checkNull("valistr","验证码不能为空");
            });
            //鼠标离开输入框，则发生用户名提示事件
            $("input[name='username']").blur(function () {
                formObj.checkNull("username","用户名不能为空");
                //获取输入框中的用户名
                var username=$("input[name='username']").val();
                if (username!=""){
                    //用ajax来验证用户名
                    $("#username").load("<%=request.getContextPath()%>/AjaxCheckUserServlet",{"username":username})
                }
            });
            //密码不能相同
            $("input[name='password2']").blur(function () {
                var password=$("input[name='password']").val();
                var password2=$("input[name='password2']").val();
                if (password!=password2){
                    $("input[name='password2']").nextAll("span").text("两次密码不一致");
                }
            });
            //点击图片，切换码
            $("#img").click(function () {
                var date=new Date();
                var time=date.getTime();
                $(this).attr("src","<%=request.getContextPath()%>/ValidateServlet?time="+time);
            });
        });
    </script>
</head>
<body>
<form action="<%=request.getContextPath()%>/RegistServlet" method="POST" onsubmit="return formObj.checkData()">
    <h1>欢迎注册EasyMall</h1>
    <table>
        <tr>
            <td class="tds" colspan="2" style="color: red;text-align: center">
            <%=request.getAttribute("msg")==null?"":request.getAttribute("msg")%>
            </td>
        </tr>
        <tr>
            <td class="tds">用户名：</td>
            <td>
                <input type="text" name="username"/>
                <span id="username"></span>
            </td>
        </tr>
        <tr>
            <td class="tds">密码：</td>
            <td>
                <input type="password" name="password"/>
                <span id="password"></span>
            </td>
        </tr>
        <tr>
            <td class="tds">确认密码：</td>
            <td>
                <input type="password" name="password2"/>
                <span></span>
            </td>
        </tr>
        <tr>
            <td class="tds">昵称：</td>
            <td>
                <input type="text" name="nickname"/>
                <span id="nickname"></span>
            </td>
        </tr>
        <tr>
            <td class="tds">邮箱：</td>
            <td>
                <input type="text" name="email"/>
                <span id="email"></span>
            </td>
        </tr>
        <tr>
            <td class="tds">验证码：</td>
            <td>
                <input type="text" name="valistr"/>
                <img id="img" src="<%=request.getContextPath()%>/ValidateServlet" width="" height="" alt="" />
                <span ></span>
            </td>
        </tr>
        <tr>
            <td class="sub_td" colspan="2" class="tds">
                <input type="submit" value="注册用户"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>


