import React from "react";
import { useNavigate, useParams } from "react-router-dom";
import { BoxContainer, Button } from "../utility";
import { HeaderWebPageContainer } from "../Pages/HomePageStyle";
import { Marginer } from "../marginer";

export function HomePagePatient(){

    let username = useParams();
    let navigate = useNavigate();
    const routeToHome = () =>{
        let path = '/';
        navigate(path);
    }

    const routeToSeeProfile = (username) =>{
        let path = `/seePatientProfile/${username}`;
        console.log(username);
        navigate(path);
    }

    const routeToMainAppPage = () => {
        let path = `/`
        navigate(path);
    }

    const routeToAccountSettings = (username) => {
        let path = `/accountSettings/${username}`
        navigate(path);
    }

    const doLogout = () => {
        window.localStorage.clear();
        routeToMainAppPage();
    }

    return (
        <div>
        <HeaderWebPageContainer style={{ display:"inline-block"}}>
            {/* <Button width="5%" type="submit" style={{ backgroundColor:"#4e93f3af"}} onClick={routeToHome}>Home</Button> */}
            <Button width="5%" type="submit" style={{ backgroundColor:"#4e93f3af"}} onClick={doLogout}>Logout</Button>
        </HeaderWebPageContainer>

        <Marginer direction="vertical" margin="17rem"/>

        <BoxContainer>
            <Button width="250px">See your appointments</Button>
            <Button width="250px" onClick={( () => routeToSeeProfile(username["username"]))}>See your profile</Button>
            <Button width="250px">Upload your analysis</Button>
            <Button width="250px" onClick={() => routeToAccountSettings(username["username"])}>Account settings</Button>
        </BoxContainer>  

        </div>
    );
}