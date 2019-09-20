$(document).ready(function() {
	var nameuser = document.getElementById("name").innerText;
	var table = $('#todotable').DataTable({
		"sAjaxSource" : "http://localhost:9999/notifications/?username="+nameuser,
		"sAjaxDataProp" : "",
		"order" : [ [ 0, "desc" ] ],
		"aoColumns" : [ {
			"mData" : "msg_id"
		}, {
			"mData" : "member_text"
		}, {
			"mData" : "timestamp"
		}, {
			"mData" : "msg_status",
			"mRender": function ( data, type, full ) {
				if(data == '0'){
					return '<span style="color:red">เก่า</span>';
				}else{
					return '<span style="color:green">ใหม่</span>';
				}
		      }	
		} ]
		
	})
	$.ajax({
		type : 'put',
		contentType : "application/json",
		beforeSend : function(xhr) {
			xhr.setRequestHeader(header, token);
			xhr.timeout = 4000;
		},
		url : "http://localhost:9999/updatenotification/?username="+nameuser,
		dataType : "json",
		timeout : 4000,
		error : function(data) {
		},
		success : function(data) {
			
		}
	});
});

