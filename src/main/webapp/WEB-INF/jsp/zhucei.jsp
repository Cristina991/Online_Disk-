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
    <div id="loginbck_left"><img src="images/login/logo_left2.jpg" /></div>
    <div id="loginbck_main">
        <div class="adming_login_border">
            <div class="admin_input">
                <form action="userRegister" method="post" >

                    <ul class="admin_items">
                        <li>
                            <h3 align="center">
                                <label for="user">随存通注册</label>
                            </h3>
                            <div align="center"><label for="user"></label>
                                <input type="text" name="username" placeholder="请输入注册用户名" id="user" size="40" class="admin_input_style" />
                            </div></li>
                        <li>
                            <label for="pwd"></label>
                            <input type="password" name="password"  id="pwd"  placeholder="请输入注册密码" size="40" class="admin_input_style" />
                        </li>

                        <li>
                            <div align="center"><input type="submit" tabindex="3" value="注册" class="btn btn-primary" href="javascript:subRegister()"/>
                            </div></li>
                    </ul>
                </form>
            </div>
        </div>

    </div>
</div>
</body>
</html>