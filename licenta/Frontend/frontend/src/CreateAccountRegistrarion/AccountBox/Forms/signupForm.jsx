import React, { useContext } from "react";
import { BoldLink, BoxContainer, FormContainer, Input, MutedLink, SubmitButton } from "../utility";
import { Marginer } from "../marginer";
import { AccountContext } from "../accountContext";
import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

export function SignUpForm(props){

    let navigate = useNavigate();
    
    const routeToLoginPage = () => {

        const path = '/login';
        navigate(path);
    }

    const [username, setUsername] = useState('');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmedPassword, setConfirmedPassword] = useState('');

    const updateUsername = (props) => {
        setUsername(props.target.value);
        console.log("username:" + username);
    }

    const updateUserPassword = (props) => {
        setPassword(props.target.value);
        console.log("password:" + password);
    }

    const updateConfirmedUserPassword = (props) => {
        setConfirmedPassword(props.target.value);
        console.log("confirmed password: " + confirmedPassword);
    }

    const updateUserFirstName = (props) => {
        setFirstName(props.target.value);
        console.log("first name: " + firstName);
    }

    const updateUserLastName = (props) => {
        setLastName(props.target.value);
        console.log("last name: " + lastName);
    }

    const updateUserEmail = (props) => {
        setEmail(props.target.value);
    }

    const doSignUp = () => {
        
        if(password !== confirmedPassword){
            alert("Wrong password!");
            return;
        }

        const requestData = {
            username: username,
            firstName: firstName,
            lastName: lastName,
            email: email,
            password: password,
            role: 'pacient'
        }

        let axiosConfig = {
            headers: {
                'Content-Type': 'application/json',
                "Access-Control-Allow-Origin": "*",
            }
        };
         
        axios.post('http://localhost:8889/api/createAccount/register', requestData, axiosConfig)
            .then((res) => {
                console.log("Response received: ", res.data);
                alert(res.data);
                switchToSignIn();

            })
            .catch((err) => {
                console.log("AXIOS ERROR: ", err);
                alert("Error during sign-up. Please retry!")
            })
    }

    const {switchToSignIn} = useContext(AccountContext);

    return <BoxContainer>
        <FormContainer>
            <Input width="60%" type="text" placeholder="Username*" onChange={updateUsername}/>
            <Input width="60%" type="text" placeholder="First name*" onChange={updateUserFirstName}/>
            <Input width="60%" type="text" placeholder="Last name*" onChange={updateUserLastName}/>
            <Input width="60%" type="email" placeholder="Email*" onChange={updateUserEmail}/>
            <Input width="60%" type="password" placeholder="Password*" onChange={updateUserPassword}/>
            <Input width="60%" type="password" placeholder="Confirm Password*" onChange={updateConfirmedUserPassword}/>
        </FormContainer>
        <Marginer direction="vertical" margin="0.5rem"/>
        <SubmitButton width="45%" type="submit" onClick = {doSignUp}>Sign Up</SubmitButton>
        <Marginer direction="vertical" margin="1rem"/>
        <MutedLink href="#">
            Already have an account? 
            <BoldLink href="#" onClick={switchToSignIn}> Sign In</BoldLink>
        </MutedLink>
    </BoxContainer>
}