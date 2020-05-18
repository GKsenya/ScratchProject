<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html class="sign">
<head>
    <title>Log in/Sign up screen animation</title>
    <link rel="stylesheet" href="css/style.css">
</head>

<body class="sign">
<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,700' rel='stylesheet' type='text/css'>

<div class="container">
    <div class="frame">
        <div class="remodalBordersign">
            <div class="nav">
                <ul class="links">
                    <li class="signin-active"><a class="btn">Sign in</a></li>
                    <li class="signup-inactive"><a class="btn">Sign up</a></li>
                </ul>
            </div>
            <div ng-app ng-init="checked = false">
                <form class="form-signin" action="signIn" method="post" name="form">
                    <p class="message">${mes}</p>
                    <label>Username</label>
                    <input class="form-styling" type="text" name="login" placeholder="" value="${login}"/>
                    <label>Password</label>
                    <input class="form-styling" type="password" name="password" placeholder="" value="${password}"/>
                        <input type="submit" value="Войти" class="btn-sign">
                </form>


                <form class="form-signup" action="signUp" method="post" name="form">
                    <label>Name</label>
                    <input class="form-styling" type="text" name="name" placeholder="" value="${name}"/>
                    <p class="smessage">${nameMes}</p>
                    <label>Email(Username)</label>
                    <input class="form-styling" type="text" name="login" placeholder="" value="${login}"/>
                    <p class="smessage">${loginMes}</p>
                    <label>Password</label>
                    <input class="form-styling" type="password" name="password" placeholder="" value="${password}"/>
                    <p class="smessage">${passwordMes}</p>
                    <input type="submit" value="Зарегистрироваться" class="btn-sign">
                </form>

                <div class="success"></div>
            </div>
        </div>
    </div>

</div>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src='http://cdnjs.cloudflare.com/ajax/libs/angular.js/1.3.14/angular.min.js'></script>

<script src="js/index.js"></script>

</body>
</html>
