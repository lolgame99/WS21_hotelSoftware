$(document).ready(function(){
    $(".addCategoryBtn").click(function(){
        var newCategoryEntry = $('.categoryEntryEmpty').clone();
        newCategoryEntry.removeClass("categoryEntryEmpty d-none");
        $(this).before(newCategoryEntry);
    });

});
