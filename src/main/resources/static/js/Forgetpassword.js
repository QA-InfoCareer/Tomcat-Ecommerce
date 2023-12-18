var email="mnaveenkumarm716@gmail.com";
$(document).ready(function(){
    sendOtp();
});
function sendOtp()
{    
    $(".submit").click(function () { 			
        $.ajax({
			url: '/admin/forgetpassword?=email'+email,
			type: 'POST',			
			success:function(){
                alert("OTP sent to your email id.");
            }
        });
        
    });
}