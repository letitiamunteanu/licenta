import React from "react";
import { Button } from "../utility";
import { useNavigate } from "react-router-dom";

export function HeaderComponent() {

    let navigate = useNavigate(); 
    const routeToManageAccount = () =>{ 
        let path = `/login`; 
        navigate(path);
    }


    return (
        <header className="headerComponent">

            <Button type="submit" onClick = {routeToManageAccount}>Manage your account!</Button>

        </header>

    );
}

export default HeaderComponent;