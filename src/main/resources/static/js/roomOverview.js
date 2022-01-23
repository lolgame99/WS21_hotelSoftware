$(document).ready(function(){
	var searchParams = new URLSearchParams(window.location.search);
	if(searchParams.has("success")){
		$(".alert-success").removeClass('d-none');
	}
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
		if(status == "Cleaning"){
			$(".changeRoomSelect").append('<option value="MAINTENANCE">Maintenance</option>');
			$(".changeRoomSelect").append('<option value="AVAILABLE">Available</option>');
		}else if(status == "Available"){
			$(".changeRoomSelect").append('<option value="MAINTENANCE">Maintenance</option>');
			$(".changeRoomSelect").append('<option value="CLEANING">Cleaning</option>');
		}else{
			$(".changeRoomSelect").append('<option value="CLEANING">Cleaning</option>');
			$(".changeRoomSelect").append('<option value="AVAILABLE">Available</option>');
		}
		
    });

	$(".roomStatusBtn").on( 'click', function( event ) {
		var getUrl = window.location;
		var baseUrl = getUrl .protocol + "//" + getUrl.host + "/";
		$.ajax({
			type: "POST",
			url: baseUrl+"api/room/setStatus?roomId="+$(".changeRoomNumber").text()+"&status="+$(".changeRoomSelect").val(),
			dataType : "json"
		}).done(function( data ) {
		    if(data.status == "ok"){
				var baseUrl = window.location.href.split("?")[0];
				$(location).attr('href',baseUrl+"?success=1");
			}else{
				$(".alert-success").addClass('d-none');
				$(".errorMsg").html(data.message);
				$(".alert-danger").removeClass("d-none")
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