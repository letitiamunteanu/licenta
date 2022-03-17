import React, { useContext } from "react";
import { BoldLink, BoxContainer, FormContainer, Input, MutedLink, SubmitButtom } from "../utility";
import { Marginer } from "../marginer";
import { AccountContext } from "../accountContext";
import { useState } from "react";
import axios from "axios";

export function LoginForm(props){

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
  
    const updateUsername = (props) => {
        setUsername(props.target.value);
        console.log("username:" + props.target.value);
    }

    const updateUserPassword = (props) => {
        setPassword(props.target.value);
        console.log("password:" + password);
    }

    const doLogin = () => {

        let axiosConfig = {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                "Access-Control-Allow-Origin": "*",
            }
          };
          
        const params = new URLSearchParams();
        params.append('username', username);
        params.append('password', password);

        console.log("params: " + params);
         
        axios.post('http://localhost:8889/api/login', params, axiosConfig)
            .then((res) => {
                console.log("RESPONSE RECEIVED: ", res.data["accessToken"]);
                window.localStorage.setItem("accessToken", res.data["accessToken"]);
                window.localStorage.setItem("refreshToken", res.data["refreshToken"]);
            })
            .catch((err) => {
                console.log("AXIOS ERROR: ", err);
            })
    }
    
    const {switchToSignUp} = useContext(AccountContext);

    return <BoxContainer>
        <FormContainer>
            <Input type="username" placeholder="Username" onChange = {updateUsername}/>
            <Input type="password" placeholder="Password" onChange = {updateUserPassword}/>
        </FormContainer>
        <Marginer direction="vertical" margin="0.5rem"/>
        <MutedLink href="#">Forgot your password?</MutedLink>
        <Marginer direction="vertical" margin="1.5rem"/>
        <SubmitButtom type="submit" onClick = {doLogin}>Sign In</SubmitButtom>
        <Marginer direction="vertical" margin="1rem"/>
        <MutedLink href="#">
            Create an account? 
            <BoldLink href="#" onClick={switchToSignUp}> Sign Up</BoldLink>
        </MutedLink>
    </BoxContainer>
}