var imageUrl1=localStorage.getItem("imageData1");
 $(document).ready(function() { 
	 $(".profilepicdiv").attr("src",'data:image/png;base64,'+imageUrl1);
	filterproductbyproductname();
	filterproductbybrandname();
	addtocartfunction();
	gotomyorderpage();
	$("#profilemodal").hide();
	 $('#excelForm').submit(function (event) {
        

        var formData = new FormData();
        formData.append('excelFile', $('#excelFile').val());

        $.ajax({
            url: '/product/importproductdetailsfromexcel', // Replace with your endpoint
            type: 'POST',
            data: formData,
            contentType: false,
            processData: false,
            success: function (response) {				
                $('#result').html(response);
            },
            error: function (xhr, status, error) {
                console.error(error);
                $('#result').html('An error occurred during import.');
            }
        });
    });
	//end
	$("#pname").val(localStorage.getItem("name"));
	$("#pcity").val(localStorage.getItem("city"));
	//$(".modalshowbutton, .profileviewbutton, .productdetailsbutton").hide();
	$("#proletype").val(localStorage.getItem("usertype"));
	$("#pemail").val(localStorage.getItem("email"));
	//$(".modalshowbutton").hide();
	$("#profilemodel").hide(); 
	$("#myModal").hide();
   $('.toggle-nav').click(function() {  
     $this = $(this);  
     $nav = $('.nice-nav');  
     $nav.toggleClass('open');  
   });  
   $('.body-part').click(function(){  
     $nav.addClass('open');  
   });  
   $submenu = $('.child-menu-ul');  
   $('.child-menu .toggle-right').on('click', function(e) {  
     e.preventDefault();  
     $this = $(this);  
     $parent = $this.parent().next();  
     $tar = $('.child-menu-ul');  
     if (!$parent.hasClass('active')) {  
       $tar.removeClass('active').slideUp('fast');  
       $parent.addClass('active').slideDown('fast');  
     } else {  
       $parent.removeClass('active').slideUp('fast');  
     }  
   });  
   var username=localStorage.getItem("name");
   $("#username").text(username);
   //showdropdown();
   saveuser();
   phonenumbercheck();
   showprofile();
   productupload();
   myprivateproject();
   logoutfunction();
   showuserdetails();
   gotocartpage();
   //showingonlyfilterproducts();
	
	
	showprofilepic();
 });  
 function showprofilepic(){
	var htmlsstr='<img src="'+imageUrl1+'" alt="null"></img>';
	$(".profilepicshowdiv").html(htmlsstr);
}

