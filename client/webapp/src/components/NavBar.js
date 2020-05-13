import React, { Component } from 'react';
import {Navbar, Nav, Form, Button, NavDropdown} from 'react-bootstrap';
import './login.css';
import '../App.css';
class OurNavBar extends Component{

    login(e){
		e.preventDfault();
		let username = document.getElementById("username";
		let pwd = document.getElementById("pwd");
		fetch("/login",{
			headers: {
				  'Content-Type': 'application/json'
			},
			method: "POST",
			body:JSON.stringify({username:u.value,password:pwd.value})})
	.then((response)=>{localStorage.setItem("username", u.value);localStorage.setItem("bearer",response.headers.get("Authorization"));location.replace("http://localhost:3000/changepass")})
	.catch((error)=>{pwd.value="";alert(error.text())})
}
    
    render() {
        const bgPurple = {backgroundColor: '#11cef0'}
        return (
    		<Navbar expand="lg" style={bgPurple}>
            <Navbar.Toggle aria-controls="basic-navbar-nav" />
			  <Navbar.Collapse id="basic-navbar-nav">
			    <Nav className="mr-auto">
			      <Nav.Link href="/login" ><b>Login</b></Nav.Link>
			      <Nav.Link href="/register" ><b>Register</b></Nav.Link>
			    </Nav>
			  </Navbar.Collapse>
			</Navbar>
        );
    }

}
export default OurNavBar;