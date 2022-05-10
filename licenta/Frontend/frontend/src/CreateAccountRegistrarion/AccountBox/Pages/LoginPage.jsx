import React from "react";
import styled from "styled-components";
import { AccountBox } from '../indexForm';
import { Button } from "../utility";
import { useNavigate } from "react-router-dom";

const AppContainer = styled.div `
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;

export function LoginPage() {

  let navigate = useNavigate();
  const routeToHome = () =>{ 
    let path = `/`; 
    navigate(path);
  }

  return(
    <div>
      <Button style={{ marginLeft:"1790px", backgroundColor:"#4ec1ee", width:"100px"}} onClick={routeToHome}>Home</Button>
      <AppContainer>
          <AccountBox/>
      </AppContainer>
    </div>
  )
}
