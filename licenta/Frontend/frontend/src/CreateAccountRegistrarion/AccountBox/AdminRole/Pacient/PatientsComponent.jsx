import React, { useContext } from "react";
import { HomePageContainerMain } from "../../Pages/HomePageStyle";
import {Button} from "../../utility";
import { Marginer } from "../../marginer";
import { SearchBarPatient } from "./SearchBarPatient";
import { useNavigate } from "react-router-dom";
import { UsernameContext } from "../../../UsernameContext";

export function PatientsComponent() {

    const {usr} = useContext(UsernameContext);
    let navigate = useNavigate();
    const role = window.localStorage.getItem("role");

    const routeBack = () => {
        let path;
        if(role == 'admin') {
            path = `/adminPage`;
        }
        
        if(role == 'doctor'){
            path = `/homeDoctor/${usr}`;
        }

        navigate(path);
    }

    return(
        <div>
            <Button onClick={() => routeBack()} style={{ float:"left", color:"black", background:"#4e93f3af", width:"5%"}}>Back</Button>
            <Marginer direction="vertical" margin="10rem"/>
            <HomePageContainerMain>
                <SearchBarPatient/>
            </HomePageContainerMain>

        </div>        
    );

    
}