$(document).ready(function(){
	$("#myform").submit(function(){
		alert("hello");
		postCustomer();
		});
		
		function postCustomer(){
		
			var dataPost={
			userName: $("#userName").val(),
			password: $("#password").val(),
			active: $("#active").val(),
			role: $("#role").val(),
			
				}
		alert(dataPost.userName);
		$.ajax({
			url: '/saveUser',
			type: 'POST',
			contentType: 'application/json',
			data: JSON.stringify(dataPost),
			dataType: 'json',
			success : function(data){
				console.log(data);
			}
		});
					
		}
		

	
});