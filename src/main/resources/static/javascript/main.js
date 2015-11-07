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
    
    // justify the text of the header links
    $('.stretch').each(function () {
        $(this).strech_text();
    });
});

searchContent = function() {
	var hostUrl = window.location.host;
	var searchTerm = $("#search-query").val();
	if (searchTerm !== "") {
		console.log("Hitting the search endpoint for search term " + searchTerm);
		window.location.href = "http://" + hostUrl +"/search?searchTerm=" + searchTerm;
	}
};

$.fn.strech_text = function () {
    var elmt = $(this),
        cont_width = elmt.width(),
        txt = elmt.html(),
        one_line = $('<span class="stretch_it">' + txt + '</span>'),
        nb_char = elmt.text().length,
        spacing = cont_width / nb_char,
        txt_width;

    elmt.html(one_line);
    txt_width = one_line.width();

    if (txt_width < cont_width) {
        var char_width = txt_width / nb_char,
            ltr_spacing = spacing - char_width + (spacing - char_width) / nb_char;

        one_line.css({
            'letter-spacing': ltr_spacing
        });
    } else {
        one_line.contents().unwrap();
        elmt.addClass('justify');
    }
};