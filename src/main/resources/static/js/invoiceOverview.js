$(document).ready(function(){
	
	if($(".itemSelection").find(".item").length > 0){
		categoryCounter = 0;
		$(".itemSelection").find(".item").each(function(){
			$(this).find(".itemToPay").attr("name","toPay["+ categoryCounter+"]");
			$(this).find(".itemName").attr("name","names["+ categoryCounter+"]");
			$(this).find(".itemDescription").attr("name","descriptions["+ categoryCounter+"]");
			$(this).find(".itemPrice").attr("name","prices["+ categoryCounter+"]");
			$(this).find(".itemId").attr("name","assignmentIds["+ categoryCounter+"]");
			categoryCounter++;
		});
	}
});