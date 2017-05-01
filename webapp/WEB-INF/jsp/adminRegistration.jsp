<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/header.jsp" />

<div class="container">

	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-4">
			<h1>Admin Registration</h1>
			<c:url var="formAction" value="/adminRegistration" />
			<form method="POST" action="${formAction}">
				<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}" /> <input
					type="hidden" name="code" value="${code}" />
				<div class="form-group">
					<label for="userName">User Name: </label> <input type="text"
						id="userName" name="userName" placeHolder="User Name"
						class="form-control" />
				</div>
				<div class="form-group">
					<label for="password">Password: </label> <input type="password"
						id="password" name="password" placeHolder="Password"
						class="form-control" />
				</div>

				<div class="form-group">
					<label for="confirmPassword">Retype Password: </label> <input
						type="password" id="confirmPassword" name="confirmPassword"
						placeHolder="Confirm Password" class="form-control" />
				</div>
				<button type="submit" class="btn btn-lg btn-success">Submit</button>
			</form>
		</div>
		<div class="col-md-4">
			<h3 style="text-align: center; color: yellow; font-weight: bold">Password Rules</h3>
			<ul>
				<li>Must be at least 10 characters long</li>
				<li>Must contain at least 3 of the following 4 types of
					characters:
					<ol>
						<li>Uppercase letter (A-Z)</li>
						<li>Lowercase letter (a-z)</li>
						<li>Number (0-9)</li>
						<li>A "special" character (#, $, space, etc)</li>
					</ol>
				</li>
				<li>No more than two identical characters in a row</li>
			</ul>
		</div>
		<div class="col-md-2"></div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {

		$("form").validate({
			rules : {
				password : {
					required : true,
					minlength : 10,
					maxlength : 128,
					complexPassword : true,
					noMoreThan2Duplicates : true
				},
				userName : {
					required : true
				},
				confirmPassword : {
					required : true,
					equalTo : "#password"
				}
			},
			messages : {
				confirmPassword : {
					equalTo : "Passwords do not match"
				}
			},
			errorClass : "error"
		});

	});
</script>

<c:import url="/WEB-INF/jsp/footer.jsp" />