newContent = function() {
	var tagNames = [];
	$('input.checks:checkbox:checked').each(function () {
		tagNames.push($(this).val());
	});
	
	var jsonPost = {
			title : $("#title").val(),
			externalLink : $("#external-link").val(),
			contentType : $("#content-type").val(),
			tagNames : tagNames
	};
	console.log(jsonPost);
	
	$.ajax({
		type : "POST",
		url : "content/create",
		contentType : "application/json; charset=utf-8",
		data : JSON.stringify(jsonPost),
		traditional: true,
		datatype : "json",
		success : function(resp) {
			console.log(resp);
			$("#title").val("");
			$("#external-link").val("");
			$('#single-content').find('input[type=checkbox]:checked').prop('checked', false);
		},
		error : function(resp) {
			console.log("Error saving new content to database");
		}
	});
}