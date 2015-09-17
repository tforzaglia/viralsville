$(document).ready(function() {
	
	// perform content search when user presses the enter key
	document.getElementById("search-query").onkeydown = function(event) {
		if (event.keyCode == 13) {
			searchContent();
		}
	}
	
	// stop the social media from scrolling over the footer
	$.fn.scrollBottom = function() {
        return $(document).height() - this.scrollTop() - this.height();
    };

    var $el = $('#social-media-div');
    var $window = $(window);
    var top = $el.parent().position().top;

    $window.bind("scroll resize", function() {
        var gap = $window.height() - $el.height() - 10;
        var visibleFoot = 275 - $window.scrollBottom();
        var scrollTop = $window.scrollTop()

        if (scrollTop < top + 10) {
            $el.css({
                top: (top - scrollTop) + "px",
                bottom: "auto"
            });
        } else if (visibleFoot > gap) {
            $el.css({
                top: "auto",
                bottom: visibleFoot + "px"
            });
        } else {
            $el.css({
                top: 0,
                bottom: "auto"
            });
        }
    }).scroll();
});

searchContent = function() {
	var searchTerm = $("#search-query").val();
	if (searchTerm !== "") {
		console.log("Hitting the search endpoint for search term " + searchTerm);
		window.location.href = "http://localhost:8080/search?searchTerm=" + searchTerm;
	}
};