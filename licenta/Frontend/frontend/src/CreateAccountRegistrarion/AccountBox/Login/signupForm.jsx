import React, { useContext } from "react";
import { BoldLink, BoxContainer, FormContainer, Input, MutedLink, SubmitButtom } from "../utility";
import { Marginer } from "../marginer";
import { AccountContext } from "../accountContext";
import { useState } from "react";
import axios from "axios";

export function SignUpForm(props){

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
            alert("Ati introdus parola gresit! Rescrieti parola!");
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
                console.log("RESPONSE RECEIVED: ", res.data);
            })
            .catch((err) => {
                console.log("AXIOS ERROR: ", err);
            })
    }

    const {switchToSignIn} = useContext(AccountContext);
    return <BoxContainer>
        <FormContainer>
            <Input type="text" placeholder="Username" onChange={updateUsername}/>
            <Input type="text" placeholder="First name" onChange={updateUserFirstName}/>
            <Input type="text" placeholder="Last name" onChange={updateUserLastName}/>
            <Input type="email" placeholder="Email" onChange={updateUserEmail}/>
            <Input type="password" placeholder="Password" onChange={updateUserPassword}/>
            <Input type="password" placeholder="Confirm Password" onChange={updateConfirmedUserPassword}/>
        </FormContainer>
        <Marginer direction="vertical" margin="0.5rem"/>
        <SubmitButtom type="submit" onClick = {doSignUp}>Sign Up</SubmitButtom>
        <Marginer direction="vertical" margin="1rem"/>
        <MutedLink href="#">
            Already have an account? 
            <BoldLink href="#" onClick={switchToSignIn}> Sign In</BoldLink>
        </MutedLink>
    </BoxContainer>
}