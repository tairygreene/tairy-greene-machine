
$(document).ready(function () {


				$("#flipper1").mouseover(function() {
					$(this).attr("src", "brainflip.png");
				});
				
				$("#flipper1").mouseout(function() {
					$(this).attr("src", "img/57267.jpg");
				});
				
				$("#flipper2").mouseover(function() {
					$(this).attr("src", "01flip.png");
				});
				
				$("#flipper2").mouseout(function() {
					$(this).attr("src", "img/transmission.jpg");
				});
				
				$("#flipper3").mouseover(function() {
					$(this).attr("src", "keyboardflip.png");
				});
				
				$("#flipper3").mouseout(function() {
					$(this).attr("src", "img/ultra-thin-zaggkeys-pro-plus-apple-ipad-keyboard-offers-seven-unique-backlight-colors.jpg");
				});
				
				$("#flipper4").mouseover(function() {
					$(this).attr("src", "powerflip.png");
				});
				
				$("#flipper4").mouseout(function() {
					$(this).attr("src", "img/ruelj2.jpg");
				});
				
				
				
				
				$("#adminflip").mouseover(function() {
					$(this).attr("src", "img/adminflip.png");
				});
				
				$("#adminflip").mouseout(function() {
					$(this).attr("src", "img/admin.png");
				});
				
				$("#studentflip").mouseover(function() {
					$(this).attr("src", "img/student2flip.png");
				});
				
				$("#studentflip").mouseout(function() {
					$(this).attr("src", "img/student2.png");
				});
				
				$("#employerflip").mouseover(function() {
					$(this).attr("src", "img/employer3flip.png");
				});
				
				$("#employerflip").mouseout(function() {
					$(this).attr("src", "img/employer3.png");
				});
				
				
				$("#searchflip").mouseover(function() {
					$(this).attr("src", "img/searchflip.png");
				});
				
				$("#searchflip").mouseout(function() {
					$(this).attr("src", "img/search.jpg");
				});
				
				$("#updateProfileForm").validate({
					rules : {
						PhoneNumber : {
							required : true,
							phoneUS : true,
						},
						firstName : {
							required : true,
							lettersonly : true,
						},
						lastName : {
							required : true,
							lettersonly : true,
						},
						email : {
							required : true,
							email : true,
						},

					},
					messages : {
						PhoneNumber : {
							phoneUS : "Please enter a 10 digit US phone number",
						},
						firstName : {
							required : "This field is required",
							lettersonly : "Please enter letters only",
						},
						lastName : {
							required : "This field is required",
							lettersonly : "Please enter letters only",
						},
						email : {
							required : "This field is required",
							email : "Please enter a valid email address",
						},
					},
					errorClass : "field-validation-error"
				});
				
				$("#adminEmployerRegistration").validate({
					rules : {
						employerName : {
							required : true,
							lettersonly : true,
						},
						email : {
							required : true,
							email : true,
						},

					},
					messages : {
						employerName : {
							required : "This field is required",
							lettersonly : "Please enter letters only",
						},
						email : {
							required : "This field is required",
							email : "Please enter a valid email address",
						},
					},
					errorClass : "field-validation-error"
				});

				$("#adminStudentAdminRegistration").validate({
					rules : {
						firstName : {
							required : true,
							lettersonly : true,
						},
						lastName : {
							required : true,
							lettersonly : true,						},
						email : {
							required : true,
							email : true,
						},

					},
					messages : {
						firstName : {
							required : "This field is required",
							lettersonly : "Please enter letters only",
						},
						lastName : {
							required : "This field is required",
							lettersonly : "Please enter letters only",
						},
						email : {
							required : "This field is required",
							email : "Please enter a valid email address",
						},
					},
					errorClass : "field-validation-error"
				});
});