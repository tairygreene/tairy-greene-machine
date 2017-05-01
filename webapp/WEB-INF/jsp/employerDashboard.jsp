<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/WEB-INF/jsp/header.jsp" />

<div class="container">
	<h1 class="page-header">Employer Dashboard</h1>
	<div class="row">

		<div class="row placeholders">
			<div class="col-md-4">
				<c:url var="searchStudentProfiles" value="/searchStudent" />
				<a href="${searchStudentProfiles}"> <img id ="searchflip" src="img/search.jpg"
					width="300" height="300" class="img-responsive"
					alt="Generic placeholder thumbnail" />

				</a>
				<h4 style="text-align: left">
					<a href="${searchStudentProfiles}">&nbspSearch Student Profiles</a>
				</h4>
			</div>
		</div>
		<div class="col-md-4"></div>
	</div>


</div>
</div>

<c:import url="/WEB-INF/jsp/footer.jsp" />
