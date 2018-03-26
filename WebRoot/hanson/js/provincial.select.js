function clearProvicialSelect(){
	$("#selProvince option:first").prop("selected", 'selected');
	$("#selCity option").remove();
	$("#selCity").append('<option value="0">--请选择市/区--</option></select>');
	$("#selDistrict option").remove();
	$("#selDistrict").append('<option value="0">--请选择区/县--</option></select>');
	
}

function initProvicialSelect(provinceid, cityid, disid){
	$("#selProvince option").remove();
	$("#selProvince").append('<option value="0">--请选择省份--</option></select>');
	if(provinceid ==null ){
		$.each(province, function (k, p) { 
	        var option = "<option value='" + p.ProID + "'>" + p.ProName + "</option>";
	        $("#selProvince").append(option);
	    });
	}else{
		$.each(province, function (k, p) { 
			var option = "<option value='" + p.ProID + "'>" + p.ProName + "</option>";
	    	if(provinceid == p.ProID){
	    		 option = "<option value='" + p.ProID + "' selected>" + p.ProName + "</option>";
	    	}
	        $("#selProvince").append(option);
	    });
		
		
		if(cityid == null){
			$.each(city, function (k, p) { 
		        if (p.ProID == provinceid) {
		            var option = "<option value='" + p.CityID + "'>" + p.CityName + "</option>";
		            $("#selCity").append(option);
		        }
		    });
		}else {
			$.each(city, function (k, p) { 
		        if (p.ProID == provinceid) {
		            var option = "<option value='" + p.CityID + "'>" + p.CityName + "</option>";
		            if(p.CityID == cityid ){
		            	option = "<option value='" + p.CityID + "' selected>" + p.CityName + "</option>";
		            }
		            $("#selCity").append(option);
		        }
		    });
			
			if(disid == null){
				$.each(District, function (k, p) {
			        if (p.CityID == cityid) {
			            var option = "<option value='" + p.Id + "'>" + p.DisName + "</option>";
			            $("#selDistrict").append(option);
			        }
			    });
			}else {
				$.each(District, function (k, p) {
			        if (p.CityID == cityid) {
			            var option = "<option value='" + p.Id + "'>" + p.DisName + "</option>";
			            if(p.Id == disid){
			            	option = "<option value='" + p.Id + "' selected>" + p.DisName + "</option>";
			            }
			            $("#selDistrict").append(option);
			        }
			    });
			}
			
		}
		
	}
	
	$("#selProvince").change(function () {
	    var selValue = $(this).val(); 
	    $("#selCity option:gt(0)").remove();
	     
	    $.each(city, function (k, p) { 
	        if (p.ProID == selValue) {
	            var option = "<option value='" + p.CityID + "'>" + p.CityName + "</option>";
	            $("#selCity").append(option);
	        }
	    });
	     
	});
	 
	$("#selCity").change(function () {
	    var selValue = $(this).val();
	    $("#selDistrict option:gt(0)").remove(); 

	    $.each(District, function (k, p) {
	        if (p.CityID == selValue) {
	            var option = "<option value='" + p.Id + "'>" + p.DisName + "</option>";
	            $("#selDistrict").append(option);
	        }
	    }); 
	}); 
	
}


     
    
