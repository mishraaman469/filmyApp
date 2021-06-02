$(document).ready(function(){
	$("button").click(function(){
		postFilm();
	});
	

	
	function postFilm(){
		var name= $("#name").val();
		$.ajax({
			url: '/getByStockName/'+name,
			type: 'GET',
			success: function(stock){
				/*console.log(data);
				var jsonStr = JSON.stringify(data);
				document.getElementById("para").innerHTML = jsonStr;*/
				
				var stockRow='<tr>'+
								'<td>'+stock.id+'</td>'+
								'<td>'+stock.name+'</td>'+
								'<td>'+stock.price+'</td>'+
								'<td>'+stock.productType+'</td>'+
								'<td>'+stock.quantitys+'</td>'+
								'</tr>'
						$('#stockTable tbody').append(stockRow);	
				
				
			/*	$.each(data,function(index,director){
					'<tr>'+
					'<td>'+director.name+'</td>'
					+'</tr>'
				});*/
				
			},
			error: function(data){
				alert("Not Found");
			}
			
		});
	}
	
});