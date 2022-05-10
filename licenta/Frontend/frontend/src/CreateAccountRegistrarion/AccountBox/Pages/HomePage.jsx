import React from "react";
import HeaderComponent from "../Components/HeaderComponent";
import { HomePageContainerMain, WelcomeText, HeaderWebPageContainer } from "./HomePageStyle";
import {Button} from "../utility";
import { Marginer } from "../marginer";
import { useNavigate } from "react-router-dom";
import axios from 'axios';

export function HomePage() {
    
    let navigate = useNavigate(); 
    const routeToSeeSpecializations = () =>{ 
        let path = `/specializations`; 
        navigate(path);
    }

    const routeToIntroduceSymptoms = () =>{ 
        let path = '/symptomsData'; 
        navigate(path);
    }


    return(
        <div>

            <HeaderWebPageContainer>
                <HeaderComponent/>
            </HeaderWebPageContainer>

            <Marginer direction="vertical" margin="7rem"/>
            <WelcomeText>Welcome!</WelcomeText>
            <Marginer direction="vertical" margin="7rem"/>
            
            <HomePageContainerMain>
                    
                    <Button width = "70%" onClick={routeToSeeSpecializations}>See the specializations</Button>
                    <Marginer direction="vertical" margin="0.5rem"/>
                    <Button width = "70%">Search the doctors</Button>
                    <Marginer direction="vertical" margin="0.5rem"/>
                    <Button width = "70%" onClick={routeToIntroduceSymptoms}>Introduce your symptoms</Button>

            </HomePageContainerMain>

        </div>        
    );
    
}