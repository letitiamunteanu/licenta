import {React, useState} from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";
import { FormContainer, SubmitButton, Input } from "../utility";
import { Marginer } from "../marginer";


export function PatientProfileForm(props) {

    const [nume, setPacientNume] = useState('');
    const [prenume, setPacientPrenume] = useState('');
    const [cnp, setPacientCnp] = useState('');
    const [dataNastere, setPacientDataNastere] = useState('');
    const [varsta, setPacientVarsta] = useState('');
    const [grupaSange, setPacientGrupaSange] = useState('');
    const [greutate, setPacientGreutate] = useState('');
    const [inaltime, setPacientInaltime] = useState('');
    const [nrTelefon, setPacientNrTelefon] = useState('');
    const [simptome, setPacientSimptome] = useState('');
    const [specificatii, setPacientSpecificatii] = useState('');
    const username = useParams();
    const navigate = useNavigate();

    const updateName = (props) => {
        setPacientNume(props.target.value);
        console.log("nume:" + props.target.value);
    }

    const updatePrenume = (props) => {
        setPacientPrenume(props.target.value);
        console.log("prenume:" + props.target.value);
    }

    const updateCnp = (props) => {
        setPacientCnp(props.target.value);
        console.log("cnp:" + props.target.value);
    }

    const updateDataNastere = (props) => {
        setPacientDataNastere(props.target.value);
        console.log("data nastere:" + props.target.value);
    }

    const updateVarsta = (props) => {
        setPacientVarsta(props.target.value);
        console.log("varsta:" + props.target.value);
    }

    const updateGrupaSange = (props) => {
        setPacientGrupaSange(props.target.value);
        console.log("grupa sange:" + props.target.value);
    }

    const updateGreutate = (props) => {
        setPacientGreutate(props.target.value);
        console.log("sala:" + props.target.value);
    }

    const updateInaltime = (props) => {
        setPacientInaltime(props.target.value);
        console.log("inaltime:" + props.target.value);
    }

    const updateNrTelefon = (props) => {
        setPacientNrTelefon(props.target.value);
        console.log("nr telefon :" + props.target.value);
    }

    const updateSimptome = (props) => {
        setPacientSimptome(props.target.value);
        console.log("simptome:" + props.target.value);
    }

    const updateSpecificatii = (props) => {
        setPacientSpecificatii(props.target.value);
        console.log("specificatii:" + props.target.value);
    }

    const routeToHomePatient = (username) => {
        let path = `/homePatient/${username}`;
        navigate(path);
    }


    const completePacientProfile = (username) => {
        
        var profilePatientInfo = {
            patientId: "",
            nume: nume,
            prenume: prenume,
            cnp: cnp,
            dataNastere: dataNastere,
            varsta: varsta,
            grupaSange: grupaSange,
            greutate: greutate,
            inaltime: inaltime,
            nrTelefon: nrTelefon,
            simptome: simptome,
            specificatii: specificatii
        }

        let axiosConfig = {
            headers: {
                'Content-Type': 'application/json',
                "Access-Control-Allow-Origin": "*",
                "Authorization": `Bearer ${window.localStorage.getItem("accessToken")}`,
            }
        };

        axios.post('http://localhost:8888/api/patients/' + username, profilePatientInfo, axiosConfig)
            .then((response) => {

                console.log(response.data);

                var linkUserProfile = {
                    username:username,
                    pacientId:response.data["id"]
                }
                console.log('link');
                console.log(linkUserProfile)
                axios.post('http://localhost:8888/api/patients/link', linkUserProfile, axiosConfig)
                    .then((response) => {
                        
                        console.log(response.data);
                        alert("Profile completed successfully!");
                        routeToHomePatient(username);

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


    return (
        <div>
        <FormContainer>
            <Marginer direction="vertical" margin="10rem"/>
            <Input width="15%" type="text" placeholder="Nume*" onChange = {updateName}/> 
            <Input width="15%" type="text" placeholder="Prenume*" onChange = {updatePrenume}/>
            <Input width="15%" type="text" placeholder="CNP*" onChange = {updateCnp}/>
            <Input width="15%" type="text" placeholder="Data nasterii*"  onChange = {updateDataNastere}/>
            <Input width="15%" type="text" placeholder="Varsta*" onChange = {updateVarsta}/>
            <Input width="15%" type="text" placeholder="Grupa sange*" onChange = {updateGrupaSange}/>
            <Input width="15%" type="text" placeholder="Greutate*"  onChange = {updateGreutate}/>
            <Input width="15%" type="text" placeholder="Inaltime*"  onChange = {updateInaltime}/>
            <Input width="15%" type="text" placeholder="Nr telefon"  onChange = {updateNrTelefon}/>
            <Input width="15%" type="text" placeholder="Simptome"  onChange = {updateSimptome}/>
            <Input width="15%" type="text" placeholder="Specificatii" onChange = {updateSpecificatii}/>
        </FormContainer>
        <SubmitButton width="10%" style={{ marginLeft:"865px"}} onClick={() => completePacientProfile(username["username"])}>Complete profile</SubmitButton>
        </div>
    )
}