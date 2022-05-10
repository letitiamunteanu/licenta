import React from "react";
import { HomePageContainerMain } from "./HomePageStyle";
import {Button} from "../utility";
import { Marginer } from "../marginer";
import { useNavigate } from "react-router-dom";
import { HeaderWebPageContainer } from "./HomePageStyle";

export function AdminPage() {
    
    let navigate = useNavigate();

    const routeGetDoctorInformation = () =>{ 
        let path = `/doctorsInfo`; 
        navigate(path);
    }

    const routeGetPatientsInformation = () =>{ 
        let path = `/patientsInfo`; 
        navigate(path);
    }

    const routeToManageMedicalInfo = () =>{ 
        let path = `/medicalInfo`; 
        navigate(path);
    }

    const routeToMainAppPage = () => {
        let path = `/`
        navigate(path);
    }

    const doLogout = () => {
        window.localStorage.clear();
        routeToMainAppPage();
    }
    return(
        <div>
             <HeaderWebPageContainer style={{ display:"inline-block"}}>
                <Button width="5%" type="submit" style={{ backgroundColor:"#4e93f3af"}} onClick={doLogout}>Logout</Button>
            </HeaderWebPageContainer>

            <Marginer direction="vertical" margin="23rem"/>
            <HomePageContainerMain>
                    
                    <Button width = "70%" onClick={routeGetDoctorInformation}>Doctors Information</Button>
                    <Marginer direction="vertical" margin="0.5rem"/>
                    <Button width = "70%" onClick={routeGetPatientsInformation}>Patients Information</Button>
                    <Marginer direction="vertical" margin="0.5rem"/>
                    <Button width = "70%" onClick={routeToManageMedicalInfo}>Add Medical Information</Button>
                    <Marginer direction="vertical" margin="0.5rem"/>
                    <Button width = "70%" onClick={routeToManageMedicalInfo}>Get Medical Information</Button>

            </HomePageContainerMain>

        </div>        
    );

    
}