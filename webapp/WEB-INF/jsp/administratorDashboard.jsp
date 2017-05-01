<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/WEB-INF/jsp/header.jsp" />



<div class="container">
	<h1 class="page-header admin-dash">Admin Dashboard</h1>
	<div class="row">

		<div class="row placeholders">
			<div class="col-md-4">
				<c:url var="createStudent" value="/createStudent" />
				<a href="${createStudent}"> <img id="studentflip" src="img/student2.png" width="300"
					height="300" class="img-responsive"
					alt="Generic placeholder thumbnail" />

				</a>
				<h4>
					<a href="${createStudent}">Create Student</a>
				</h4>
			</div>
			<div class="col-md-4">
				<c:url var="createEmployer" value="/createEmployer" />
				<a href="${createEmployer}"> <img id="employerflip" src="img/employer3.png"
					width="300" height="300" class="img-responsive"
					alt="Generic placeholder thumbnail" />

				</a>

				<h4>
					<a href="${createEmployer}">Create Employer</a>
				</h4>
			</div>
			<div class="col-md-4">
				<c:url var="createAdmin" value="/createAdmin" />
				<a href="${createAdmin}"> <img id="adminflip" src="img/admin.png"
					width="300" height="300" class="img-responsive"
					alt="Generic placeholder thumbnail" />

				</a>

				<h4>
					<a href="${createAdmin}">Create Admin</a>
				</h4>
			</div>
		</div>


	</div>
</div>

<c:import url="/WEB-INF/jsp/footer.jsp" />