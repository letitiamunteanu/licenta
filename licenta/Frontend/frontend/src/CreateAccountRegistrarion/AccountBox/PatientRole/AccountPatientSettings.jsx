import { React, useState, useContext } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { Marginer } from "../marginer";
import { BoxContainer, Button } from "../utility";
import axios from "axios";
import { UsernameContext } from "../../UsernameContext";

export function AccountPatientSettings() {

    const username = useParams();
    let navigate = useNavigate();
    const [updateButton, setUpdateButton] = useState(true);
    const {usr} = useContext(UsernameContext);
    const role = window.localStorage.getItem("role");

    const routeBack = () => {
        let path;
        if(role == 'admin') {
            path = `/adminPage`;
        }
        
        if(role == 'doctor'){
            path = `/homeDoctor/${usr}`;
        }

        if(role == 'pacient'){
            path = `/homePatient/${usr}`;
        }

        navigate(path);
    }

    const seeAccountDetails = (username) => {
        let path = `/seeAccountDetails/${username}`;
        navigate(path);
    }

    const routeToChangePassword = (username) => {
        let path = `/changePassword/${username}`;
        navigate(path);
    }

    const routeToHome = () => {
        let path = '/';
        navigate(path);
    }

    const doDelete = async (username) => {

        let axiosDeleteConfig = {
            headers: {
                'Content-Type': 'application/json',
                "Access-Control-Allow-Origin": "*",
                "Authorization": `Bearer ${window.localStorage.getItem("accessToken")}`
            }
        };
        
        
        let res = await axios.delete('http://localhost:8889/api/userController/deleteUser/' + username, axiosDeleteConfig)
                .catch((err) => {
                    console.log(err);
                })

                if(res){
                    if(res.data){
                        let res2 = await axios.delete('http://localhost:8888/api/patients/deletePatient/' + username, axiosDeleteConfig)
                                    .catch((err) => {
                                        console.log(err);
                                    });

                            if(res2){
                                if(res2.data){
                                    console.log(res2.data);
                                    alert(res2.data);
                                    routeToHome();
                                }
                            }
                    }
                }

    }

    return(
        <div>
            <Button onClick={() => routeBack()} style={{ float:"left", color:"black", background:"#4e93f3af", width:"5%"}}>Back</Button>
            <BoxContainer>
                <Marginer direction="vertical" margin="20rem"/>
                <Button width="200px" onClick={() => seeAccountDetails(username["username"])}>See account details</Button>
                <Button width="200px" onClick={() => routeToChangePassword(username["username"])}>Change your password</Button>
                <div>
                <input type="checkbox" id="account" name="account" style={{ }} onClick={() => setUpdateButton(!updateButton)}/>
                <button disabled={updateButton} 
                        style={{ width:'200px', padding:"5px", fontSize:"15px", fontWeight:"700", 
                        border:"none", borderRadius:"100px 100px 100px 100px", cursor:"pointer",
                        background:"#94e9e4", marginRight:"25px", marginTop:"5px",
                        transition:"all, 240ms ease-in-out"}} onClick={() => doDelete(username["username"])}>
                                                Delete account
                </button>
        
            </div>
            </BoxContainer>
        </div>
    );
}