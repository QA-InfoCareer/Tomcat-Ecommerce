$(document).ready(function() {
	backbuttoncall();
	//orderplaced();
	showallreadytoorderproducts();
	removeproduct();
	myorder();
	cancleorder();
	conformorder();
	//$(".orderpayment").hide();
});
function backbuttoncall() {
	$("#backbutton").click(function() {
		window.location.href = "Home.html";
	});
}
/*function orderplaced()
{
	$("#orderbutton").click(function(){
		$.ajax({
		url:'/myorders/myorderdetails',
		method: "post",
		processData: false,
		contentType: false,	
		dataType: 'json',
		success:function(data){
			if(data!=null)
			{
				  alert("Order placed....");
			window.location.href="Home.html"; 
			}
			else{
				alert("Record not found");
			}
		}
		});
		    
	});
}*/
function showallreadytoorderproducts() {
	var id=localStorage.getItem("id");	
	$.ajax({
		url: '/cart/showallproductcart',
		method: "get",
		processData: false,
		contentType: false,
		dataType: 'json',
		success: function(data) {
			var htmlstr = '';
			if (data != null && data != "") {
				for (i = 0; i < data.length; i++) {
					var datas = data[i];
					htmlstr += '<tr style="border-top: 1px solid #000;">';
					htmlstr += '<td>' + datas.name + '</td>';
					htmlstr += '<td>' + datas.rate + '</td>';
					htmlstr += '<td><p value="' + datas.id + '" class="remove" style="cursor: pointer;">Remove</p></td>';
					htmlstr += '</tr>';
				}
				$('.productdetailsdontainer').html(htmlstr);
			} else {
				$('.productdetailsdontainer').html('no records foundd');
			}
		}
	});
}
function removeproduct() {
	$(document).on('click', '.remove', function() {
		var productid = $(this).attr('value');
		$.ajax({
			url: '/cart/removeproduct?productid=' + productid,
			method: 'post',
			processData: false,
			contentType: false,
			dataType: 'json',
			success: function(data) {
				if (data == true) {
					//alert("Product removed.");
					window.location.reload();
				} else {
					alert("Product not removed.");
				}
			}
		});
	});
}
function myorder() {
	$("#orderbutton").click(function() {
		$.ajax({
			url: '/myorders/orderplaced',
			method: 'post',
			processData: false,
			contentType: false,
			dataType: 'json',
			success: function(data) {
				console.log("datas " + data);
				if (data != null) {
					htmlstr = '';
					for (i = 0; i < data.length; i++) {
						var datas = data[i];
						htmlstr += '<tr>';
						htmlstr += '<td>' + datas.name + '</td>';
						htmlstr += '<td>' + datas.rate + '</td>';
						htmlstr += '</tr>';

					}
					$('.orderpaymentdetails').append(htmlstr);
					$.ajax({
						url: '/bill/showbillcopy',
						method: 'post',
						processData: false,
						contentType: false,
						dataType: 'json',
						success: function(response) {
							var htmlstr1 = '';
							htmlstr1 += '<tr>';
							htmlstr1 += '<td>' + "Productprice" + '</td>';
							htmlstr1 += '<td>' + response.productPrice + '</td>';
							htmlstr1 += '</tr>';
							htmlstr1 += '<tr>';
							htmlstr1 += '<td>' + "Gst" + '</td>';
							htmlstr1 += '<td>' + response.gst + '</td>';
							htmlstr1 += '</tr>';
							htmlstr1 += '<tr>';
							htmlstr1 += '<td>' + "Delivery Charge" + '</td>';
							htmlstr1 += '<td>' + response.deleveryCharge + '</td>';
							htmlstr1 += '</tr>';
							htmlstr1 += '<tr style="border-top: 1px solid #000;">';
							htmlstr1 += '<td>' + "Total Price" + '</td>';
							htmlstr1 += '<td>' + response.totalPrice + '</td>';
							htmlstr1 += '</tr>';
							$('.orderpaymentdetails').append(htmlstr1);
							$(".orderpayment").show();
						}
					});

				}

			}

		});
	});
}
function cancleorder() {
	$(".closebtn,#closebtn").click(function() {
		$.ajax({
			url: '/bill/cancleorder',
			method: 'post',
			processData: false,
			contentType: false,
			dataType: 'json',
			success: function(data) {
				if (data == true) {
					$('.orderpaymentdetails').empty();

				}
				window.location.reload();
			}
		});
	});
}
function conformorder() {
	$(".conformorder").click(function() {
		var id=localStorage.getItem("id");	
		$.ajax({
			url: '/soldproduct/ordercopytosoldproduct?id='+id,
			method: 'post',
			processData: false,
			contentType: false,
			dataType: 'json',
			success: function(data) {
				if (data == true) {
					$.ajax({
						url: '/cart/deleteallcart',
						method: 'post',
						processData: false,
						contentType: false,
						dataType: 'json',
						success: function(data) {
							if (data == true) {
								alert("Your order is placed.");
									
									window.location.reload();
								

							}else if (data == true){
								window.location.reload();
							}

						}
					});

				}
				
			}
		});
	});
}