


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
	      
		console.log( options );
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