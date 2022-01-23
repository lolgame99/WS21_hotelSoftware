$(document).ready(function(){
	$(".defaultGuest").prop( "checked", true );
	let categoryCounter = 0;
	
	let now = new Date();
	let day = ("0" + now.getDate()).slice(-2);
	let month = ("0" + (now.getMonth() + 1)).slice(-2);
	let today = now.getFullYear()+"-"+(month)+"-"+(day);
	
	$('input[name=checkInDate]').val(today);
	$('input[name=checkInDate]').prop('disabled',true);
		
	if($(".roomSelection").find(".roomCategorys").length > 0){
		categoryCounter = 0;
		$(".roomSelection").find(".roomNumber").each(function(){
			var catId = $(this).parent().siblings().find(".roomCategorys").val();
			var sug = "";
			$(".roomSuggestions").find(".category").first().each(function(){
				if(catId == $(this).text()){
					sug = $(this).siblings().text();
					$(this).parent().remove();
				}
			});
			$(this).val(sug);
			$(this).attr("name","roomNumbers["+ categoryCounter+"]");
			categoryCounter++;
		});
	}
	
    $(".addCategoryBtn").click(function(){
        let newCategoryEntry = $('.roomEntryEmpty').clone();
        newCategoryEntry.removeClass("roomEntryEmpty d-none");
		newCategoryEntry.addClass("roomEntry");
		newCategoryEntry.find(".roomCategorys")[0].setAttribute("name","roomCategorys["+ categoryCounter+"]");
		newCategoryEntry.find(".roomNumber")[0].setAttribute("name","roomNumbers["+ categoryCounter+"]");
       	$(this).before(newCategoryEntry);
		categoryCounter++;
    });

	$(".removeCategoryBtn").click(function(){
		if(categoryCounter > 0){
			categoryCounter--;
		}
        $(".roomEntry").last().remove();
    });

	$("input[type=radio][name=guest]").change(function() {
	    if (this.value == "newGuest") {
	        $(".guest").each(function () {
            	$(this).removeClass("d-none");
				$(this).find("input").prop('required',true);
				$(this).find("#guestMiddleName").prop('required',false);
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
	if($("#companyName").val() != ""){
		$('#companyCustomerRadio').prop('checked',true);
		companyCustomerSelected();
	}else{
		individualCustomerSelected();
	}
	
	$("input[type=radio]").change(function() {
	    if (this.value == "individualCustomer") {
	        individualCustomerSelected();
	    }
	    else if (this.value == "companyCustomer") {
			companyCustomerSelected();
	    }
	});
});

function companyCustomerSelected(){
	$(".individualCustomer").each(function () {
    	$(this).addClass("d-none");
		$(this).find("input").prop('required',false);
		$(this).find("input").val("");
	});
	$(".companyCustomer").each(function () {
    	$(this).removeClass("d-none");
		$(this).find("input").prop('required',true);
	});
}

function individualCustomerSelected(){
	$(".companyCustomer").each(function () {
    	$(this).addClass("d-none");
		$(this).find("input").prop('required',false);
		$(this).find("input").val("");
	});
	$(".individualCustomer").each(function () {
    	$(this).removeClass("d-none");
		$(this).find("input").prop('required',true);
		$(this).find("#customerMiddleName").prop('required',false);
	});
}