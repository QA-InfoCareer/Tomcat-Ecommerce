var originalpassword;
$(document).ready(function(){
	$("#usernamestatus").hide();
	$("#emailidstatus").hide();
	saveuser();
  	checkPasswords();
	usernameCheck();
	emailIdCheck();
});
function checkPasswords()
{
	$("#conformpass").focusout(function(){
			var conformpass=$("#conformpass").val();
	var pass=$("#pass").val();
	if(conformpass.length==pass.length)
	{
		if(conformpass==pass)
		{
			originalpassword=pass;
			
		}
		else
		{
			$("#passcheck").val("password mismatch");
		}
	}
	else
		{
			$("#passcheck").val("password mismatch");
		}
	});

}
function saveuser(){
	$("#submit").click(function(){
		var userDto = new FormData();
		userDto.append("name",$("#name").val());
		userDto.append("age",$("#age").val());
		userDto.append("city",$("#city").val());
		userDto.append("email",$("#email").val());
		userDto.append("password",originalpassword);
		userDto.append("userType","user");
		$.ajax({
		url:"/admin/saveuser",
		method: "POST",
		processData: false,
		contentType: false,
		data:userDto,
		success: function(data) {
			if(data.massage=="SUCCESS")
			{
				alert("account ccreated successfully...");
				window.location.href="Login.html";
			}else
			{
				alert(data);
			}
		}		
	});
	});
	
}
function usernameCheck()
{
	$("#name").focusout(function(){
		var uname=$("#name").val();
		$.ajax({
			url:'/admin/usernamecheck?uname='+uname,
			type:"post",
			contentType:false,
			processData:false,
			success:function(response)
			{
				var res=false;
				var htmlStr="";
				if(response===res)
				{					
					htmlStr+="The username is not available.";					
				}else{
					htmlStr+="The username is available.";
				}
				$("#usernamestatus").text(htmlStr);
				$("#usernamestatus").show();
			}
		});
	});
}

function emailIdCheck()
{
	$("#email").focusout(function(){
		var email=$("#email").val();
		$.ajax({
			url:'/admin/emailidcheck?email='+email,
			type:"post",
			contentType:false,
			processData:false,
			success:function(response)
			{
				var res=false;
				var htmlStr="";
				if(response===res)
				{
					htmlStr+="The email is not available.";
					$("#email").val("");
					$("#email").focus();
				}else{
					htmlStr+="The email is available.";
				}
				$("#emailidstatus").text(htmlStr);
				$("#emailidstatus").show();
			}
		});
	});
}