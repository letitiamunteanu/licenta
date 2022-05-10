import {React, useEffect, useState } from "react";
import { Input,FormContainer, SubmitButton, Button} from "../utility";
import { Marginer } from "../marginer";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";

export function SeePatientProfile() {

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
    const [updateButton, setUpdateButton] = useState(true);
    let username = useParams();
    
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
            patientId: "",
            Nume: "",
            Prenume: "",
            Cnp: "",
            DataNastere: "",
            Varsta: "",
            GrupaSange: "",
            Greutate: "",
            Inaltime: "",
            NrTelefon: "",
            Simptome: "",
            Specificatii: ""
        }
    ])

    useEffect(async () => {

        let res = await axios.get('http://localhost:8888/api/patients/checkUserProfile/' + username["username"], axiosGetConfig)
                .catch((err) => {
                    console.log("AXIOS ERROR: ", err);
                });

                if(res){
                    if(res.data){
                        console.log(res.data);
                        var id = res.data.split("!")[1];
                        console.log(id);
                    }
                }

        let res2 = await axios.get('http://localhost:8888/api/patients/' + id + "/" + username["username"], axiosGetConfig)
                    .catch((err) => {
                        console.log("AXIOS ERROR: ", err);
                    });
                    
                    if(res2){
                        if(res2.data){
                            setInfo({...info,
                                patientId: res2.data["id"],
                                Nume: res2.data["nume"],
                                Prenume: res2.data["prenume"],
                                Cnp: res2.data["cnp"],
                                DataNastere: res2.data["dataNastere"],
                                Varsta: res2.data["varsta"],
                                GrupaSange:  res2.data["grupaSange"],
                                Greutate: res2.data["greutate"],
                                Inaltime: res2.data["inaltime"],
                                NrTelefon: res2.data["nrTelefon"],
                                Simptome: res2.data["simptome"],
                                Specificatii: res2.data["specificatii"]
                            });

                            setPacientNume(res2.data["nume"]);
                            setPacientPrenume(res2.data["prenume"]);
                            setPacientCnp(res2.data["cnp"]);
                            setPacientDataNastere(res2.data["dataNastere"]);
                            setPacientVarsta(res2.data["varsta"]);
                            setPacientGrupaSange( res2.data["grupaSange"]);
                            setPacientGreutate(res2.data["greutate"]);
                            setPacientInaltime(res2.data["inaltime"]);
                            setPacientNrTelefon(res2.data["nrTelefon"]);
                            setPacientSimptome(res2.data["simptome"]);
                            setPacientSpecificatii(res2.data["specificatii"]);
                        }
                    }
            
    },[]);

    let navigate = useNavigate();
    const routeToPatientHomePage = (username) => {
        let path = `/homePatient/${username}`
        navigate(path);
    }

    const seePatientProfileInfo = async (username) =>{

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

        if(cnp != info.Cnp){
            updateInfo += "\"cnp\": \"" + cnp + "\",";
        }

        if(dataNastere != info.DataNastere){
            updateInfo += "\"dataNastere\": \"" + dataNastere + "\",";
        }

        if(varsta != info.Varsta){
            updateInfo += "\"varsta\": \"" + varsta + "\",";
        }

        if(grupaSange != info.GrupaSange){
            updateInfo += "\"grupaSange\": \"" + grupaSange + "\",";
        }

        if(greutate != info.Greutate){
            updateInfo += "\"greutate\": \"" + greutate + "\",";
        }

        if(inaltime != info.Inaltime){
            updateInfo += "\"inaltime\": \"" + inaltime + "\",";
        }

        if(nrTelefon != info.NrTelefon){
            updateInfo += "\"nrTelefon\": \"" + nrTelefon + "\",";
        }

        if(simptome != info.Simptome){
            updateInfo += "\"simptome\": \"" + simptome + "\",";
        }

        if(specificatii != info.Specificatii){
            updateInfo += "\"specificatii\": \"" + specificatii + "\",";
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

            let res = await axios.patch('http://localhost:8888/api/patients/' + info.patientId + "/" + username, updateInfo, axiosPatchConfig)
                    .catch((err) =>{
                        console.log("err");
                        console.log(err);
                        alert("No profile update done! There were some errors!");
                    })
                if(res){
                    if(res.data){
                        console.log(res.data);
                        console.log("acc info");
                        console.log(accountInfo);
                        if(accountInfo.length != 1) {
                            let url = 'http://localhost:8889/api/userController/updateUserAccount/' + username;
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
                                        routeToPatientHomePage(username);
                                    }
                                }
                        }
                        else {
                            alert("Successfully updated the profile!");
                            routeToPatientHomePage(username);
                        }


                    }
                }
                
                

        }
    }


    return (
        <div>
        <Button onClick={() => routeToPatientHomePage(username["username"])} style={{ float:"left", color:"black", background:"#4e93f3af", width:"5%"}}>Back</Button>
        <FormContainer>
            <Marginer direction="vertical" margin="5rem"/>
            <div style={{display:"inline-block"}}>
                <label>Last name</label>
                <Input width="300px" type="text" placeholder="Nume*" value={nume} onChange = {updateName}/> 
            </div>
            <div  style={{display:"inline-block"}}>
                <label >First name</label>
                <Input width="300px" type="text" placeholder="Prenume*" value={prenume} onChange = {updatePrenume}/>
            </div>
            <div style={{display:"inline-block"}}>
                <label style={{marginLeft:'40px'}} >CNP </label>
                <Input  width="300px" type="text" placeholder="CNP*" value={cnp} onChange = {updateCnp}/>
            </div>

            <div style={{display:"inline-block"}}>
                <label style={{ fontSize:'15px', marginLeft:'5px'}}>Date of birth</label>
                <Input style={{marginRight:"20px"}} width="300px" type="text" placeholder="Data nasterii*"  value={dataNastere}  onChange = {updateDataNastere}/>
            </div>
            
            <div style={{display:"inline-block"}}>
                <label style={{ marginLeft:'50px'}}>Age</label>
                <Input width="300px" type="text" placeholder="Varsta*" value={varsta} onChange = {updateVarsta}/>
            </div>
            
            <div>
                <label style={{ marginLeft:'5px'}}>Blood type</label>
                <Input width="300px" type="text" placeholder="Grupa sange*" value={grupaSange} onChange = {updateGrupaSange}/>
            </div>
            
            <div>
                <label style={{ marginLeft:'30px'}}>Weight</label>
                <Input width="300px" type="text" placeholder="Greutate*"  value={greutate} onChange = {updateGreutate}/>
            </div>
            
            <div>
                <label style={{ marginLeft:'30px'}}>Height</label>
                <Input width="300px" type="text" placeholder="Inaltime*" value={inaltime}  onChange = {updateInaltime}/>
            </div>

            <div>
                <label>Phone Number</label>
                <Input style={{ marginRight:'35px'}} width="300px" type="text" placeholder="Nr telefon" value={nrTelefon} onChange = {updateNrTelefon}/>
            </div>
            
            <div>
                <label style={{ marginLeft:'5px'}}>Symptoms</label>
                <Input width="300px" type="text" placeholder="Simptome" value={simptome} onChange = {updateSimptome}/>
            </div>
            
            <div>
                <label>Specifications</label>
                <Input style={{ marginRight:'25px'}} width="300px" type="text" placeholder="Specificatii" value={specificatii} onChange = {updateSpecificatii}/>
            </div>
            
        </FormContainer>

            <div>
                <input type="checkbox" id="profile" name="profile" style={{ marginLeft:"880px"}} onClick={() => setUpdateButton(!updateButton)}/>
                <button width="10%" disabled={updateButton} style={{ marginLeft:"10px", padding:"5px", fontSize:"15px", fontWeight:"700", 
                                                            border:"none", borderRadius:"100px 100px 100px 100px", cursor:"pointer",
                                                            background:"#94e9e4", marginRight:"7px", marginTop:"5px",
                                                            transition:"all, 240ms ease-in-out"}} onClick={() => seePatientProfileInfo(username["username"])}>Update profile</button>
        
            </div>
        </div>
    );

}