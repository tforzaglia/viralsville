$(document).ready(function() {
	document.getElementById("search-query").onkeydown = function(event) {
		if (event.keyCode == 13) {
			searchContent();
		}
	}
});

searchContent = function() {
	var searchTerm = $("#search-query").val();
	if (searchTerm !== "") {
		console.log("Hitting the search endpoint for search term " + searchTerm);
		window.location.href = "http://localhost:8080/search?searchTerm=" + searchTerm;
	}
};