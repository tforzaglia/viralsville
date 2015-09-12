searchContent = function() {
	var searchTerm = $("#search-query").val();
	console.log("Hitting the search endpoint for search term " + searchTerm);
	window.location.href = "http://localhost:8080/search?searchTerm=" + searchTerm;
}