if('serviceWorker' in navigator){
    navigator.serviceWorker.register("sw.js").then(reg => {
        console.log(`Registration:`, reg);
    }).catch(err => {
        console.warn(`Registration:`, err);
    })
}  
window.console.log = function(){
    console.error('The developer console is temp...');
    window.console.log = function() {
        return false;
    }
}



window.onload = () => {    
        if("Notification" in window){
            Notification.requestPermission(permission => {
            	  if(permission === "granted"){
            		     console.log("Permission granted")
            		  } else {
            		     console.log("Permission is not granted")
            		  }
            })
        } else {
            prepend("Notification API is not supported in your browser!");
        }  
        
        setInterval(ajaxCall, 5000);        
    }


function ajaxCall() {
    //do your AJAX stuff here
	var totalnotification = document.getElementById("greetings");
	 //console.log(totalnotification);
	$.ajax({
    	   type:'POST',
    	   contentType : "application/json",
    	   beforeSend: function(xhr){
    	       xhr.setRequestHeader(header, token);
    	       xhr.timeout = 4000;
    	   },
    	   url :"/timecheck",
    	   dataType: "json",
    	   timeout: 4000,
    	   success : function(){
    		  // console.log("pass");
    	   },
    	   error : function(){
    		 //  console.log("not pass");
    	   }
    	}); 
    
}


var stompClient = null;


function connect() {
    var socket = new SockJS('/zengcode-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings/'+data, function (message) {
            showGreeting(JSON.parse(message.body).content);
        });
    });
    var socket2 = new SockJS('/noticode-websocket');
    stompClient2 = Stomp.over(socket2);
    stompClient2.connect({}, function (frame){
    	console.log('Connected: ' + frame);
    	 stompClient.subscribe('/topic/notiings/'+data, function (message) {
             shownotification();
         });
    });
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}


function showGreeting(message) {
    $("#greetings").html(message);
}

function shownotification(){
	if("Notification" in window){
        Notification.requestPermission(stat => {
            if(stat === "granted"){
                navigator.serviceWorker.getRegistration().then(sw => {
                    let options = {
                        body: "You have notification!",
                        icon: "img/icon_notification.png",
						image: "img/notification-flat.png",
						click_action : "http://localhost:8080/notification"
                    }
                    sw.showNotification("You have notification!", options);
                   
                })
            } else {
                prepend("Notification isn't granted");
            }
        })
    } else {
        prepend("Notification API is not supported in your browser!");
    }
}


