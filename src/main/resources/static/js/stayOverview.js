$(document).ready(function(){
	var searchParams = new URLSearchParams(window.location.search);
	if(searchParams.has("date")){
		$(".dateSelection").val(searchParams.get("date"));
	}else{
		var now = new Date();
		var day = ("0" + now.getDate()).slice(-2);
		var month = ("0" + (now.getMonth() + 1)).slice(-2);
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

});