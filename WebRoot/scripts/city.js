$(function (){
	var province = "";
	var city = "";
	var country = "";
	$.ajax({
		url:"../cityServlet",
		type:"post",
		data:{pid:1},
		dataType:"json",
		success:function (obj){ 
			for(var i = 0; i < obj.length; i++){
				var json = obj[i];
				$("#province").append("<option value='"+json.id+"'>"+json.cityname+"</option>");
			}
		}
	});
	
	$("#province").change(function (){
		$("#city").get(0).length = 1;
		$("#country").get(0).length = 1;
		var provinceId = $("#province option:selected").val();
		province = $("#province option:selected").text();
		if(provinceId != "-1"){
			$.ajax({
  				url:"../cityServlet",
  				type:"post",
  				data:{pid:provinceId},
  				dataType:"json",
  				success:function (obj){ 
  					for(var i = 0; i < obj.length; i++){
  						var json = obj[i];
  						$("#city").append("<option value='"+json.id+"'>"+json.cityname+"</option>");
  					}
  				}
  			});
  			$("#address").val(province);
		}else{
			$("#address").val("");
		}
	});
	
	$("#city").change(function (){
		$("#country").get(0).length = 1;
		//var cityId = $("#city option:selected").val();
		var cityId = $("#city").val();
		city = $("#city option:selected").text();
		if(cityId != "-1"){
			$.ajax({
  				url:"../cityServlet",
  				type:"post",
  				data:{pid:cityId},
  				dataType:"json",
  				success:function (obj){ 
  					for(var i = 0; i < obj.length; i++){
  						var json = obj[i];
  						$("#country").append("<option value='"+json.id+"'>"+json.cityname+"</option>");
  					}
  				}
  			});
  			$("#address").val(province + city);
		}else{
			$("#address").val(province);
		}
	});
	
	$("#country").change(function (){
		var countryId = $("#country").val();
		country = $("#country option:selected").text();
		if(countryId != "-1"){
			$("#address").val(province + city + country);
		}else{
			$("#address").val(province + city);
		}
	});
});