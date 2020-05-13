import React, { Component } from 'react';
import {Navbar, Nav, Form, Button,NavDropdown} from 'react-bootstrap';
import './login.css'
import '../App.css';
import OurNavBar from './OurNavBar';

class Delete extends Component{

    render(){
        const bgPurple = {backgroundColor: '#11cef0'}
       return(
        <div className="div2">
        <OurNavBar/>
        <div className="horizontalMargin40">
                <br /><br />
		        <Form onSubmit={(e)=>this.registar(e)}>
				  <Form.Group >
				    <Form.Label><b>Account to delete:</b></Form.Label>
				    <Form.Control id="deleteAcc" placeholder="Enter account to delete" />
				  </Form.Group>
				  <center>
				  <Button type="submit" >
				    <b>Delete Account</b>
				  </Button>
				  </center>
				</Form>
			</div>
        </div>
       )
    }
}
export default Delete;