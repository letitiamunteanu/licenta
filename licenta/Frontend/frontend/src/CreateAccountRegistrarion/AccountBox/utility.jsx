import styled from "styled-components";

export const BoxContainer = styled.div`
    //border: ${props => props.border ? props.border : 'auto'};
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-top: 10px;
    z-index: 10;
`;

export const FormContainer = styled.form`
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
   
`;

export const MutedLink = styled.a`
    font-size: 12px;
    color: #070606cc;
    font-weight: 500;
    text-decoration: none;
`;

export const BoldLink = styled.a`
    font-size: 12px;
    color: rgb(3, 3, 3);
    font-weight: 500;
    text-decoration: none;
    margin: 0 4px;
`;

export const Input = styled.input`
    width: ${props => props.width ? props.width : 'auto'};
    //width: 60%;
    height: 42px;
    outline: none;
    border: 1px solid rgba(200, 200, 200, 0.3);
    padding: 0px 10px;
    border-bottom: 1.4px solid transparent;
    transition: all 200ms ease-in-out;
    font-size: 10px;
    border-radius: 27px;
    margin: 7px;

    &::placeholder{
        color: rgba(200,200,200,1);
    }

    &:not(:last-of-type){
        border-bottom: 1.5px solid rgba(200,200,200, 0.4);
    }

    &:focus{
        outline: none;
        border-bottom: 2px solid #aee2f7;
    }

`;

export const SubmitButton = styled.button`
    width: ${props => props.width ? props.width : 'auto'};
    //width: 45%;
    padding: 10px;
    color: #fff;
    font-size: 15px;
    font-weight: 600;
    border: none;
    border-radius: 100px 100px 100px 100px;
    cursor: pointer;
    transition: all, 240ms ease-in-out;
    background: #90d9f7;
    
    &:hover{
        filter: brightness(1.03);
    }
`;

export const Button = styled.button`
    width: ${props => props.width ? props.width : 'auto'};
    background: ${props => props.background ? props.background : 'primary'};
    padding: 5px;
    color: white;
    font-size: 15px;
    font-weight: 700;
    border: none;
    border-radius: 100px 100px 100px 100px;
    cursor: pointer;
    transition: all, 240ms ease-in-out;
    background: #94e9e4;
    margin-right: 7px;
    margin-top: 5px;

    
    &:hover{
        filter: brightness(1.03);
        color:black;
        background-color: white;
    }
`;