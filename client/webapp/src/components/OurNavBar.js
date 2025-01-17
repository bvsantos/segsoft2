import React, { Component } from 'react';
import {Navbar, Nav} from 'react-bootstrap';
import './login.css';
import '../App.css';
class OurNavBar extends Component{
    
    render() {
        const bgPurple = {backgroundColor: '#11cef0'}
        return (
    		<Navbar expand="lg" style={bgPurple}>
            <Navbar.Toggle aria-controls="basic-navbar-nav" />
			  <Navbar.Collapse id="basic-navbar-nav">
			    <Nav className="mr-auto">
			      <Nav.Link href="/login" ><b>Login</b></Nav.Link>
			      <Nav.Link href="/register" ><b>Register</b></Nav.Link>
				  <Nav.Link href="/delete" ><b>Delete</b></Nav.Link>
				  <Nav.Link href="/changepassword" ><b>Change Pass</b></Nav.Link>
			    </Nav>
			  </Navbar.Collapse>
			</Navbar>

        );
    }

}
export default OurNavBar;