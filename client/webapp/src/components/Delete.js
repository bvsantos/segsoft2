import React, { Component } from 'react';
import {Navbar, Nav, Form, Button,NavDropdown} from 'react-bootstrap';
import './login.css'
import '../App.css';
import OurNavBar from './OurNavBar';

class Delete extends Component{

    delete(e){
		e.preventDefault();
		let accName = document.getElementById("deleteAcc");
			fetch("/delete?user="+accName.value,{
				headers: {
      				'Content-Type': 'application/json'
    			},
				method: "DELETE",
				body:JSON.stringify(obj)
			}).then((response) =>{
				return response.json
			}).then((json) =>{
				window.location("/login");
			}).catch((error)=>{alert(error)})
		}
    }
    
    render(){
        const bgPurple = {backgroundColor: '#11cef0'}
       return(
        <div className="div2">
        <OurNavBar/>
        <div className="horizontalMargin40">
                <br /><br />
		        <Form onSubmit={(e)=>this.delete(e)}>
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