import {React, useEffect, useState} from "react";
import { Marginer } from "../../marginer";
import { FormContainer, SubmitButton } from "../../utility";
import { Input } from "../../utility";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";


export function UpdatePatientsForm(props) {

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
    const id = useParams();

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

    let axiosGetConfig = {
        headers: {
            'Content-Type': '*/*',
            "Access-Control-Allow-Origin": "*",
            "Authorization": `Bearer ${window.localStorage.getItem("accessToken")}`,
            "mode":"no-cors"
        }
    };

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
                    //console.log("DECODE JWT: ", res.data.split(" ")[1]);
                    username = res.data.split(" ")[0];
                }
            }

        let res2 = await axios.get('http://localhost:8888/api/patients/' + id["id"] + "/" + username, axiosGetConfig)       
            .catch((err) => {
                alert(err);
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

    }, []);

    let navigate = useNavigate();

    const routeToPacienti = () => {
        let path = "/patientsInfo";
        navigate(path);
    }

    const updatePacientInformation = async (id) => {

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
            updateInfo += "\"varsta\": " + varsta + ",";
        }

        if(grupaSange != info.GrupaSange){
            updateInfo += "\"grupaSange\": \"" + grupaSange + "\",";
        }

        if(greutate != info.Greutate){
            updateInfo += "\"greutate\": " + greutate + ",";
        }

        if(inaltime != info.Inaltime){
            updateInfo += "\"inaltime\": " + inaltime + ",";
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
                accountInfo = accountInfo.slice(0, -1);
                accountInfo += "}";
                accountInfo = JSON.parse(accountInfo);
            }
        }
        else{
            alert("No fields changes! No update done!");
        }
        

        if(modificare == 1){

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
                      
                        console.log("DECODE JWT: ", response.data.split(" ")[1])
                        username = response.data.split(" ")[0];
                    }
                }
            
            
            let res1 = await axios.get('http://localhost:8888/api/patients/getPatientUsername/' + id + "/" + username, axiosGetConfig)
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
                let res = await axios.patch('http://localhost:8888/api/patients/' + id + "/" + username, updateInfo, axiosPatchConfig)
                        .catch((err) =>{
                            console.log("err");
                            console.log(err);
                            alert("There were some errors! No update done!");
                            //window.location.reload();
                        });

                        if(res){
                            if(res.data){
                                if(accountInfo.length != 1) {
                                    console.log("acc info");
                                    console.log(accountInfo);
                                    let url = 'http://localhost:8889/api/userController/updateUserAccount/' + usernameForUpdate;
                                    let res1 = await axios.patch(url, accountInfo, axiosPatchConfig)
                                            .catch((err) => {
                                                console.log("err");
                                                console.log(err);
                                                alert("No account update done! There were some errors!");
                                            });
                                        if(res1){
                                            if(res1.data){
                                                alert("Successfully updated!");
                                                routeToPacienti();
                                            }
                                        }
                                }
                                else{
                                    alert("Successfully updated!");
                                    routeToPacienti();
                                }
                            }
                        }
            }
        }
        
    }


    return (
        <div>

        <FormContainer>
            <Marginer direction="vertical" margin="10rem"/>
            <Input width="15%" type="text" placeholder="Nume" value={nume} onChange = {updateName}/> 
            <Input width="15%" type="text" placeholder="Prenume" value={prenume} onChange = {updatePrenume}/>
            <Input width="15%" type="text" placeholder="CNP" value={cnp} onChange = {updateCnp}/>
            <Input width="15%" type="text" placeholder="Data nasterii" value={dataNastere} onChange = {updateDataNastere}/>
            <Input width="15%" type="text" placeholder="Varsta" value={varsta} onChange = {updateVarsta}/>
            <Input width="15%" type="text" placeholder="Grupa sange" value={grupaSange} onChange = {updateGrupaSange}/>
            <Input width="15%" type="text" placeholder="Greutate" value={greutate} onChange = {updateGreutate}/>
            <Input width="15%" type="text" placeholder="Inaltime" value={inaltime} onChange = {updateInaltime}/>
            <Input width="15%" type="text" placeholder="Nr telefon" value={nrTelefon} onChange = {updateNrTelefon}/>
            <Input width="15%" type="text" placeholder="Simptome" value={simptome} onChange = {updateSimptome}/>
            <Input width="15%" type="text" placeholder="Specificatii" value={specificatii} onChange = {updateSpecificatii}/>
        </FormContainer>
        <SubmitButton width="10%" style={{ marginLeft:"865px"}} onClick={() => updatePacientInformation(id["id"])}>Update</SubmitButton>
        </div>
    )
}