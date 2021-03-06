$(document).ready(function(){
	var searchParams = new URLSearchParams(window.location.search);
	if(searchParams.has("date")){
		$(".dateSelection").val(searchParams.get("date"));
	}else{
		var now = new Date();
		var day = ("0" + now.getDate()).slice(-2);
		var month = ("0" + (now.getMonth() + 1)).slice(-2);
	}
	
	if(searchParams.has("success")){
		$(".alert-success").removeClass('d-none');
	}
	
    $(".staySearch").keyup(function () {
        var filter = jQuery(this).val();
        jQuery(".stayOverview tr td:first-child").each(function () {
            if (jQuery(this).text().search(new RegExp(filter, "i")) < 0) {
                jQuery(this).parent().hide();
            } else {
                jQuery(this).parent().show();
            }
        });
    });

	$(".dateSelection").change(function() {
		var baseUrl = window.location.href.split("?")[0];
		$(location).attr('href',baseUrl+"?date="+$(this).val());
	});

	$(".checkOutBtn").change(function() {
		var getUrl = window.location;
		var baseUrl = getUrl .protocol + "//" + getUrl.host + "/";
		var checkbox = $(this);
		$.ajax({
			type: "POST",
			url: baseUrl+"api/stay/checkout?stayId="+checkbox.data("stayid"),
			dataType : "json"
		}).done(function( data ) {
		    if(data.status == "ok"){
				var baseUrl = window.location.href.split("?")[0];
				$(location).attr('href',baseUrl+"?success=1");
			}else{
				$(".alert-success").addClass('d-none');
				$(".errorMsg").html(data.message);
				$(".alert-danger").removeClass("d-none")
				checkbox.prop("checked", false);
			}
		});
	});
});