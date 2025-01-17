import React, { Component } from 'react';
import {Form, Button} from 'react-bootstrap';
import './login.css';
import '../App.css';
import OurNavBar from './OurNavBar';

class Login extends Component{

    login(e){
		e.preventDefault();
		console.log("Omg");
		let username = document.getElementById("username");
		let pwd = document.getElementById("pwd");
		fetch("/admin").then((response)=>{return response.text()})
		.then((json)=>{
			fetch("/login",{
				headers: {
					  'Content-Type': 'application/json'
				},
				method: "POST",
				body:JSON.stringify({username:username.value,password:pwd.value})})
			.then((response)=>{localStorage.setItem("username", username.value);localStorage.setItem("bearer",response.headers.get("Authorization"));return response.text()})
			.then((json)=>{
				console.log(json)
				window.location = ("http://localhost:3000/changepassword")})
			.catch((error)=>{pwd.value="";alert(error)})})
}
    
    render() {
        return (
            <div className="div2">
    		<OurNavBar/>
        	<div className="horizontalMargin40"><br /><br />
                <Form onSubmit={(e)=>this.login(e)}>
				  <Form.Group >
				    <Form.Label><b>E-MAIL</b></Form.Label>
				    <Form.Control id="username" type="username" placeholder="Enter your username" />
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