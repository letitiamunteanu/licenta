import React, { useState } from "react";
import styled from "styled-components";
import { LoginForm } from "./Forms/loginForm";
import {motion} from "framer-motion";
import { AccountContext } from "./accountContext";
import { SignUpForm } from "./Forms/signupForm";

const BoxContainer = styled.div`
    width: 500px; //pot modifica
    min-height: 550px;
    display: flex;
    flex-direction: column;
    border-radius: 19px;
    position: relative;
    //overflow: hidden;
    margin: 20px;
    z-index: 10;

`;

const TopContainer = styled.div`
    width: 100%;
    height: 250px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    padding: 0 1.8em;
    padding-bottom: 5em;
    align-items:center;
`;

const BackDrop = styled(motion.div)`
    width: 160%;
    height: 400px;
    position: absolute;
    display: flex;
    flex-direction: column;
    border-radius: 50%;
    transform: rotate(60deg);
    top: -200px;
    left: -375px;
    background: #c2f3ef73;
    border: solid 0.5px #15abbe;
    
`;

const HeaderContainer = styled.div`
    width: 50%;
    display: flex;
    flex-direction: column;
    text-align:center;
    align-content: center;
    z-index:10 ;
    justify-content: center ;
    margin-right: 190px;
    margin-top:10rem;
    
`;


export const HeaderText = styled.h2`
    font-size: 30px;
    font-weight: 400;
    line-height: 1.24;
    color: #010002;
    z-index: 10;
    margin: 0;
`;

const SmallText = styled.h5`
    color: #0e0101;
    font-weight: 500;
    font-size: 15px;
    z-index: 10;
    margin: 0;
    line-height: 1.6;
    text-align: center;
    align-items: center;
`;

const InnerContainer = styled.div`
    width: 100%;
    height: 500px;
    display: flex;
    flex-direction: column;
    padding:0 0.1em;
`;

const backDropVariants = {

    expanded: {
        width: "250%",
        height: "1050px",
        borderRadius: "20%",
        transform: "rotate(180deg)"
    },

    collapsed: {
        width: "250%",
        height: "1050px",
        borderRadius: "50%",
        transform: "rotate(180deg)"
    }

}

const expandingTransitions = {
    type: "spring",
    duration: 2.3,
    stiffness: 30, 
};

export function AccountBox(props){

    const [isExpanded, setExpanded] = useState(false);
    const [active, setActive] = useState("signin");

    const playExpandingAnimation = () =>{
        setExpanded(true);
        setTimeout(() => {
            setExpanded(false)
        }, expandingTransitions.duration * 1000 - 1500);
    };

    const switchToSignUp = () => {
        playExpandingAnimation();
        setTimeout(() => {
            setActive("signup");
        }, 400)
    }

    const switchToSignIn = () => {
        playExpandingAnimation();
        setTimeout(() => {
            setActive("signin");
        }, 400)
    }

    const contexValue = {switchToSignUp, switchToSignIn};

    return (
    <AccountContext.Provider value={contexValue}>
        <BoxContainer>
            <TopContainer>
                <BackDrop 
                    initial={false} 
                    animate={isExpanded ? "expanded" : "collapsed"}  
                    variants={backDropVariants}
                    transition={expandingTransitions}
                />

                {active === "signin" && <HeaderContainer>
                    <HeaderText>Welcome!</HeaderText>
                    <SmallText>Please sign in to continue!</SmallText>
                </HeaderContainer>}

                {active === "signup" && <HeaderContainer>
                    <HeaderText>Create Account</HeaderText>
                    <SmallText>Please sign-up to continue!</SmallText>
                </HeaderContainer>}

            </TopContainer>
            <InnerContainer>
                {active === "signin" && <LoginForm/>}
                {active === "signup" && <SignUpForm/>}
            </InnerContainer>
        </BoxContainer>
    </AccountContext.Provider>
    );
}