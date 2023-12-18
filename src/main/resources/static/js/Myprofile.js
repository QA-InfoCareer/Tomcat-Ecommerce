var imageUrl1 = localStorage.getItem("imageData1");
var id=localStorage.getItem("id");
var username=localStorage.getItem("name");
$(document).ready(function() {
	$("#username").text(username);
	$(".profile-pic").attr("src", 'data:image/png;base64,' + imageUrl1);
	updateprofilepicture();
});
function updateprofilepicture() {
	$(".add-icon").click(function() {
		var input = document.createElement('input');
		input.type = 'file';
		input.click();
		input.addEventListener('change', function(event) {
			var file = event.target.files[0];			
			if (file) {
				var formData = new FormData();
				formData.append('file', file);
				formData.append("id",id);
				$.ajax({
					url: '/admin/uploadimage',
					type: 'POST',
					data: formData,
					contentType: false, // Do not set any content type
					processData: false, // Do not process the data
					success: function(response) {
						alert("Profile picture uploaded successfully.");
						console.log('Image uploaded successfully:', response);
					},
					error: function(xhr, status, error) {

						console.error('Error uploading image:', error);
					}
				}); console.log('Selected file:', file.name);
			} else {
				console.log('No file selected');
			}
		});
	});
}