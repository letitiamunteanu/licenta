import React from "react";
import { HomePageContainerMain } from "../../Pages/HomePageStyle";
import { Marginer } from "../../marginer";
import { SearchBar } from "./SearchBarDoctor";
import { useNavigate } from "react-router-dom";
import { Button } from "../../utility";

export function DoctorsComponent() {
    
    let navigate = useNavigate();
    const routeBack = () => {
        let path = `/adminPage`
        navigate(path);
    }
    return(
        <div>
            <Button onClick={() => routeBack()} style={{ float:"left", color:"black", background:"#4e93f3af", width:"5%"}}>Back</Button>
            <Marginer direction="vertical" margin="10rem"/>
            <HomePageContainerMain>     
                <SearchBar/>
            </HomePageContainerMain>

        </div>        
    );

    
}