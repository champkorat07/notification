$(document).ready(function() {
	var table = $('#todotable').DataTable({
		"sAjaxSource" : "/notifications",
		"sAjaxDataProp" : "",
		"order" : [ [ 0, "asc" ] ],
		"aoColumns" : [ {
			"mData" : "msg_id"
		}, {
			"mData" : "member_text"
		}, {
			"mData" : "timestamp"
		}, {
			"sDefaultContent" : "<button >Clear!</button>"
		} ]
	})

	$('#todotable tbody').on('click', 'button', function() {
		var $row = $(this).closest("tr"); // Find the row
		var text = $row.find(".sorting_1").text(); // Find the text
		console.log('data is: ', text);
		$.ajax({
			type : 'POST',
			contentType : "application/json",
			beforeSend : function(xhr) {
				xhr.setRequestHeader(header, token);
				xhr.timeout = 4000;
			},
			url : "/update/?text=" + text,
			dataType : "json",
			timeout : 4000,
			error : function(data) {
				location.reload()
				console.log("error", data);
			},
			success : function(data) {
				location.reload()
			}
		});

	});
});
