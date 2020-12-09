<%@page import='project.web.socket.Pvo'%>
<%@ page language='java' contentType='text/html; charset=UTF-8'
	pageEncoding='UTF-8'%>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>Insert title here</title>
<script type='text/javascript'>
	let userName;
	let socket;
	let A = 'A\r\n';
	let B = 'B\r\n';
	const LOG_IN = 'a';
	const LOG_OUT = 'b';
	const MESSAGE = 'Z';
	
	window.addEventListener('DOMContentLoaded', (e) => {
		userName = prompt('Input your name');
		document.querySelector('#user_name').innerHTML = document.querySelector('#user_name').innerHTML + '[ ' + userName + ' ]';
		document.querySelector('#user_list').innerHTML += "<span id='userName'>" + userName + '(Me)<br></span>';	
		A += userName + '\r\n';
		B += userName + '\r\n';
		connectWS();
		document.querySelector('#msg').addEventListener('keydown', (evt) => {
			if (evt.keyCode == 13) {
				evt.preventDefault();
				if (document.querySelector('#msg').value != '') {
					if (document.querySelector('#s_method').value === 'A') {					
						socket.send(A + document.querySelector('#msg').value);
					} else {
						socket.send(B + document.querySelector('#msg').value);
					}
					document.querySelector('#msg').value = '';
				}
			}
		});
	});
	
	function connectWS() {
		socket = new WebSocket('ws:\\\\localhost:8000\\ws?');
		socket.onopen = (e) => {
			console.log('Server connect');
			socket.send(LOG_IN + '\r\n' + userName);
		};
		
		socket.onmessage = (e) => {
			let a = e.data.split('\r\n');
			if (a[0] === MESSAGE) {		
				document.querySelector('#text_area').innerHTML += a[1] + ": " + a[2] + '<br>';
				document.querySelector('#text_area').scrollTop = document.querySelector('#text_area').scrollHeight;
			} else if (a[0] === LOG_IN) {
				document.querySelector('#text_area').innerHTML += a[2] + a[3] + '<br>';
				document.querySelector('#user_list').innerHTML += "<span id='" + a[1] + "'>" + a[2] + '(' + a[1] + ')<br></span>';
			} else if (a[0] === LOG_OUT) {
				document.querySelector('#text_area').innerHTML += a[2] + a[3] + '<br>';
				let i = document.querySelector('#user_list').innerHTML.indexOf('id="' + a[1] + '"');
				let j = document.querySelector('#user_list').innerHTML.indexOf('<br></span>', i + 1);
				document.querySelector('#user_list').innerHTML = 
					document.querySelector('#user_list').innerHTML.substring(0, i) + document.querySelector('#user_list').innerHTML.substring(j + 11);
			}
		};

		socket.onclose = (e) => {
			console.log('Server disconnect');
		};
		
		socket.onerror = (e) => {
			console.log('Server Error');
		};
	}
</script>
</head>
<body>
	<div id='user_name'>My Name:</div>
	<div id='text_area'
		style='border: 2px solid black; overflow: auto; width: 600px; height: 350px;'></div>
	<select id="s_method">
		<option value='A'>A(to Me)</option>
		<option value='B'>B(to All)</option>
	</select>
	<input type='text' id='msg' class='form-control'
		style='margin-bottom: 40px; width: 515px'>
	<div id='user_list'
		style='border: 1px solid black; overflow: auto; width: 600px; height: 350px;'></div>
</body>
</html>
