$(document).ready(function(){
	loginuser();
	gotosignupfunction();
});
var lname;
function loginuser()
{
	$("#login").click(function(){
		var formData=new FormData();
		formData.append("name",$("#username").val());
		formData.append("password",$("#password").val());
		$.ajax({
		url:"/admin/checklogin",
		method: "POST",
		processData: false,
		contentType: false,
		data:formData,
			success:function(data){				
				localStorage.setItem("name",data.name);
				localStorage.setItem("city",data.city);
				localStorage.setItem("usertype",data.userType);
				localStorage.setItem("email",data.email);
				localStorage.setItem("id",data.id);		
				
				//localStorage.setItem("userPic",data)
				console.log(data);
				console.log(data.message);
				if(data.massage=="true"){
				   $.ajax({
					url:"/admin/getuserpic?id="+data.id,
					method: "GET",
					processData: false,
					contentType: false,
					success:function(response)
					{
						if(response!=null)
						{
							var blob = new Blob([data.imageData], { type: 'image/pnj' });
        		var imageUrl = URL.createObjectURL(blob);
        		localStorage.setItem("imageData",imageUrl);
        		localStorage.setItem("imageData1",data.imageData);
						window.location.href="Home.html";
						}else{
							window.location.reload();
						}
					}
					});
			   
   
				
					
				}
				else
				{
					alert("login failed");
					window.location.reload();
				}				
			}
		});
	});
}
function gotosignupfunction()
{
	$(".gotosignup").click(function(){
		window.location.href="User.html";
	});
}
