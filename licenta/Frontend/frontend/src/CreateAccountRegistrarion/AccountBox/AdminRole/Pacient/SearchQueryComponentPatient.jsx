import React from "react";
import styled from "styled-components";
import { Button, SubmitButton } from "../../utility";
import { useState } from "react";
import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";

const SearchQueryContainer = styled.div`
    width: 90%;
    height: 2em;
    display: flex;
    border-bottom: 1px solid #d8d8d852;
    padding: 6px 8px;
    padding-top: 2px;
    padding-bottom: 2px;
    background-color: #bbfaeb7d;
    align-items: center;
    border-radius: 15px;
    margin-bottom: 5px;
`;

export function SearchQueryComponentPatient(props){

    const { patient } = props;
    const role = window.localStorage.getItem("role");
    let navigate = useNavigate();

    const routeToUpdatePatient = (id) => {
        let path = `/updatePatients/${id}`;
        navigate(path);
    }

    let axiosGetConfig = {
        headers: {
            'Content-Type': '*/*',
            "Access-Control-Allow-Origin": "*",
            "Authorization": `Bearer ${window.localStorage.getItem("accessToken")}`
        }
    };

    let axiosDeleteConfig = {
        headers: {
            'Content-Type': 'application/json',
            "Access-Control-Allow-Origin": "*",
            "Authorization": `Bearer ${window.localStorage.getItem("accessToken")}`
        }
    };

    const doDelete = async (id) => {

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


        let res1 = await axios.delete('http://localhost:8888/api/patients/deletePatient/' + id + "/" + username, axiosGetConfig)
                            .catch(error => {
                                console.error('There was an error!', error.response);
                            });

            if(res1){
                if(res1.data){
                    
                    let usr = res1.data.split("!")[1];
                    
                    let res2 = await axios.delete('http://localhost:8889/api/userController/deleteUser/' + usr, axiosDeleteConfig)
                                .catch((err) => {
                                    console.log(err);
                                    alert(err.response.data);
                                });

                        if(res2){
                            if(res2.data){
                                console.log(res2.data);
                                alert(res2.data);
                                window.location.reload(false);
                            }
                        }
                }
            }

    }


    return(

        <SearchQueryContainer>
            <div>
                {patient.nume} &nbsp;
            </div>  

            <div>
                {patient.prenume}&nbsp;
            </div>
            
            <div style={{display:"flex", justifyContent:"right", marginLeft:"auto"}}>
                {role == 'admin' && <Button width="80px" type="submit" style={{ backgroundColor:"#eb862e"}} onClick={() => doDelete(patient.id)}>Delete</Button>}
                <Button width="200px" type="submit" style={{ backgroundColor:"#87e75a"}} onClick={() => routeToUpdatePatient(patient.id)}>See profile or Update</Button>
            </div>
        </SearchQueryContainer>
    )
}