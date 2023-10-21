<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Sign Up Form by Colorlib</title>

<!-- Font Icon -->
	<link rel="stylesheet" href="<c:url value="/fonts/material-icon/css/material-design-iconic-font.min.css"/>">
<!-- Main css -->
<link rel="stylesheet" href="<c:url value="/css/style.css"/>">
</head>
<body>

	<div class="main">

		<!-- Sing in  Form -->
		<section class="sign-in">
			<div class="container">
				<div class="signin-content">
					<div class="signin-image">
						<figure>
							<img src="${pageContext.request.contextPath}/images/signin-image.jpg" alt="sing up image">
						</figure>
						<a href="<c:url value='/registration.jsp' />" class="signup-image-link">Create an
							account</a>
					</div>

					<div class="signin-form">

						<c:if test="${not empty message}">
							<p>${message}</p>
						</c:if>

						<h2 class="form-title">Sign up</h2>
						<%-- Check if validation errors exist --%>
						<c:if test="${not empty validation}">
							<div class="error-messages" id="error-container">
								<h3>Please correct the following errors:</h3>
								<ul>
									<c:forEach items="${validation}" var="error">
										<li>${error}</li>
									</c:forEach>
								</ul>
							</div>
						</c:if>
						<form method="post" action="<c:url value='/auth/signin'/>" class="register-form"
							id="login-form">
							<c:if test="${not empty validationEmail}">
								<div class="error-messages" id="error-container">
								<p>${validationEmail}</p>
								</div>
							</c:if>
							<div class="form-group">
								<label for="email"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <input
									type="text" name="email" id="email"
									placeholder="Your email" />
							</div>
							<c:if test="${not empty validationPassword}">
								<div class="error-messages" id="error-container">
									<p>${validationPassword}</p>
								</div>
							</c:if>
							<div class="form-group">
								<label for="password"><i class="zmdi zmdi-lock"></i></label> <input
									type="password" name="password" id="password"
									placeholder="Password" />
							</div>
							<div class="form-group">
								<input type="checkbox" name="remember-me" id="remember-me"
									class="agree-term" /> <label for="remember-me"
									class="label-agree-term"><span><span></span></span>Remember
									me</label>
							</div>
							<div class="form-group form-button">
								<input type="submit" name="signin" id="signin"
									class="form-submit" value="Log in" />
							</div>
						</form>
						<div class="social-login">
							<span class="social-label">Or login with</span>
							<ul class="socials">
								<li><a href="#"><i
										class="display-flex-center zmdi zmdi-facebook"></i></a></li>
								<li><a href="#"><i
										class="display-flex-center zmdi zmdi-twitter"></i></a></li>
								<li><a href="#"><i
										class="display-flex-center zmdi zmdi-google"></i></a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</section>

	</div>

	<!-- JS -->
	<script src="${pageContext.request.contextPath}vendor/jquery/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/main.js"></script>
</body>
</html>