import {React, useEffect, useState} from "react";
import { Marginer } from "../../marginer";
import { Button, FormContainer, SubmitButton } from "../../utility";
import { Input } from "../../utility";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";


export function UpdateDoctorsForm(props) {

    const [nume, setDoctorNume] = useState('');
    const [prenume, setDoctorPrenume] = useState('');
    const [specializare, setDoctorSpecializare] = useState('');
    const [sediu, setDoctorSediu] = useState('');
    const [sala, setDoctorSala] = useState('');
    const id = useParams();

    const [ info, setInfo ] = useState([
        {
            Cuim: "",
            Nume: "",
            Prenume: "",
            Specializare: "",
            Sediu: "",
            Sala: ""
        }
    ]);

    let axiosPatchConfig = {
        headers: {
            'Content-Type': 'application/json',
            "Access-Control-Allow-Origin": "*",
            'Access-Control-Allow-Methods':'GET,PUT,POST,DELETE,PATCH,OPTIONS',
            'Access-Control-Allow-Headers': 'Origin, Content-Type, X-Auth-Token',
            // 'Access-Control-Allow-Credentials':false,
            "Authorization": `Bearer ${window.localStorage.getItem("accessToken")}`
        }
    };

    let axiosGetConfig = {
        headers: {
            'Content-Type': '*/*',
            "Access-Control-Allow-Origin": "*",
            "Authorization": `Bearer ${window.localStorage.getItem("accessToken")}`,
            "mode":"no-cors"
        }
    };

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

    useEffect(async () => {

        let username;
        let res = await axios.get('http://localhost:8889/api/jwt/decodeJwt', axiosGetConfig)
                .catch((err) => {
                    if(err == "Error: Request failed with status code 403"){
                        alert("403 forbidden");
    
                    }
                });

                if(res){
                    if(res.data){
                        username = res.data.split(" ")[0];
                    }
                }

        let res1 = await axios.get('http://localhost:8888/api/doctors/id/' + id["id"] + "/" + username, axiosGetConfig)
                    .catch((err) => {
                        alert(err);
                    });

            if(res1){
                if(res1.data){
                    setInfo({...info,
                        Cuim: res1.data["id"],
                        Nume: res1.data["nume"],
                        Prenume: res1.data["prenume"],
                        Sala: res1.data["sala"],
                        Specializare: res1.data["specializare"],
                        Sediu: res1.data["sediu"]

                    });

                    setDoctorNume(res1.data["nume"]);
                    setDoctorPrenume(res1.data["prenume"]);
                    setDoctorSpecializare(res1.data["specializare"]);
                    setDoctorSediu(res1.data["sediu"]);
                    setDoctorSala(res1.data["sala"]);
                }
            }

    }, []);

    let navigate = useNavigate();

    const routeToDoctors = () => {
        let path = "/doctorsInfo";
        navigate(path);
    }

    const updateDoctorInformation = async (id) => {
        var modificare = 0;
        var updateInfo = "{";
        var accountInfo = "{";

        if(nume != info.Nume){
            updateInfo += "\"nume\": \"" + nume + "\",";
            accountInfo += "\"lastName\": \"" + nume + "\",";
        }

        if(prenume != info.Prenume){
            updateInfo += "\"prenume\": \"" + prenume + "\",";
            accountInfo += "\"firstName\": \"" + prenume + "\",";
        }

        if(specializare != info.Specializare){
            updateInfo += "\"specializare\": \"" + specializare + "\",";
        }

        if(sediu != info.Sediu){
            updateInfo += "\"sediu\": \"" + sediu + "\",";
        }

        if(sala != info.Sala){
            updateInfo += "\"sala\": \"" + sala + "\",";
        }

        if(updateInfo.length != 1){
            updateInfo = updateInfo.slice(0, -1);
            updateInfo += "}";
            updateInfo = JSON.parse(updateInfo);
            modificare = 1;

            if(accountInfo.length != 1) {
                accountInfo = accountInfo.slice(0, -1);
                accountInfo += "}";
                accountInfo = JSON.parse(accountInfo);
            }
        }
        else{
            alert("No fields changes! No update done!");
        }
        
        if(modificare === 1){


            let username = "";
            let usernameForUpdate = "";

            let response = await axios.get('http://localhost:8889/api/jwt/decodeJwt', axiosGetConfig)
                    .catch((err) => {
                        if(err == "Error: Request failed with status code 403"){
                            alert("403 forbidden");
        
                        }
                    });

                if(response){
                    if(response.data){
                      
                        //console.log("DECODE JWT: ", response.data.split(" ")[1])
                        username = response.data.split(" ")[0];                
                    }
                }
            
            
            let res1 = await axios.get('http://localhost:8888/api/doctors/getUsername/' + info.Cuim + "/" + username, axiosGetConfig)
                        .catch((err) =>{
                            console.log("err");
                            console.log(err);
                        })
                
                if(res1){
                    if(res1.data){
                        console.log("for update");
                        console.log(res1.data);
                        usernameForUpdate = res1.data;
                    }
                }
                
            if(usernameForUpdate != ""){

                let res = await axios.patch('http://localhost:8888/api/doctors/update/' + id + "/" + username, updateInfo, axiosPatchConfig)
                            .catch((err) => {
                                console.log("err");
                                console.log(err);
                                alert("Nu s-a putut efectua update-ul!");
                                window.location.reload();
                            });

                if(res){
                    if(res.data){
                            console.log("res.data");
                            console.log(res.data);
                            console.log("S-a facut cu succes update-ul!");

                            if(accountInfo.length != 1) {
                                console.log("acc info");
                                console.log(accountInfo);

                                let url = 'http://localhost:8889/api/userController/updateUserAccount/' + usernameForUpdate;
                                let res2 = await axios.patch(url, accountInfo, axiosPatchConfig)
                                        .catch((err) => {
                                            console.log("err");
                                            console.log(err);
                                            alert("No account update done! There were some errors!");
                                        });
                                    if(res2){
                                        if(res2.data){
                                            alert("Successfully done the update!");
                                            routeToDoctors();
                                        }
                                    }
                            }
                            else{
                                alert("Successfully done the update!");
                                routeToDoctors();
                            }
                    }
                }

            }
            
   
        }
        
    }


    return (
        <div>
        <FormContainer>
            <Marginer direction="vertical" margin="15rem"/>
            <Input width="15%" type="text" placeholder="Nume" value={nume} onChange = {updateName}/> 
            <Input width="15%" type="text" placeholder="Prenume" value={prenume} onChange = {updatePrenume}/>
            <Input width="15%" type="text" placeholder="Specializare" value={specializare} onChange = {updateSpecializare}/>
            <Input width="15%" type="text" placeholder="Sediu" value={sediu} onChange = {updateSediu}/>
            <Input width="15%" type="text" placeholder="Sala" value={sala} onChange = {updateSala}/>
        </FormContainer>
        <SubmitButton width="10%" style={{ marginLeft:"865px"}} onClick={() => updateDoctorInformation(id["id"])}>Update</SubmitButton>
        </div>
    )
}