function saveuser(){
	$("#submit").click(function(){
		var file=$("#userpic");
		console.log(file);
		var userDto = new FormData();
		userDto.append("name",$("#name").val());
		userDto.append("city",$("#city").val());
		userDto.append("userType",$("#usertype").val());
		userDto.append("phone",$("#phonenum").val());
		userDto.append("email",$("#email").val());
		userDto.append("password",$("#pass").val());
		userDto.append("imageData",$("#userpic"));
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
				window.location.reload();
			}else
			{
				alert(data);
			}
		}		
	});
	});
	
}
function phonenumbercheck()
{
	$("#phone").on("keydown", function(event) {
		const key = event.keyCode || event.which;
		const inputValue = $(this).val();
		if (key >= 48 && key <= 57 || key === 8) {
			if (inputValue.length >= 10 && key !== 8) {
				event.preventDefault();
			}
		} else {
			event.preventDefault();
		}
	});
}
function showprofile()
{
	/*$("#viewprofile").click(function(){
		$("#profilemodel").show();
	});*/
	
}
function myprivateproject() {
	$.ajax({
		url: "/product/showallproduct",
		success: function(data) {
			var html = "";
			if (data != null) {
				
				for (var i = 0; i < data.length; i++) {
					var product = data[i];

					var phone = {						
						name: product.name,
						storage: product.storage,
						cameraFront: product.frentCamara,
						cameraRear: product.backCamara,
						display: product.display,
						color: product.color,
						rate: product.rate,
						ram: product.ram,
						picture:product.productPic
					};
						html += '<div style="background-color:#f0f7f2; width:200px; height:250px;"><div style="width:99%; background-color:white;height:50%; margin:1px;"><img src="'+phone.picture+'" style="width:100%; height:100%;"></div><div class="details"><label>' + phone.name +" "+ '</label><label>' + phone.ram +"GB "+ '</label><label>' + phone.storage +"GB "+ '</label><label>' + phone.cameraFront +"mp "+ '</label><label>' + phone.cameraRear +"mp "+ '</label><label>' + phone.display +"inch "+ '</label><label>' + phone.color +" "+ '</label><label>' +" Rs:"+ phone.rate +"/- "+ '</label></div><div class="buyactions"><div><button type="button" class="btn btn-success" style="width:98%; margin:1px;">Buy</button></div><div><button type="button" class="btn btn-primary addtocart" style="width:98%; margin:1px;" id="addtocart" value="'+product.id+'">Add to cart</div></div></div>';

			
				}
					$(".product-thmnail").append(html);
			
			}
		}
	});
}
function productupload()
{
	$("#saveproduct").click(function(){
		var data=new FormData();
		data.append("name",$("#productName").val());
		data.append("rate",$("#productPrice").val());
		data.append("battery",$("#productBattery").val());
		data.append("color",$("#productColor").val());
		data.append("ram",$("#productRam").val());
		data.append("storage",$("#productStorage").val());
		data.append("frentCamara",$("#productFrontCam").val());
		data.append("backCamara",$("#productRearCam").val());
		data.append("processer",$("#processer").val());
		data.append("display",$("#display").val());
		data.append("productPic",$("#ProductPic").val());
		data.append("brand",$("#brand").val());
		data.append("product",$("#product").val());
		console.log("data "+data);		
		$.ajax({
			url:"/product/saveproduct",	
			method: "POST",
			processData: false,
			contentType: false,
			data:data,
			success:function(data){
				if(data.message=="SUCCESS")
				{
					alert("product uploaded...");
					window.location.reload();
				}
				else
				{
					alert("product upload failed...");
				}
				
			}
		});
	});	
}
function logoutfunction(){
	$(document).on('change','#dropdown3',function(){
		var value=$(this).val();
		if(value=="Logout"){
			localStorage.clear();
		window.location.href="Login.html";
		}else if(value==="Account")
		{
			window.location.href="Myprofile.html";
		}else if(value=="Change Password")
		{	window.location.href="Changepassword.html";
		}
	});
}
function showuserdetails()
{
	
	
		
		$(".showallusers").click(function(){
				/*$.ajax({
			url:"/admin/showall",	
			method: "GET",
			processData: false,
			contentType: false,
			success:function(data)
			{
				if(data!=null)
				{
					for(var i=0;i<data.length;i++){
						var table=jQuery("#userdetails").DataTable({
							colomn:[
								{
									data:"Name"
								},{
									data:"UserType"
								}
							]
						});
					}
				}else
				{
					alert("data is not available");
				}
			}
		});*/
		//$(".showallusers").hide();
		/*	$(".userdetails").text("its working");
			$.ajax({
			url:"/admin/showall",	
			method: "GET",
			processData: false,
			contentType: false,
			success:function(data)
			{
				if(data!=null)
				{*/
					var	 exampacktbl = jQuery("#tableManageTag").DataTable({
		        "dom": '<"listtable"fit>pl',        
		        "responsive" : true,
				"autoWidth" : false,
				"scrollX" : true,
		        "Paging": false,
		        "Searching": false,
		        "destroy": true,
		        "oLanguage": {
		            "sEmptyTable":     "No Records Found",
		            "sInfo":           "Showing _START_ to _END_ of _TOTAL_ entries",
		            "sInfoEmpty":      "Showing 0 to 0 of 0 entries",
		            "sInfoFiltered":   "(filtered from _MAX_ total entries)",
		            "sInfoPostFix":    "",
		            "sInfoThousands":  ",",
		            "sLengthMenu":     "Show _MENU_ entries",
		            "sLoadingRecords": "Loading...",
		            "sProcessing":     "Processing...",
		            "sSearch":         "",
		            "sZeroRecords":    "No Records Found",
		           
		            "oPaginate": {
		                "sFirst":    "First",
		                "sLast":     "Last",
		                "sNext":     "Next",
		                "sPrevious": "Previous"
		            }
		        },
		       
		        "ajax": {
		            "url": "/product/showallusers",
		            "dataSrc": ""
		          },
	            "columns": [
								{
	                        	  "data":"name",
	                        	  "className":"name"
	                          },
	                          {
	                        	  "data":"city",
	                        	  "className":"city"
	                          },
	                          {
	                        	  "data":"email",
	                        	  "className":"email"
	                          },
	                           {
	                        	  "data":"phone",
	                        	  "className":"phone"
	                          },
	                           {
	                        	  "data":"userType",
	                        	  "className":"userType"
	                          }
	                          
	                        ],
		        "pageLength": 10,
		        "order": [
		            [ 0, "asc" ]
		        ],
		        "lengthMenu": [
		            [10, 25, 50, -1],
		            [10, 25, 50, "All"]
		        ],
		        "aoColumnDefs": [
					  {
					     bSortable: false,
					     aTargets: [ -1 ]
					  }
					],
					"drawCallback": function () {
				        $('.dataTables_paginate .paginate_button').attr('tabindex', '-1');
				    }
		        
		    });
		    
		    jQuery(".dataTables_filter input").attr("placeholder",
	"Enter search term...");
			/*	}
			}
			});*/
		});	
	
}
//put product ex phone ,laptop like this
function filterproductbyproductname()
{
	$("#dropdown3").change(function(){
		var products=$(this).val();
		$.ajax({
			url:"/product/showproductbyproductname?product="+products,
			method: "POST",
			processData: false,
			contentType: false,	
			dataType: 'json',				
			success:function(data)
			{
				if(data !=null && data!="" )
				{
					var html = "";
					$(".product-thmnail").removeClass("product-thmnail");
					for (var i = 0; i < data.length; i++) {
					var product = data[i];

					var phone = {						
						name: product.name,
						storage: product.storage,
						cameraFront: product.frentCamara,
						cameraRear: product.backCamara,
						display: product.display,
						color: product.color,
						rate: product.rate,
						ram: product.ram,
						picture:product.productPic
					};
						html += '<div style="background-color:#f0f7f2; width:200px; height:250px;"><div style="width:99%; background-color:white;height:50%; margin:1px;"><img src="'+phone.picture+'" style="width:100%; height:100%;"></div><div class="details"><label>' + phone.name +" "+ '</label><label>' + phone.ram +"GB "+ '</label><label>' + phone.storage +"GB "+ '</label><label>' + phone.cameraFront +"mp "+ '</label><label>' + phone.cameraRear +"mp "+ '</label><label>' + phone.display +"inch "+ '</label><label>' + phone.color +" "+ '</label><label>' +" Rs:"+ phone.rate +"/- "+ '</label></div><div class="buyactions"><div><button type="button" class="btn btn-success" style="width:98%; margin:1px;">Buy</button></div><div><button type="button" class="btn btn-primary addtocart" style="width:98%; margin:1px;" id="addtocart" value="'+product.id+'">Add to cart</div></div></div>';

			
				}
					$(".product-thmnail").append(html);
					
				}	
				
			}
			
		});
	});
}
//put brand name like Redmi Realme like this
function filterproductbybrandname()
{
	$("#dropdown4").change(function(){
		var brandname=$(this).val();
		$.ajax({
			url:"/product/showproductbybrandname?brand="+brandname,
			method: "POST",
			processData: false,
			contentType: false,	
			dataType: 'json',	
			success:function(data)
			{
				var html = "";
				if(data!=null && data!="")
				{
					$(".product-thmnail").removeClass("product-thmnail");
					for (var i = 0; i < data.length; i++) {
					var product = data[i];

					var phone = {
						name: product.name,
						storage: product.storage,
						cameraFront: product.frentCamara,
						cameraRear: product.backCamara,
						display: product.display,
						color: product.color,
						rate: product.rate,
						ram: product.ram,
						picture:product.productPic
					};
						html += '<div style="background-color:#f0f7f2; width:200px; height:250px;"><div style="width:99%; background-color:white;height:50%; margin:1px;"><img src="'+phone.picture+'" style="width:100%; height:100%;"></div><div class="details"><label>' + phone.name +" "+ '</label><label>' + phone.ram +"GB "+ '</label><label>' + phone.storage +"GB "+ '</label><label>' + phone.cameraFront +"mp "+ '</label><label>' + phone.cameraRear +"mp "+ '</label><label>' + phone.display +"inch "+ '</label><label>' + phone.color +" "+ '</label><label>' +" Rs:"+ phone.rate +"/- "+ '</label></div><div class="buyactions"><div><button type="button" class="btn btn-success" style="width:98%; margin:1px;">Buy</button></div><div><button type="button" class="btn btn-primary addtocart" style="width:98%; margin:1px;" id="addtocart" value="'+product.id+'">Add to cart</div></div></div>';

			
				}
					$(".product-thmnail").append(html);
					
				}	
				
			}
			
		});
	});
}
function loadTagsTable(){
	

	
	
	
}
/*function showingonlyfilterproducts()
{
		$("#dropdown3").change(function(){
			var products=$(this).val();
			$("#dropdown4").change(function(){
				var brandname=$(this).val();
				var product=new FormData();
				product.append("brand",brandname);
				product.append("product",products);
						$.ajax({
			url:"/product/getwhatproductyouwant,
			method: "POST",
			processData: false,
			contentType: false,	
			dataType: 'json',	
			success:function(data)
			{
				var html = "";
				if(data!=null)
				{
					$(".product-thmnail").empty();
					for (var i = 0; i < data.length; i++) {
					var product = data[i];

					var phone = {
						name: product.name,
						storage: product.storage,
						cameraFront: product.frentCamara,
						cameraRear: product.backCamara,
						display: product.display,
						color: product.color,
						rate: product.rate,
						ram: product.ram,
						picture:product.productPic
					};
						html += '<div style="background-color:#f0f7f2; width:200px; height:250px;"><div style="width:99%; background-color:white;height:50%; margin:1px;"><img src="'+phone.picture+'" style="width:100%; height:100%;"></div><div class="details"><label>' + phone.name +" "+ '</label><label>' + phone.ram +"GB "+ '</label><label>' + phone.storage +"GB "+ '</label><label>' + phone.cameraFront +"mp "+ '</label><label>' + phone.cameraRear +"mp "+ '</label><label>' + phone.display +"inch "+ '</label><label>' + phone.color +" "+ '</label><label>' +" Rs:"+ phone.rate +"/- "+ '</label></div><div class="buyactions"><div><button type="button" class="btn btn-success" style="width:98%; margin:1px;">Buy</button></div><div><button type="button" class="btn btn-primary addtocart" style="width:98%; margin:1px;" id="addtocart" value="'+product+'">Add to cart</div></div></div>';

			
				}
					$(".product-thmnail").append(html);
					
				}	
				
			}
			
		});
				
			});
		});
	
	
	
}
*/
function gotocartpage()
{
	$("#addcart").click(function(){
		window.location.href="Cart.html";
	});
}

function addtocartfunction()
{

	$(document).on('click', '.addtocart', function() {
    var prodtid = $(this).val();  
    var id=localStorage.getItem("id");
    console.log("value "+prodtid); 
	$.ajax({
		url:"/product/findproductavailornot?productid="+prodtid,
		method: "POST",
		processData: false,
		contentType: false,	
		dataType: 'json',
		success:function(data)
		{
			if(data==true)
			{
				alert("The product is already added to cart ");
			}else
			{
				 	$.ajax({
			url:"/product/addtocartproduct?productid="+prodtid+"&id="+id,
			method: "POST",
			processData: false,
			contentType: false,	
			dataType: 'json',
			success:function(data)
			{
				if(data==true)
				{
					alert("Added successfully.");
				}else
				{
					alert("Added failed.");
				}

			}
		});
			}

		}
	});

});
  
   
	

}
function gotomyorderpage()
{
	$("#myorder").click(function(){
		window.location.href="Myorder.html";
	});
}