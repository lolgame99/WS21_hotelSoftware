$(document).ready(function(){
    $(".bookingSearch").keyup(function () {
        var filter = jQuery(this).val();
        jQuery(".bookingTable tr td:first-child").each(function () {
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