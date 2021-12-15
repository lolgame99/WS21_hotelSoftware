$(document).ready(function(){
	var categoryCounter = 0 + $(".categoryEntry").length;
	
	/*$("input[type=radio][name=customer]").change(function() {
	    if (this.value == "individualCustomer") {
	        $(':radio[id=flexRadioDefault1]').change(function() {
				$("#yes").removeClass("none");
				$("#no").addClass("none");


			});
			$(':radio[id=flexRadioDefault2]').change(function() {
				$(".customer").removeClass("form-row row my-2");

			});
	    }
	    else if (this.value == "company_travelAgency") {
			 $(".customer").each(function () {
            	$(this).addClass("d-none");
				$(this).find("input").prop('required',false);
        	});
	    }
	});*/
	
		$("input[name='flexRadioDefault']").click(function() {
			if ($("#flexRadioDefault1").is(":checked")) {
				$(".form-row row my-2").addClass("show-DIV");
			} else {
				$(".form-row row my-2").removeClass("show-DIV");
			}
		});
	
	
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
		if(categoryCounter > 0){
			categoryCounter--;
		}
        $(".categoryEntry").last().remove();
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