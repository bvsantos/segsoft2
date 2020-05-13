import React, { Component } from 'react';
import {Navbar, Nav, Form, Button,NavDropdown} from 'react-bootstrap';
import './login.css'
import '../App.css';

class ChangePassword extends Component{

	registar(e){
		e.preventDefault();
		let u = document.getElementById("uName");
		let pwd1 = document.getElementById("pwd1");
		let pwd2 = document.getElementById("pwd2");
		if(pwd1.value !== pwd2.value){
			pwd1.value = ""
			pwd2.value = ""
			alert("Passwords don't match!")
		}else{
			localStorage.setItem("username", u.value);
			let obj = {
				"userName": u.value,
				"password":pwd1.value,
				"amount": 0
			}
			fetch("register",{
				headers: {
      				'Content-Type': 'application/json'
    			},
				method: "POST",
				body:JSON.stringify(obj)
			}).then((response) =>{
				return response.json
			}).then((json) =>{
				location.replace("/login");
			}).catch((error)=>{alert(error)})
		}
	}

    render(){
        const bgPurple = {backgroundColor: '#11cef0'}
       return(
        <div className="div2">
        <Navbar expand="lg" style={bgPurple}>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="mr-auto">
              <Nav.Link href="/login" ><b>Login</b></Nav.Link>
              <Nav.Link href="/register" ><b>Register</b></Nav.Link>
            </Nav>
          </Navbar.Collapse>
        </Navbar>
        <div className="horizontalMargin40">
                <br /><br />
		        <Form onSubmit={(e)=>this.registar(e)}>
				  <Form.Group >
				    <Form.Label><b>E-MAIL</b></Form.Label>
				    <Form.Control id="uName" type="email" placeholder="Enter your username" />
				  </Form.Group>

				  <Form.Group>
				    <Form.Label><b>PASSWORD</b></Form.Label>
				    <Form.Control id="pwd1" type="password" placeholder="Enter your password" />
				  </Form.Group>
				  <Form.Group >
				    <Form.Label><b>RE-ENTER YOUR PASSWORD</b></Form.Label>
				    <Form.Control id="pwd2" type="password" placeholder="Re-enter your password" />
				  </Form.Group>
				  <center>
				  <Button type="submit" >
				    <b>Register</b>
				  </Button>
				  </center>
				</Form>
			</div>
        </div>
       )
    }
}
export default ChangePassword;