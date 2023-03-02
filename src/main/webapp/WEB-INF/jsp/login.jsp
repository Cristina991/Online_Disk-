<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>软通云-随存通</title>
    <link rel="stylesheet" type="text/css" href="css/admin_login.css"/>


</head>
<body>
<div class="admin_login_wrap">
    <div id="loginbck_top"><img src="images/login/logo_top.jpg" /></div>
    <div id="loginbck_left"><img src="images/login/logo_left.jpg" /></div>
    <div id="loginbck_main">
        <div class="adming_login_border">
            <div class="admin_input">
                <form action="userlogin" method="post">
                    <ul class="admin_items">
                        <li>
                            <h3 align="center">
                                <label for="user">随存通</label>
                            </h3>
                            <div align="center"><label for="user"></label>
                                <input type="text" name="username"  placeholder="请输入用户名" id="user" size="40" class="admin_input_style" />
                            </div></li>
                        <li>
                            <label for="pwd"></label>
                            <input type="password" name="password" placeholder="请输入密码" id="pwd" size="40" class="admin_input_style" />
                        </li>
                        <li><label for="pwd"></label>
                            <input type="checkbox" value="" id="chkpwd" size="40" /> &nbsp; &nbsp;记住密码
                        </li>
                        <li>
                            <div align="center"><input type="submit" tabindex="3" value="登录" class="btn btn-primary" />
                            </div></li>
                    </ul>
                </form>
            </div>
        </div>
        <div align="center"><p class="admin_copyright"><a tabindex="5" href="register" target="_blank">新用户注册</a> &nbsp; &nbsp; &nbsp; <a tabindex="5" href="#" target="_blank">忘记密码</a></p>

        </div>

    </div>
</div>
</body>
</html>