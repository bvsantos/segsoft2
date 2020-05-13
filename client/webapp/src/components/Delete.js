import React, { Component } from 'react';
import {Form, Button} from 'react-bootstrap';
import './login.css'
import '../App.css';
import OurSecondNavBar from './OurSecondNavBar';

class Delete extends Component{

	componentWillMount(){
		if(localStorage.getItem("username") !== "root")
			window.location = ("/login");
			window.alert("Root not logged in")
	}

    delete(e){
		e.preventDefault();
		let accName = document.getElementById("deleteAcc");
			fetch("/delete?user="+accName.value,{
				headers: {
					  'Content-Type': 'application/json',
					  "Authorization":localStorage.getItem("bearer")
    			},
				method: "DELETE"
			}).then((response) =>{
				return response.json
			}).then((json) =>{
				console.log(json);
			}).catch((error)=>{alert(error)})
		}
    
    render(){
       return(
        <div className="div2">
        <OurSecondNavBar/>
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