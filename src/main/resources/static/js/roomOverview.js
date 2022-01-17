


$(document).ready(function(){
    $(".roomOverviewSearch").keyup(function () {
        var filter = jQuery(this).val();
        jQuery(".roomOverview tr td:first-child").each(function () {
            if (jQuery(this).text().search(new RegExp(filter, "i")) < 0) {
                jQuery(this).parent().hide();
            } else {
                jQuery(this).parent().show();
            }
        });
    });

	$(".showRoomStatusChange").click(function(){
		var status = $(this).siblings().get(1).innerText;
		var number = $(this).parent().siblings().get(0).innerText;
		
		$(".changeRoomNumber").text(number);
		$(".changeRoomStatus").text(status);
		$(".changeRoomSelect").children().each(function () {
            $(this).remove();
        });
		if(status = "Cleaning"){
			$(".changeRoomSelect").append('<option value="maintenance">Maintenance</option>');
			$(".changeRoomSelect").append('<option value="available">Available</option>');
		}else if(status = "Available"){
			$(".changeRoomSelect").append('<option value="maintenance">Maintenance</option>');
			$(".changeRoomSelect").append('<option value="cleaning">Cleaning</option>');
		}else{
			$(".changeRoomSelect").append('<option value="cleaning">Cleaning</option>');
			$(".changeRoomSelect").append('<option value="available">Available</option>');
		}
		
    });
	
	var options = [];
	$( '.dropdown-menu a' ).on( 'click', function( event ) {
	    var  $target = $( event.currentTarget ),
	        val = $target.attr( 'data-value' ),
	        $inp = $target.find( 'input' ),
	        idx;
	
	    if ( ( idx = options.indexOf( val ) ) > -1 ) {
	       options.splice( idx, 1 );
	       setTimeout( function() { $inp.prop( 'checked', false ) }, 0);
	    } else {
	       options.push( val );
	       setTimeout( function() { $inp.prop( 'checked', true ) }, 0);
	    }
	
	    $( event.target ).blur();
	      
		if(options.length != 0){
			$(".roomStatus").each(function() {
				if($.inArray($(this).html(),options) == -1){
					$(this).parent().parent().hide();
				}else{
					$(this).parent().parent().show();
				}
			});
		}else{
			$(".roomStatus").each(function() {
					$(this).parent().parent().show();
			});
		}
		
	});
});