$(document).ready(function(){
	var categoryCounter = 0;
	
	var now = new Date();
	var day = ("0" + now.getDate()).slice(-2);
	var month = ("0" + (now.getMonth() + 1)).slice(-2);
	var today = now.getFullYear()+"-"+(month)+"-"+(day);
	
	$('input[name=checkInDate]').val(today);
	$('input[name=checkInDate]').prop('disabled',true);
	
    $(".addCategoryBtn").click(function(){
        var newCategoryEntry = $('.categoryEntryEmpty').clone();
        newCategoryEntry.removeClass("categoryEntryEmpty d-none");
		newCategoryEntry.addClass("categoryEntry");
		newCategoryEntry.find(".categoryAmounts")[0].setAttribute("name","categoryAmounts["+ categoryCounter+"]");
		newCategoryEntry.find(".categoryValues")[0].setAttribute("name","categoryValues["+ categoryCounter+"]");
        $(this).before(newCategoryEntry);
		categoryCounter++;
    });

	$(".removeCategoryBtn").click(function(){
		categoryCounter--;
        $(".categoryEntry").last().remove();
    });

	$("input[type=radio][name=guest]").change(function() {
	    if (this.value == "newGuest") {
	        $(".guest").each(function () {
            	$(this).removeClass("d-none");
				$(this).find("input").prop('required',true);
        	});
	    }
	    else if (this.value == "sameAsCustomer") {
			 $(".guest").each(function () {
            	$(this).addClass("d-none");
				$(this).find("input").prop('required',false);
        	});
	    }
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