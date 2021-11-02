$(document).ready(function(){
    $(".addCategoryBtn").click(function(){
        var newCategoryEntry = $('.categoryEntryEmpty').clone();
        newCategoryEntry.removeClass("categoryEntryEmpty d-none");
        $(this).before(newCategoryEntry);
    });

    jQuery("#customerSearch").keyup(function () {
    var filter = jQuery(this).val();
    jQuery(".customerList li").each(function () {
        if (jQuery(this).text().search(new RegExp(filter, "i")) < 0) {
            jQuery(this).hide();
        } else {
            jQuery(this).show()
        }
    });
});

});
