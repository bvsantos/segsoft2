import React, { Component } from 'react';
import {Navbar, Nav} from 'react-bootstrap';
import './login.css';
import '../App.css';
class OurSecondNavBar extends Component{

    logout(){
        fetch("logoff",{
        headers: {
            "Authorization":localStorage.getItem("bearer")
          },
        method: "DELETE"
      }).then((response) =>{
        localStorage.clear();
        window.location = ("/login");
      })
    }
    
    render() {
        const bgPurple = {backgroundColor: '#11cef0'}
        return (
    		<Navbar expand="lg" style={bgPurple}>
            <Navbar.Toggle aria-controls="basic-navbar-nav" />
			  <Navbar.Collapse id="basic-navbar-nav">
			    <Nav className="mr-auto">
			      <Nav.Link href="/register" ><b>Register</b></Nav.Link>
				  <Nav.Link href="/delete" ><b>Delete</b></Nav.Link>
				  <Nav.Link href="/changepassword" ><b>Change Pass</b></Nav.Link>
                  <div onClick = {this.logout}>
                  <Nav.Link href="#" ><b>Logout</b></Nav.Link>
                  </div>
			      
			    </Nav>
			  </Navbar.Collapse>
			</Navbar>
        );
    }

}
export default OurSecondNavBar;