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

		<!-- Sign up form -->
		<section class="signup">
			<div class="container">
				<div class="signup-content">
					<div class="signup-form">
						<h2 class="form-title">Sign up</h2>

						<%-- Check if validation errors exist --%>
						<c:if test="${not empty validationErrors}">
						<div class="error-messages" id="error-container">
								<h3>Please correct the following errors:</h3>
								<ul>
									<c:forEach items="${validationErrors}" var="error">
										<li>${error}</li>
									</c:forEach>
								</ul>
							</div>
						</c:if>

						<c:if test="${not empty message}">
							<p>${message}</p>
						</c:if>


						<form  method="post" action="<c:url value='/auth/Signup'/>" class="register-form"
							id="register-form">
							<div class="form-group">
								<label for="firstName"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <input
									type="text" name="firstName" id="firstName" placeholder="Your firstName" />
							</div>
							<div class="form-group">
								<label for="lastname"><i
										class="zmdi zmdi-account material-icons-name"></i></label> <input
									type="text" name="lastName" id="lastname" placeholder="Your lastName" />
							</div>
							<div class="form-group">
								<label for="email"><i class="zmdi zmdi-email"></i></label> <input
									type="email" name="email" id="email" placeholder="Your Email" />
							</div>
							<div class="form-group">
								<label for="pass"><i class="zmdi zmdi-lock"></i></label> <input
									type="password" name="pass" id="pass" placeholder="Password" />
							</div>

							<div class="form-group">
								<input type="checkbox"id="agree-term"
									class="agree-term" /> <label for="agree-term"
									class="label-agree-term"><span><span></span></span>I
									agree all statements in <a href="#" class="term-service">Terms
										of service</a></label>
							</div>
							<div class="form-group form-button">
								<input type="submit" name="signup" id="signup"
									class="form-submit" value="Register" />
							</div>
						</form>
					</div>
					<div class="signup-image">
						<figure>
							<img src="${pageContext.request.contextPath}/images/signup-image.jpg" alt="sing up image">
						</figure>
						<a href="${pageContext.request.contextPath}/login.jsp" class="signup-image-link">I am already
							member</a>
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