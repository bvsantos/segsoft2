import React, { Component } from 'react';
import {Navbar, Nav, Form, Button, NavDropdown} from 'react-bootstrap';
import './login.css';
import '../App.css';
class Login extends Component{
    login(e){
		e.preventDfault();
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
			    </Nav>
			  </Navbar.Collapse>
			</Navbar>
        	<div className="horizontalMargin40"><br /><br />
                <Form onSubmit={(e)=>this.login(e)}>
				  <Form.Group >
				    <Form.Label><b>E-MAIL</b></Form.Label>
				    <Form.Control id="uName" type="email" placeholder="Enter your username" />
				  </Form.Group>
				  <Form.Group >
				    <Form.Label><b>PASSWORD</b></Form.Label>
				    <Form.Control id="pw1" type="password" placeholder="Enter your password" />
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