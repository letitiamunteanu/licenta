import React from "react";
import styled from "styled-components";
import { Button, SubmitButton } from "../../utility";
import axios from "axios";
import { useNavigate } from "react-router-dom";

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

export function SearchQueryComponent(props){

    const { doctor } = props;
    const role = window.localStorage.getItem("role");
    let navigate = useNavigate();

    const routeToUpdateDoctors = (id) => {
        let path = `/updateDoctors/${id}`;
        navigate(path);
    }

    let axiosDeleteConfig = {
        headers: {
            'Content-Type': 'application/json',
            "Access-Control-Allow-Origin": "*",
            "Authorization": `Bearer ${window.localStorage.getItem("accessToken")}`
        }
    };

    let axiosGetConfig = {
        headers: {
            'Content-Type': '*/*',
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
    
        let res1 = await axios.delete('http://localhost:8888/api/doctors/delete/' + id + "/" + username, axiosGetConfig)
                .catch(error => {
                    console.error('There was an error!', error);
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
                {doctor.nume} &nbsp;
            </div>  

            <div>
                {doctor.prenume}&nbsp;
            </div>
            
            <div>
                {doctor.specializare}&nbsp;
            </div>
            
            <div style={{display:"flex", justifyContent:"right", marginLeft:"auto"}}>
                {role == 'admin' && <Button width="50%" type="submit" style={{ backgroundColor:"#eb862e"}} onClick={() => doDelete(doctor.id)}>Delete</Button>}
                {role == 'admin' && <Button width="50%" type="submit" style={{ backgroundColor:"#87e75a"}} onClick={() => routeToUpdateDoctors(doctor.id)}>Update</Button>}
            </div>
        </SearchQueryContainer>
    )
}