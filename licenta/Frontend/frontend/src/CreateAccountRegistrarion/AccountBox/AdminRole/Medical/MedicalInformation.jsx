import React, { useState } from "react";
import { BoxContainer, Button, Input } from "../../utility";
import { Marginer } from "../../marginer";
import styled from "styled-components";
import axios from "axios";
import { useNavigate } from "react-router-dom";

export const Container = styled.div`
    display: flex;
    flex-direction: row;
    justify-content: space-evenly;
    width: 100%;
    margin-top: 300px;
    border: 5rem;
    
`;


export function MedicalInformation(props) {


    const [symptom, setSymptom] =  useState('');
    const [disease, setDisease] = useState('');
    const [specialization, setSpecialization] = useState('');

    const updateSymptom = (props) =>{
        setSymptom(props.target.value);
        console.log("symptopm:" + props.target.value);
    }

    const updateDisease = (props) =>{
        setDisease(props.target.value);
        console.log("disease:" + props.target.value);
    }

    const updateSpecialization = (props) =>{
        setSpecialization(props.target.value);
        console.log("specialization:" + props.target.value);
    }

    let axiosConfig = {
        headers: {
            'Content-Type': 'application/json',
            "Access-Control-Allow-Origin": "*",
            'Authorization': `Bearer ${window.localStorage.getItem("accessToken")}`,
            "mode":"no-cors"
        }
    };

    const addDisease = async () => {
        let diseaseData = {
            numeBoala:disease
        }
 
        let res = await axios.post('http://localhost:8888/api/disease/addNewDisease', JSON.stringify(diseaseData), axiosConfig)
            // .then((response) => {
            //     console.log(response.data);
            // })
            .catch((err) => {
                alert(err.response.data);
            });

            if(res){
                if(res.data){
                    console.log(res.data);
                    alert("Successfully added this disease!");
                }
            }


    }

    const addSymptom = async () => {
        let symptomData = {
            numeSimptom:symptom
        }
 
        let res = await axios.post('http://localhost:8888/api/symptoms/addSymptom', JSON.stringify(symptomData), axiosConfig)
            .catch((err) => {
                alert(err.response.data);
            })

            if(res){
                if(res.data){
                    console.log(res.data);
                    alert("Successfully added this symptom!");
                }
            }

    }

    const addSpecialization = async () => {
        let specializationData = {
            nume:specialization
        }
 
        let res = await axios.post('http://localhost:8888/api/specialization', JSON.stringify(specializationData), axiosConfig)
            .catch((err) => {
                alert(err.response.data);
            });

            if(res){
                if(res.data){
                    console.log(res.data);
                    alert("Successfully added this specialization!");
                }
            }

    }

    let navigate = useNavigate();
    const routeBack = () => {
        let path = `/adminPage`
        navigate(path);
    }
    return (
        <div>
            <Button onClick={() => routeBack()} style={{ float:"left", color:"black", background:"#4e93f3af", width:"5%"}}>Back</Button>
            <Marginer direction="vertical" margin="0rem"/>
            <Container>
                <BoxContainer style={{ backgroundColor:"#83eeee62", borderRadius:"15px", paddingTop:"70px", paddingBottom:"70px", borderColor:"black", borderWidth:"10px", border:"solid 2px", marginRight:"5px", marginLeft:"5px"}}>
                    <Input width="200px" type='text' placeholder="New disease" onChange={updateDisease}/>
                    <Marginer direction="vertical" margin="0.5rem"/>
                    <Button type="submit" width = "20%" onClick={addDisease}>Add this disease</Button>
                    <Marginer direction="vertical" margin="0.5rem"/>
                </BoxContainer>

                <BoxContainer style={{ backgroundColor:"#83eeee91", borderRadius:"15px", paddingTop:"70px", paddingBottom:"70px", borderColor:"black", borderWidth:"10px", border:"solid 2px", marginRight:"5px"}}>
                    <Input width="200px" type='text' placeholder="New symptom" onChange={updateSymptom}/>
                    <Marginer direction="vertical" margin="0.5rem"/>
                    <Button type="submit" width = "20%" onClick={addSymptom}>Add this symptom</Button>
                    <Marginer direction="vertical" margin="0.5rem"/>
                    
                </BoxContainer>

                <BoxContainer style={{ backgroundColor:"#83eeee91", borderRadius:"15px", paddingTop:"70px", paddingBottom:"70px", borderColor:"black", borderWidth:"10px", border:"solid 2px", marginRight:"5px"}}>
                    <Input width="200px" type='text' placeholder="New specialization" onChange={updateSpecialization}/>
                    <Marginer direction="vertical" margin="0.5rem"/>
                    <Button type="submit" width = "25%" onClick={addSpecialization}>Add this specialization</Button>
                    <Marginer direction="vertical" margin="0.5rem"/>
                </BoxContainer>

            </Container>
        </div>
    );

}