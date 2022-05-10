import {React, useEffect, useState, useContext } from "react";
import { Input,FormContainer, SubmitButton, Button} from "../utility";
import { Marginer } from "../marginer";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";
import { UsernameContext } from "../../UsernameContext";

export function SeeDoctorProfile() {

    const [cuim, setDoctorCuim] =  useState('');
    const [nume, setDoctorNume] = useState('');
    const [prenume, setDoctorPrenume] = useState('');
    const [specializare, setDoctorSpecializare] = useState('');
    const [sediu, setDoctorSediu] = useState('');
    const [sala, setDoctorSala] = useState('');
    const {usr} = useContext(UsernameContext);
    const [updateButton, setUpdateButton] = useState(true);
    let username = useParams();
    let navigate = useNavigate();
    
    const updateCuim = (props) => {
        setDoctorCuim(props.target.value);
        console.log("cuim:" + props.target.value);
    }

    const updateNume = (props) => {
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
        console.log("varsta:" + props.target.value);
    }

   
    let axiosGetConfig = {
        headers: {
            'Content-Type': 'text/plain',
            "Access-Control-Allow-Origin": "*",
            'Authorization': `Bearer ${window.localStorage.getItem("accessToken")}`
        }
    };

    let axiosPatchConfig = {
        headers: {
            'Content-Type': 'application/json',
            "Access-Control-Allow-Origin": "*",
            'Access-Control-Allow-Methods':'GET,PUT,POST,DELETE,PATCH,OPTIONS',
            'Access-Control-Allow-Headers': 'Origin, Content-Type, X-Auth-Token',
            // 'Access-Control-Allow-Credentials':false,
            "Authorization": `Bearer ${window.localStorage.getItem("accessToken")}`,
            "mode":"no-cors"
        }
    };
    
    const [ info, setInfo ] = useState([
        {
            Cuim: "",
            Nume: "",
            Prenume: "",
            Specializare: "",
            Sala: "",
            Sediu: ""
        }
    ])

    useEffect(async () => {

        let res = await axios.get('http://localhost:8888/api/doctors/checkDrProfile/' + usr, axiosGetConfig)
                .catch((err) => {
                    console.log("AXIOS ERROR: ", err);
                });

                if(res){
                    if(res.data){
                        var id = res.data.split("!")[1];
                    }
                }

        let res2 = await axios.get('http://localhost:8888/api/doctors/id/' + id + "/" + usr, axiosGetConfig)
                    .catch((err) => {
                        console.log("AXIOS ERROR: ", err);
                    });
                    
                    if(res2){
                        if(res2.data){
                            setInfo({...info,
                                Cuim: res2.data["id"],
                                Nume: res2.data["nume"],
                                Prenume: res2.data["prenume"],
                                Specializare: res2.data["specializare"],
                                Sediu: res2.data["sediu"],
                                Sala: res2.data["sala"],
                            });

                            setDoctorCuim(res2.data["cuim"]);
                            setDoctorNume(res2.data["nume"]);
                            setDoctorPrenume(res2.data["prenume"]);
                            setDoctorSpecializare(res2.data["specializare"]);
                            setDoctorSediu(res2.data["sediu"]);
                            setDoctorSala(res2.data["sala"]);
                        }
                    }
            
    },[]);

    
    const routeBack = (usr) => {
        let path = `/homeDoctor/${usr}`
        navigate(path);
    }

    const seeDoctorProfileInfo = async (usr) =>{

        var modificare = 0;
        var updateInfo = "{";
        var accountInfo = "{";

        console.log("din update ");
        console.log(info);

        if(nume != info.Nume){
            updateInfo += "\"nume\": \"" + nume + "\",";
            accountInfo += "\"lastName\": \"" + nume + "\",";
        }

        if(prenume != info.Prenume){
            updateInfo += "\"prenume\": \"" + prenume + "\",";
            accountInfo += "\"firstName\": \"" + prenume + "\",";
        }

        if(specializare != info.Specializare){
            updateInfo += "\"cnp\": \"" + specializare + "\",";
        }

        if(sediu != info.Sediu){
            updateInfo += "\"dataNastere\": \"" + sediu + "\",";
        }

        if(sala != info.Sala){
            updateInfo += "\"varsta\": \"" + sala + "\",";
        }

        if(updateInfo.length != 1){
            updateInfo = updateInfo.slice(0, -1);
            updateInfo += "}";
            updateInfo = JSON.parse(updateInfo);
            modificare = 1;
            
            if(accountInfo.length != 1) {
                console.log("am intrat aici");
                accountInfo = accountInfo.slice(0, -1);
                accountInfo += "}";
                accountInfo = JSON.parse(accountInfo);
    
                console.log("acc info");
                console.log(accountInfo);
            }
        }
        else{
            alert("No changed fields! No update done!");
            setUpdateButton(true);
            document.getElementById("profile").checked = false;
        }

        if(modificare === 1){

            let res = await axios.patch('http://localhost:8888/api/doctors/update/' + info.Cuim + "/" + usr, updateInfo, axiosPatchConfig)
                    .catch((err) =>{
                        console.log("err");
                        console.log(err);
                        alert("No profile update done! There were some errors!");
                    });

                if(res){
                    if(res.data){
                        console.log(res.data);
                        console.log("acc info");
                        console.log(accountInfo);

                        if(accountInfo.length != 1) {
                            let url = 'http://localhost:8889/api/userController/updateUserAccount/' + usr;
                            let response =  await axios.patch(url, accountInfo, axiosPatchConfig)
                                    .catch((err) => {
                                        console.log("err");
                                        console.log("Error from update account after update profile!");
                                        console.log(err);
                                        alert("No account update done! There were some errors!");
                                    });

                                if(response){
                                    if(response.data){
                                        console.log("res2 data");
                                        console.log(response.data);
                                        alert("Successfully updated the profile!");
                                        routeBack(username);
                                    }
                                }
                        }
                        else {
                            alert("Successfully updated the profile!");
                            routeBack(username);
                        }


                    }
                }
                
                

        }
    }


    return (
        <div>
        <Button onClick={() => routeBack(usr)} style={{ float:"left", color:"black", background:"#4e93f3af", width:"5%"}}>Back</Button>
        <Marginer direction="vertical" margin="10rem"/>
        <FormContainer>
            <Marginer direction="vertical" margin="5rem"/>
            <div style={{display:"inline-block"}}>
                <label>Last name</label>
                <Input width="300px" type="text" placeholder="Nume*" value={nume} onChange = {updateNume}/> 
            </div>
            <div  style={{display:"inline-block"}}>
                <label >First name</label>
                <Input width="300px" type="text" placeholder="Prenume*" value={prenume} onChange = {updatePrenume}/>
            </div>
            <div style={{display:"inline-block"}}>
                <label  > Specializare </label>
                <Input style={{ marginRight:'20px'}}  width="300px" type="text" placeholder="Specializare*" value={specializare} onChange = {updateSpecializare}/>
            </div>

            <div style={{display:"inline-block"}}>
                <label style={{ fontSize:'15px', marginLeft:'35px'}}> Sediu </label>
                <Input width="300px" type="text" placeholder="Sediu*"  value={sediu}  onChange = {updateSediu}/>
            </div>
            
            <div style={{display:"inline-block"}}>
                <label style={{ marginLeft:'45px'}}> Sala </label>
                <Input width="300px" type="text" placeholder="Sala*" value={sala} onChange = {updateSala}/>
            </div>
            
        </FormContainer>

            <div>
                <input type="checkbox" id="profile" name="profile" style={{ marginLeft:"880px"}} onClick={() => setUpdateButton(!updateButton)}/>
                <button width="10%" disabled={updateButton} style={{ marginLeft:"10px", padding:"5px", fontSize:"15px", fontWeight:"700", 
                                                            border:"none", borderRadius:"100px 100px 100px 100px", cursor:"pointer",
                                                            background:"#94e9e4", marginRight:"7px", marginTop:"5px",
                                                            transition:"all, 240ms ease-in-out"}} onClick={() => seeDoctorProfileInfo(usr)}>Update profile</button>
        
            </div>
        </div>
    );

}
