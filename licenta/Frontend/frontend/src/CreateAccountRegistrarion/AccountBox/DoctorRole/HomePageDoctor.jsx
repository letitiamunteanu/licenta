import React from "react";
import { HeaderWebPageContainer } from "../Pages/HomePageStyle";
import { Button, BoxContainer } from "../utility";
import { Marginer } from "../marginer";
import { useNavigate, useParams } from "react-router-dom";

export function HomePageDoctor() {

    let username = useParams();
    let navigate = useNavigate();

    const routeToSeeProfile = (username) =>{
        let path = `/seeDoctorProfile/${username}`;
        console.log(username);
        navigate(path);
    }

    const routeToAccountSettings = (username) => {
        let path = `/accountSettings/${username}`;
        console.log("from acc settings");
        console.log(username);
        navigate(path);
    }

    const routeToMainAppPage = () => {
        let path = `/`;
        navigate(path);
    }

    const routeToSearchPatients = () => {
        let path = '/patientsInfo';
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

        <Marginer direction="vertical" margin="17rem"/>

        <BoxContainer>
            <Button width="250px" onClick={routeToSearchPatients}>See patients information</Button>
            <Button width="250px" onClick={( () => routeToSeeProfile(username["username"]))}>See your profile</Button>
            <Button width="250px">Medical management</Button>
            <Button width="250px" onClick={() => routeToAccountSettings(username["username"])}>Account settings</Button>
        </BoxContainer>  

        </div>
       
    );
}