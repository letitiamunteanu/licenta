import React, { useContext } from "react";
import { BoldLink, BoxContainer, FormContainer, Input, MutedLink, SubmitButton } from "../utility";
import { Marginer } from "../marginer";
import { useState } from "react";
import axios from "axios";
import styled from "styled-components";

export const ForgotPassForm = styled.main`

    width: 500px;
    margin-top: 350px;
    align-items: center;
    display: flex;
    justify-content: center;
    flex-direction: column;
    margin-left: 700px;

`;

export function ForgotPassword(props){

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmedPassword, setConfirmedPassword] = useState('');
  
    const updateUserEmail = (props) => {
        setEmail(props.target.value);
    }

    const updateUserPassword = (props) => {
        setPassword(props.target.value);
    }

    const updateUserConfirmedPassword = (props) => {
        setConfirmedPassword(props.target.value);
    }

    const doResetPassword = () => {

        //step 1: verific daca exista in baza de date userul cu emailul dat

        const requestData = {
            email: email
        }

        let axiosConfig = {
            headers: {
                'Content-Type': 'application/json',
                "Access-Control-Allow-Origin": "*",
            }
          };

         
        axios.get('http://localhost:8889/api/userController/getUserByEmail/' + email, axiosConfig)
            .then((res) => {
                console.log("RESPONSE RECEIVED: ", res.data);

                if(res.data === true){
                    alert("merge");
                }
            })
            .catch((err) => {
                console.log("AXIOS ERROR: ", err);
            })
    }
    

    return (
        <ForgotPassForm>
            <FormContainer>
                <Input width="60%" type="text" placeholder="Email" onChange = {updateUserEmail}/>
                <Input width="60%" type="password" placeholder="Password" onChange = {updateUserPassword}/>
                <Input width="60%" type="password" placeholder="Confirmed password" onChange = {updateUserConfirmedPassword}/>
            </FormContainer>
            <Marginer direction="vertical" margin="0.5rem"/>
            <SubmitButton width="45%" type="submit" onClick = {doResetPassword}>Reset password</SubmitButton>
        </ForgotPassForm>
    
    )
}