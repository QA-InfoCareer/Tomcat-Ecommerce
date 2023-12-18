$(document).ready(function() {
	gotohomepage();
	showmyorders();
});
function gotohomepage() {
	$("#back").click(function() {
		window.location.href = "Home.html";
	});
}
function showmyorders() {
	var id = localStorage.getItem("id");	
	$.ajax({
		url: "/myorders/myorder?id=" + id,
		method: "POST",
		processData: false,
		contentType: false,		
		success: function(data) {
			var htmlstr="";
			if(data!=null)
			{
                for(i=0;i<data.length;i++)
                {
                    var value=data[i];
				htmlstr+='<div style="width:175px; height:200px; border:1px solid #edfcf1;">';
                htmlstr+='<div style="width:80%;">';
                htmlstr+='<img src="'+value.productPic+'" alt="null">';
                htmlstr+='</div>';
                htmlstr+='<div style="width:20%;">';
                htmlstr+='<label>'+value.name+'</label><label>'+value.ram+""+value.storage+""+value.frentCamara+""+value.backCamara+""+value.processer+""+value.color+""+value.display+""+value.battery+'</label>';
                htmlstr+='</div>';
                htmlstr+='</div>';
				$(".layout").html(htmlstr);
                }
			}else{
				$(".layout").html("no record found");
			}
		}});
}