import React, { useContext } from "react";
import { BoldLink, BoxContainer, FormContainer, Input, MutedLink, SubmitButtom } from "../utility";
import { Marginer } from "../marginer";
import { AccountContext } from "../accountContext";
import { useState } from "react";
import axios from "axios";

export function ForgotPassword(props){

    const [passwordConfirmed, setConfirmedPassword] = useState('');
    const [password, setPassword] = useState('');
  
    const updateChangedPassword = (props) => {
        setConfirmedPassword(props.target.value);
        console.log("username:" + props.target.value);
    }

    const updateUserPassword = (props) => {
        setPassword(props.target.value);
        console.log("password:" + password);
    }

    const changePassword = () => {

        if(password !== passwordConfirmed){
            alert("Ati introdus parola gresit! Verificati inca o data!");
            return;
        }

        const requestPasswordData = {
            password
        }

        let axiosConfig = {
            headers: {
                'Content-Type': 'application/json',
                "Access-Control-Allow-Origin": "*",
            }
        };

        const url = 'http://localhost:8889/api/userController/updateUserAccount/' + username;
         
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
            <Input type="password" placeholder="New password" onChange = {updateUserPassword}/>
            <Input type="password" placeholder="Confirm password" onChange = {updateUserPassword}/>
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