import axios from "axios";
import { React, useEffect, useState} from "react";
import { useNavigate, useParams } from "react-router-dom";
import { BoxContainer, Input, Button } from "../utility";
import { Marginer } from "../marginer";

export function SeeAccountDetails ()  {

    const [lastname, setLastName] = useState('');
    const [firstname, setFirstName] = useState('');
    const [updateButton, setUpdateButton] = useState(true);
    let username = useParams();
    let navigate = useNavigate();

    const updateFirstName = (props) => {
        setFirstName(props.target.value);
        console.log("ln:" + props.target.value);
    }

    const updateLastName = (props) => {
        setLastName(props.target.value);
        console.log("fn:" + props.target.value);
    }

    const routeToAccountSettings = (username) => {
        let path = `/accountSettings/${username}`
        navigate(path);
    }


    const [ info, setInfo ] = useState([
        {
            Username: "",
            Email: "",
            Role: "",
            FirstName: "",
            LastName: ""
        }
    ])

    let axiosGetConfig = {
        headers: {
            'Content-Type': '*/*',
            "Access-Control-Allow-Origin": "*",
            "Authorization": `Bearer ${window.localStorage.getItem("accessToken")}`,
        }
    };

    useEffect(async () => {

        let res = await axios.get('http://localhost:8889/api/userController/getUserByUsername/' + username["username"], axiosGetConfig)
            .catch((err) => {
                console.log(err.data);
            })

            if(res){
                if(res.data){

                    setFirstName(res.data["firstName"]);
                    setLastName(res.data["lastName"])

                    setInfo({...info,
                        Username: res.data["username"],
                        Email: res.data["email"],
                        Role: res.data["role"],
                        FirstName: res.data["firstName"],
                        LastName: res.data["lastName"]
                    });
                }
            }
    },[]);

    const seePatientProfileInfoOrUpdate = async (username) =>{
        var modificare = 0;
        var updateInfo = "{";
        var profileInfo = "{";

        if(firstname != info.FirstName){
            updateInfo += "\"firstName\": \"" + firstname + "\",";
            profileInfo += "\"prenume\": \"" + firstname + "\",";
        }

        if(lastname != info.LastName){
            updateInfo += "\"lastName\": \"" + lastname + "\",";
            profileInfo += "\"nume\": \"" + lastname + "\",";
        }

        if(updateInfo.length != 1){
            updateInfo = updateInfo.slice(0, -1);
            profileInfo = profileInfo.slice(0, -1);
            updateInfo += "}";
            profileInfo += "}";
            
            updateInfo = JSON.parse(updateInfo);
            profileInfo = JSON.parse(profileInfo);
            modificare = 1;
        } else{

            alert("No field changed! No update done!");
            setUpdateButton(true);
            document.getElementById("account").checked = false;
        }

        if(modificare === 1){

            let axiosPatchConfig = {
                headers: {
                    'Content-Type': 'application/json',
                    "Access-Control-Allow-Origin": "*",
                    'Access-Control-Allow-Methods':'GET,PUT,POST,DELETE,PATCH,OPTIONS',
                    'Access-Control-Allow-Headers': 'Origin, Content-Type, X-Auth-Token',
                    // 'Access-Control-Allow-Credentials':false,
                    "Authorization": `Bearer ${window.localStorage.getItem("accessToken")}`,
                    'mode':'no-cors'
                }
            };

            
            let url = 'http://localhost:8889/api/userController/updateUserAccount/' + username;
            let res = await axios.patch(url, updateInfo, axiosPatchConfig)
                            .catch((err) => {
                                console.log("err");
                                console.log(err);
                                alert("No account update done! There were some errors!");
                            });
                
                if(res){
                    if(res.data){
                        console.log("res.data");
                        console.log(res.data); 
                    }
                }
            
            if(window.localStorage.getItem("role") == "pacient"){
                let id;
                let res2 = await axios.get('http://localhost:8888/api/patients/checkUserProfile/' + username, axiosGetConfig)
                                    .catch((err) => {
                                        console.log("AXIOS ERROR: ", err);
                                    });

                    if(res2){
                        if(res2.data){
                            console.log(res2.data);
                            id = res2.data.split("!")[1];
                            console.log(id);
                        }
                    }
            
                let res3 = await axios.patch('http://localhost:8888/api/patients/' + id + "/" + username, profileInfo, axiosPatchConfig)
                                .catch((err) => {
                                    console.log("err");
                                    console.log(err);
                                    alert("No profile update done! There were some errors!");
                                });

                    if(res3){
                        if(res3.data){
                            console.log("res2.data");
                            console.log(res2.data); 
                        }
                    }
            
                alert("Successfully updated the account!");
                routeToAccountSettings(username);

            }

            if(window.localStorage.getItem("role") == "doctor"){

                let cuim;
                let res2 = await axios.get('http://localhost:8888/api/doctors/checkDrProfile/' + username, axiosGetConfig)
                                    .catch((err) => {
                                        console.log("AXIOS ERROR: ", err);
                                    });

                    if(res2){
                        if(res2.data){
                            cuim = res2.data.split("!")[1];
                            console.log(cuim);
                        }
                    }
                
                let res3 = await axios.patch('http://localhost:8888/api/doctors/update/' + cuim + "/" + username, profileInfo, axiosPatchConfig)
                                .catch((err) => {
                                    console.log("err");
                                    console.log(err);
                                    alert("No profile update done! There were some errors!");
                                });

                    if(res3){
                        if(res3.data){
                            console.log("res2.data");
                            console.log(res2.data); 
                        }
                    }
            }   
        }
    }
    
    return (    
        <div>
            <Button onClick={() => routeToAccountSettings(username["username"])} style={{ float:"left", color:"black", background:"#4e93f3af", width:"5%"}}>Back</Button>
            <BoxContainer>
                <Marginer direction="vertical" margin="14rem"/>
                <div style={{display:"inline-block"}}>
                    <label>Username</label>
                    <Input width="300px" type="text" placeholder="Username" value={info.Username} readOnly/> 
                </div>

                <div style={{display:"inline-block"}}>
                    <label>First Name</label>
                    <Input width="300px" type="text" placeholder="Username" value={firstname} onChange={updateFirstName}/> 
                </div>


                <div style={{display:"inline-block"}}>
                    <label>Last Name</label>
                    <Input width="300px" type="text" placeholder="Username" value={lastname} onChange={updateLastName}/> 
                </div>

                <div style={{display:"inline-block"}}>
                    <label style={{marginLeft:'35px'}}>Email</label>
                    <Input width="300px" type="text" placeholder="Username" value={info.Email} readOnly/> 
                </div>

                <div style={{display:"inline-block"}}>
                    <label style={{marginLeft:'40px'}}>Role</label>
                    <Input width="300px" type="text" placeholder="Username" value={info.Role} readOnly/> 
                </div>

            </BoxContainer>
            <div>
                <input type="checkbox" id="account" name="account" style={{ marginLeft:"880px"}} onClick={() => setUpdateButton(!updateButton)}/>
                <button width="10%"  disabled={updateButton} 
                        style={{ marginLeft:"10px", padding:"5px", fontSize:"15px", fontWeight:"700", 
                        border:"none", borderRadius:"100px 100px 100px 100px", cursor:"pointer",
                        background:"#94e9e4", marginRight:"7px", marginTop:"5px",
                        transition:"all, 240ms ease-in-out"}} onClick={() => seePatientProfileInfoOrUpdate(username["username"])}>
                                                Update account
                </button>
        
            </div>
        </div>
    );
}