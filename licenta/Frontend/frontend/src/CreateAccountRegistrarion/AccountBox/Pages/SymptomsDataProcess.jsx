import React from "react";
import { useNavigate } from "react-router-dom";
import { Button } from "../utility";
import { SearchBarSymptoms } from "./SearchBarSymptoms";
import { HomePageContainerMain } from "./HomePageStyle";
import { Marginer } from "../marginer";
import { useState } from "react";
import axios from 'axios';

export function SymptomsDataProcess () {

    let navigate = useNavigate(); 
    const routeBack = () =>{ 
        let path = '/'; 
        navigate(path);
    }

    const [symptomData, setSymptomData] = useState(new Set());

    const addSymptomsToList = (symptom) => {
        setSymptomData((symptoms) => 
            new Set([...symptoms, symptom])
        );

        console.log("toate simptomele");
        //console.log(symptomData);
        //var set = new Set(symptomData);
        //setSymptomData(set);
        console.log(symptomData);
    }

    let axiosConfig = {
        headers: {
            'Content-Type': 'application/json',
            "Access-Control-Allow-Origin": "*",
        }
    };

    const IntroduceYourSymptoms = async () => {

        let requestBody = "{\"symptoms\": " + "\"" + (Array.from(symptomData).map((symptom) => symptom )).toString() + "\"}";

        let response = await axios.post('http://localhost:5920/test', JSON.parse(requestBody), axiosConfig)
            .catch((err) => {
                console.log(err);
            });

            if(response){
                console.log(response);
                if(response.data){
                    console.log(response.data); 
                    alert("Your disease seems to be " + response.data);
                }
            }
    }


    return(
        <div>
            <Button onClick={() => routeBack()} style={{ float:"left", color:"black", background:"#4e93f3af", width:"5%", height:"35px"}}>Back</Button>
            <HomePageContainerMain>
                <div style={{display:'row'}}>
                    <textarea value={Array.from(symptomData).map((symptom) => symptom )} spellCheck="false" style={{width:'800px', height:'50px', marginTop:'50px', resize:'none'}} readOnly/>
                    <Button onClick={IntroduceYourSymptoms}>See the disease</Button>
                </div>
                <Marginer direction="vertical" margin="1rem"/> 
                <SearchBarSymptoms addSymptoms={addSymptomsToList}/>
            </HomePageContainerMain>

        </div>
    );
}