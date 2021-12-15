


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
	$('.dropdown-menu a').on('click', function(event) {
	   var options = [];

	   var $target = $(event.currentTarget),
	       val = $target.attr('data-value'),
	       $inp = $target.find('input'),
	       idx;
	
	   if ((idx = options.indexOf(val)) > -1) {
	      options.splice(idx, 1);
	      setTimeout(function() { $inp.prop('checked', false)}, 0);
	   } else {
	      options.push(val);
	      setTimeout(function() {$inp.prop('checked', true)}, 0);
	   }
	
	   $(event.target).blur();
	      
	   console.log(options);
	   return false;
	});
	
});