$(document).ready(function(){
	var categoryCounter = 0;
	
    $(".addCategoryBtn").click(function(){
        var newCategoryEntry = $('.categoryEntryEmpty').clone();
        newCategoryEntry.removeClass("categoryEntryEmpty d-none");
		newCategoryEntry.find(".categoryAmounts")[0].setAttribute("name","categoryAmounts["+ categoryCounter+"]");
		newCategoryEntry.find(".categoryValues")[0].setAttribute("name","categoryValues["+ categoryCounter+"]");
        $(this).before(newCategoryEntry);
		categoryCounter++;
    });

	$(".resetBtn").click(function(){
        var baseUrl = window.location.href.split("?")[0];
		$(location).attr('href',baseUrl);
    });

    jQuery("#customerSearch").keyup(function () {
        var filter = jQuery(this).val();
        jQuery(".customerList li").each(function () {
            if (jQuery(this).text().search(new RegExp(filter, "i")) < 0) {
                jQuery(this).hide();
            } else {
                jQuery(this).show();
            }
        });
    });

});
