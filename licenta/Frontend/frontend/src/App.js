import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import React, { useState } from 'react';
import {HomePage} from './CreateAccountRegistrarion/AccountBox/Pages/HomePage';
import {LoginPage} from './CreateAccountRegistrarion/AccountBox/Pages/LoginPage';
import { ForgotPassword } from './CreateAccountRegistrarion/AccountBox/Forms/forgotPasswordForm';
import { AdminPage } from './CreateAccountRegistrarion/AccountBox/Pages/AdminPage';
import { DoctorsComponent } from './CreateAccountRegistrarion/AccountBox/AdminRole/Doctor/DoctorsComponent';
import { PatientsComponent } from './CreateAccountRegistrarion/AccountBox/AdminRole/Pacient/PatientsComponent';
import { UpdateDoctorsForm } from './CreateAccountRegistrarion/AccountBox/AdminRole/Doctor/updateDoctorsForm';
import { UpdatePatientsForm } from './CreateAccountRegistrarion/AccountBox/AdminRole/Pacient/updatePatientsForm';
import { HomePagePatient } from './CreateAccountRegistrarion/AccountBox/PatientRole/HomePagePatient';
import { PatientProfileForm } from './CreateAccountRegistrarion/AccountBox/PatientRole/PatientProfileForm';
import { MedicalInformation } from './CreateAccountRegistrarion/AccountBox/AdminRole/Medical/MedicalInformation';
import { SeePatientProfile } from './CreateAccountRegistrarion/AccountBox/PatientRole/SeePatientProfile';
import { AccountPatientSettings } from './CreateAccountRegistrarion/AccountBox/PatientRole/AccountPatientSettings';
import { SeeAccountDetails } from './CreateAccountRegistrarion/AccountBox/PatientRole/SeeAccountDetails';
import { ChangePassword } from './CreateAccountRegistrarion/AccountBox/PatientRole/ChangePassword';
import { HomePageDoctor } from './CreateAccountRegistrarion/AccountBox/DoctorRole/HomePageDoctor';
import { DoctorProfileForm } from './CreateAccountRegistrarion/AccountBox/DoctorRole/DoctorProfileForm';
import { SeeDoctorProfile } from './CreateAccountRegistrarion/AccountBox/DoctorRole/SeeDoctorProfile';
import { UsernameContext } from './CreateAccountRegistrarion/UsernameContext';
import { SymptomsDataProcess } from './CreateAccountRegistrarion/AccountBox/Pages/SymptomsDataProcess';




export default function App () {

        // render() {
          const [usr, setUsr] = useState('');
          return(
              <Router>
                  <UsernameContext.Provider value={{usr, setUsr}}>
                    <Routes>
                        <Route exact path = '/' element = {<HomePage/>}/>
                        <Route exact path = '/login' element = {<LoginPage/>}/>
                        <Route exact path = '/forgotPassword' element = {<ForgotPassword/>}/>
                        <Route exact path = '/adminPage' element = {<AdminPage/>}/>
                        <Route exact path = '/doctorsInfo' element = {<DoctorsComponent/>}/>
                        <Route exact path = '/patientsInfo' element = {<PatientsComponent/>}/>
                        <Route exact path = '/updateDoctors/:id' element = {<UpdateDoctorsForm/>}/>
                        <Route exact path = '/updatePatients/:id' element = {<UpdatePatientsForm/>}/>
                        <Route exact path = '/homePatient/:username' element = {<HomePagePatient/>}/>
                        <Route exact path = '/completePatientProfile/:username' element = {<PatientProfileForm/>}/>
                        <Route exact path = '/seePatientProfile/:username' element = {<SeePatientProfile/>}/>
                        <Route exact path = '/medicalInfo' element = {<MedicalInformation/>}/>
                        <Route exact path = '/accountSettings/:username' element = {<AccountPatientSettings/>}/>
                        <Route exact path = '/seeAccountDetails/:username' element = {<SeeAccountDetails/>}/>
                        <Route exact path = '/changePassword/:username' element = {<ChangePassword/>}/>
                        <Route exact path = '/homeDoctor/:username' element = {<HomePageDoctor/>}/>
                        <Route exact path = '/completeDoctorProfile/:username' element = {<DoctorProfileForm/>}/>
                        <Route exact path = '/seeDoctorProfile/:username' element = {<SeeDoctorProfile/>}/>
                        <Route exact path = '/symptomsData' element = {<SymptomsDataProcess/>}/>
                    </Routes>
                  </UsernameContext.Provider>    
              </Router>
          );
      //}
}

// export default App;