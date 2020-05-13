import React, { Component } from 'react';
import {Form, Button} from 'react-bootstrap';
import './login.css'
import '../App.css';
import OurSecondNavBar from './OurSecondNavBar';

class ChangePassword extends Component{

	componentWillMount(){
		if(localStorage.getItem("username") !== "")
			window.location = ("/login");
			window.alert("No user logged in!")
	}

	changePassword(e){
		e.preventDefault();
		let pwd1 = document.getElementById("pwd1");
		let pwd2 = document.getElementById("pwd2");
		if(pwd1.value !== pwd2.value){
			pwd1.value = ""
			pwd2.value = ""
			alert("Passwords don't match!")
		}else{
			let obj = {
				"username": localStorage.getItem("username"),
				"password":pwd1.value,
				"password2":pwd2.value
			}
			fetch("register",{
				headers: {
      				'Content-Type': 'application/json',
      				'Authorization': localStorage.getItem('bearer')
    			},
				method: "POST",
				body:JSON.stringify(obj)
			}).then((response) =>{
				return response.json
			}).then((json) =>{
				console.log(json);
			}).catch((error)=>{alert(error)})
		}
	}

    render(){
       return(
        <div className="div2">
        <OurSecondNavBar/>
        <div className="horizontalMargin40">
                <br /><br />
		        <Form onSubmit={(e)=>this.changePassword(e)}>

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
				    <b>Update Password</b>
				  </Button>
				  </center>
				</Form>
			</div>
        </div>
       )
    }
}
export default ChangePassword;