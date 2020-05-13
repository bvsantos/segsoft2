import React, { Component } from 'react';
import {Navbar, Nav, Form, Button, NavDropdown} from 'react-bootstrap';
import './login.css';
import '../App.css';
class Login extends Component{

    login(e){
		e.preventDfault();
		let username = document.getElementById("username");
		let pwd = document.getElementById("pwd");
		fetch("/login",{
			headers: {
				  'Content-Type': 'application/json'
			},
			method: "POST",
			body:JSON.stringify({username:username.value,password:pwd.value})})
	.then((response)=>{localStorage.setItem("username", username.value);localStorage.setItem("bearer",response.headers.get("Authorization"));
	window.location("http://localhost:3000/changepass")})
	.catch((error)=>{pwd.value="";alert(error.text())})
}
    
    render() {
        const bgPurple = {backgroundColor: '#11cef0'}
        return (
            <div className="div2">
    		<Navbar expand="lg" style={bgPurple}>
            <Navbar.Toggle aria-controls="basic-navbar-nav" />
			  <Navbar.Collapse id="basic-navbar-nav">
			    <Nav className="mr-auto">
			      <Nav.Link href="/login" ><b>Login</b></Nav.Link>
			      <Nav.Link href="/register" ><b>Register</b></Nav.Link>
				  <Nav.Link href="/delete" ><b>Delete</b></Nav.Link>
				</Nav>
			  </Navbar.Collapse>
			</Navbar>
        	<div className="horizontalMargin40"><br /><br />
                <Form onSubmit={(e)=>this.login(e)}>
				  <Form.Group >
				    <Form.Label><b>E-MAIL</b></Form.Label>
				    <Form.Control id="username" type="email" placeholder="Enter your username" />
				  </Form.Group>
				  <Form.Group >
				    <Form.Label><b>PASSWORD</b></Form.Label>
				    <Form.Control id="pwd" type="password" placeholder="Enter your password" />
				  </Form.Group>
				  <center>
				  <Button type="submit" >
				    <b>Login</b>
				  </Button>
				  </center>
				</Form>
			</div>
		</div>
        );
    }

}
export default Login;