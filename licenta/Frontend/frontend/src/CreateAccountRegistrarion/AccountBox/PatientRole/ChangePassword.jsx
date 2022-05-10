import axios from "axios";
import { React, useState} from "react";
import { useParams, useNavigate} from "react-router-dom";
import { Marginer } from "../marginer";
import { Button, FormContainer, Input, SubmitButton } from "../utility";

export function ChangePassword(props) {

    const [oldPassword, setOldPassword] = useState('');
    const [newPassword, setNewPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');

    let username = useParams();
    let navigate = useNavigate();
    
    const updateOldPassword = (props) => {
        setOldPassword(props.target.value);
        console.log("old pass" + props.target.value);
    }

    const updateNewPassword = (props) => {
        setNewPassword(props.target.value);
        console.log("new pass:" + props.target.value);
    }
    const updateConfirmPassword = (props) => {
        setConfirmPassword(props.target.value);
        console.log("conf pass:" + props.target.value);
    }

    const routeBack = (username) => {
        let path = `/accountSettings/${username}`;
        navigate(path);
    }

    const changePassword = async () => {

        if(newPassword != confirmPassword){
            alert("New password doesn't match");
            return;
        }

        let axiosGetConfig = {
            headers: {
                'Content-Type': '*/*',
                "Access-Control-Allow-Origin": "*",
                "Authorization": `Bearer ${window.localStorage.getItem("accessToken")}`
            }
        };

        let res = await axios.get('http://localhost:8889/api/userController/checkPassword/' + username["username"] + "/" + oldPassword, axiosGetConfig)
                .catch((err) => {
                    console.log(err.data);
                })
            
            if(res){
                if(res.data){
                    if(res.data == 'da'){
                        
                        let axiosPatchConfig = {
                            headers: {
                                'Content-Type': 'application/json',
                                "Access-Control-Allow-Origin": "*",
                                "Authorization": `Bearer ${window.localStorage.getItem("accessToken")}`
                            }
                        };
                        var updateInfo = "{\"password\": \"" + newPassword + "\"}";
                        console.log(updateInfo);
                        try {
                            var response = await axios.patch('http://localhost:8889/api/userController/updateUserAccount/' + username["username"], updateInfo, axiosPatchConfig);
                            console.log("res.data");
                            console.log(response.data); 
                            alert("Successfully updated password!"); 

                        }
                        catch(err){
                            console.log("err");
                            console.log(err);
                            alert("No update done! There were some errors");
                        }
                }
                else{

                    alert("Old password is wrong!");
                }
            }
        
        }
    }
    return(
        <div>
            <Button onClick={() => routeBack(username["username"])} style={{ float:"left", color:"black", background:"#4e93f3af", width:"5%"}}>Back</Button>
            <Marginer direction="vertical" margin="250px"/>
            <FormContainer>
                <Input width='200px' type="password" placeholder="Old password" onChange={updateOldPassword}/>
                <Input width='200px' type="password" placeholder="New password" onChange={updateNewPassword}/>
                <Input width='200px' type="password" placeholder="Confirm password" onChange={updateConfirmPassword}/>
            </FormContainer>
            <SubmitButton style={{ marginLeft:'885px'}} onClick={() => changePassword()}>Update password</SubmitButton>
        </div>
    );  
}