import React from "react";
import styled from "styled-components";
import { useNavigate } from "react-router-dom";

const SearchQueryContainer = styled.div`
    width: 90%;
    height: 2em;
    display: flex;
    border-bottom: 1px solid #d8d8d852;
    padding: 6px 8px;
    padding-top: 2px;
    padding-bottom: 2px;
    background-color: #bbfaeb7d;
    align-items: center;
    border-radius: 15px;
    margin-bottom: 5px;
`;

export function SearchQueryComponentSymptom(props){

    const { symptom } = props;
    let navigate = useNavigate();
    let symptomsList = []

    const printSymptom = (name) => {
        //console.log(name);

        console.log("search bar com symp");
        console.log(props);
        props.addSymptoms(name);
    }

    return(
    
        <SearchQueryContainer onClick={() => printSymptom(symptom.numeSimptom)}>
            <div>
                {symptom.numeSimptom} &nbsp;
            </div>
        </SearchQueryContainer>

    )
}