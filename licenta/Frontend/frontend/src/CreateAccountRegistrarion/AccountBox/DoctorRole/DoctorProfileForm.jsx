import React from "react";
import { useState } from "react";
import { SubmitButton, Input, FormContainer } from "../utility";
import { Marginer } from "../marginer";
import axios from "axios";
import { useParams, useNavigate } from "react-router-dom";

export function DoctorProfileForm() {

    const [cuim, setCuim] = useState('');
    const [nume, setDoctorNume] = useState('');
    const [prenume, setDoctorPrenume] = useState('');
    const [specializare, setDoctorSpecializare] = useState('');
    const [sediu, setDoctorSediu] = useState('');
    const [sala, setDoctorSala] = useState('');
    const username = useParams();
    const navigate = useNavigate();

    const updateCuim = (props) => {
        setCuim(props.target.value);
        console.log("cuim:" + props.target.value);
    } 

    const updateName = (props) => {
        setDoctorNume(props.target.value);
        console.log("nume:" + props.target.value);
    }

    const updatePrenume = (props) => {
        setDoctorPrenume(props.target.value);
        console.log("prenume:" + props.target.value);
    }

    const updateSpecializare = (props) => {
        setDoctorSpecializare(props.target.value);
        console.log("specializare:" + props.target.value);
    }

    const updateSediu = (props) => {
        setDoctorSediu(props.target.value);
        console.log("sediu:" + props.target.value);
    }

    const updateSala = (props) => {
        setDoctorSala(props.target.value);
        console.log("sala:" + props.target.value);
    }

    const routeToHomeDoctor = (username) => {
        let path = `/homeDoctor/${username}`;
        navigate(path);
    }

    const completeDoctorProfile = (username) => {
        
        var profileDoctorInfo = {
            id: cuim,
            nume: nume,
            prenume: prenume,
            specializare: specializare,
            sediu: sediu,
            sala: sala
        }

        let axiosConfig = {
            headers: {
                'Content-Type': 'application/json',
                "Access-Control-Allow-Origin": "*",
                "Authorization": `Bearer ${window.localStorage.getItem("accessToken")}`,
            }
        };

        console.log("body");
        console.log(profileDoctorInfo);

        axios.post('http://localhost:8888/api/doctors/add/' + username, profileDoctorInfo, axiosConfig)
            .then((response) => {

                console.log(response.data);

                var linkDoctorProfile = {
                    username:username,
                    doctorId:cuim
                }

                console.log('link');
                console.log(linkDoctorProfile);

                axios.post('http://localhost:8888/api/doctors/link', linkDoctorProfile, axiosConfig)
                    .then((response) => {
                        
                        console.log(response.data);
                        alert("Profile completed successfully!");
                        routeToHomeDoctor(username["username"]);

                    })
                    .catch((err) => {
                        console.log(err.data);
                        alert("There were some errors! This profile couldn't be saved! Please retry!");
                    })
                
            })
            .catch((err) =>{
                console.log(err.data);
            })
    }

    return(
        <div>
        <FormContainer>
            <Marginer direction="vertical" margin="10rem"/>
            <Input width="15%" type="text" placeholder="Cuim*" onChange = {updateCuim}/> 
            <Input width="15%" type="text" placeholder="Nume*" onChange = {updateName}/> 
            <Input width="15%" type="text" placeholder="Prenume*" onChange = {updatePrenume}/>
            <Input width="15%" type="text" placeholder="Specializare*" onChange = {updateSpecializare}/>
            <Input width="15%" type="text" placeholder="Sediu*"  onChange = {updateSediu}/>
            <Input width="15%" type="text" placeholder="Sala*" onChange = {updateSala}/>
        </FormContainer>
        <SubmitButton width="10%" style={{ marginLeft:"865px"}} onClick={() => completeDoctorProfile(username["username"])}>Complete profile</SubmitButton>
        </div>
    );
}