import React, { useContext } from "react";
import { BoldLink, BoxContainer, FormContainer, Input, MutedLink, SubmitButton } from "../utility";
import { Marginer } from "../marginer";
import { AccountContext } from "../accountContext";
import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { UsernameContext } from "../../UsernameContext";

export function LoginForm(props){

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const {setUsr} = useContext(UsernameContext);

    let navigate = useNavigate();
  
    const updateUsername = (props) => {
        setUsername(props.target.value);
        console.log("username:" + props.target.value);
    }

    const updateUserPassword = (props) => {
        setPassword(props.target.value);
        console.log("password:" + password);
    }

    const routeForAdmin = () =>{ 
        let path = `/adminPage`; 
        navigate(path);
    }

    const routeForPatient = (username) =>{ 
        let path = `/homePatient/${username}`; 
        navigate(path);
    }

    const routeToCompletePatientProfile = (username) =>{ 
        let path = `/completePatientProfile/${username}`; 
        navigate(path);
    }

    const routeForDoctor = (username) =>{ 
        let path = `/homeDoctor/${username}`; 
        navigate(path);
    }

    const routeToCompleteDoctorProfile = (username) =>{ 
        let path = `/completeDoctorProfile/${username}`; 
        navigate(path);
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
                
                let axiosGetConfig = {
                    headers: {
                        'Content-Type': '*/*',
                        "Access-Control-Allow-Origin": "*",
                        'Authorization': `Bearer ${window.localStorage.getItem("accessToken")}`
                    }
                };

                axios.get('http://localhost:8889/api/jwt/decodeJwt', axiosGetConfig)
                .then((response) => {
                    console.log("DECODE JWT: ", response.data.split(" ")[1])
                    window.localStorage.setItem("role", response.data.split(" ")[1]);
                    

                    if(window.localStorage.getItem("role") === "admin"){
                        routeForAdmin();
                    }
                    
                    if(window.localStorage.getItem("role") === "pacient"){

                        axios.get('http://localhost:8888/api/patients/checkUserProfile/' + username, axiosGetConfig)
                            .then((response) => {

                                if(response.data == 'The user has to complete the profile!'){
                                    routeToCompletePatientProfile(username);
                                }
                                else{
                                    routeForPatient(username);
                                }
                            })
                            .catch((err) => {
                                console.log("Error:", err.message);
                            });
                    }

                    if(window.localStorage.getItem("role") === "doctor"){

                        axios.get('http://localhost:8888/api/doctors/checkDrProfile/' + username, axiosGetConfig)
                            .then((response) => {

                                if(response.data == 'The user has to complete the profile!'){
                                    routeToCompleteDoctorProfile(username);
                                }
                                else{
                                    routeForDoctor(username);
                                }
                            })
                            .catch((err) => {
                                console.log("Error:", err.message);
                            });
                    }

                });

            })
            .catch((err) => {
                if(err == "Error: Request failed with status code 403"){
                    alert("403 forbidden");

                }
            });

        setUsr(username);
    }
    
    const {switchToSignUp} = useContext(AccountContext);

    
    const routeToForgotPassword = () =>{ 
        let path = `/forgotPassword`; 
        navigate(path);
    }

    return(
    <BoxContainer>
        <FormContainer>
            <Input width="60%" type="username" placeholder="Username" onChange = {updateUsername}/>
            <Input width="60%" type="password" placeholder="Password" onChange = {updateUserPassword}/>
        </FormContainer>
        <Marginer direction="vertical" margin="0.5rem"/>
        <MutedLink href="#" onClick={routeToForgotPassword}>Forgot your password?</MutedLink>
        <Marginer direction="vertical" margin="1.5rem"/>
        <SubmitButton width="45%" type="submit" onClick = {doLogin}>Sign In</SubmitButton>
        <Marginer direction="vertical" margin="1rem"/>
        <MutedLink href="#">
            Create an account? 
            <BoldLink href="#" onClick={switchToSignUp}> Sign Up</BoldLink>
        </MutedLink>
    </BoxContainer>
    )
}