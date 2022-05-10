import React, { useEffect, useRef, useState} from "react";
import styled from "styled-components";
import {IoClose, IoSearch} from "react-icons/io5"; 
import { motion } from "framer-motion";
import { useClickOutside } from "react-click-outside-hook";
import { AnimatePresence } from "framer-motion";
import MoonLoader from "react-spinners/MoonLoader";
import { SearchDebounceHook } from "../../hooks/searchDebounceHook";
import axios from "axios";
import { SearchQueryComponentPatient } from "./SearchQueryComponentPatient";

const SearchBarContainer = styled(motion.div)`
    display: flex;
    flex-direction: column;
    width: 50em;
    height: 3.8em;
    background-color: #ffff;
    border-radius: 6px;
    box-shadow: 0px 2px 12px 3px rgba(0, 0, 0, 0.14);
`;

const SearchInputContainer = styled.div`
    width: 97%;
    min-height: 4em;
    display: flex;
    align-items: center;
    position: relative;
    padding-left:15px;
`;

const SearchInput = styled.input`
    width: 100%;
    height: 100%;
    outline: none;
    border: none;
    font-size: 21px;
    color: #bebebe;
    border-radius: 6px;
    font-weight: 500;
    background-color: transparent;


    &:focus {
        outline: none;
        &::placeholder{
            opacity: 0;
        }
    }

    &::placeholder{
        color: #bebebe;
        transition: all 250ms ease-in-out;
    }
`;


const SearchContentList = styled.div`
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    padding: 1em;
    overflow-y: auto;
    align-items: center;
`;

const LineSeparator = styled.span`
    display: flex;
    min-width: 100%;
    min-height: 2px;
    background-color: #d8d8d878;
`;

const LoadingWrapper = styled.div`
    width: 100%;
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
`;

const SearchIcon = styled.span`
    color: #bebebe;
    font-size: 23px;
    margin-right: 7px;
    margin-top: 6px;
    vertical-align: middle;

`;

const CloseIcon = styled(motion.span)`
    color: #bebebe;
    font-size: 23px;
    vertical-align: middle;
    transition: all 200ms ease-in-out;
    cursor: pointer;
    
    &:hover{
        color: #dfdfdf;
    }
`;

const containerVariants = {

    expanded: {
        height: "20em",
    },

    collapsed: {
        height: "3.8em"
    }
}

const WarningMessage = styled.div`
    color: #a1a1a1;
    font-size: 14px;
    display: flex;
    align-self: center;
    justify-self: center;
`;

const searchBarContainerTransition = {type : 'spring', damping: 27, stiffness: 170}

export function SearchBarPatient(props){

    const [isExpanded, setExpanded] = useState(false);
    const [clickReference, isClickedOutside] = useClickOutside();
    const searchBarInputReference = useRef();
    const [seachQuery, setSearchQuery] = useState("");
    const [isLoading, setLoading] = useState(false);
    const [searchedPatients, setSearchedPatients] = useState([]);
    const [noPatientsFound, setNoPatientsFound] = useState(false);

    var isEmpty = !searchedPatients || searchedPatients.length === 0;

    const changeInputSearch = (e) => {
        e.preventDefault();
        setSearchQuery(e.target.value);
    }

    const expandContainer = () => {
        setExpanded(true);
    }

    const collapseContainer = () => {
        setExpanded(false);
        setSearchQuery("");
        setLoading(false);
        setSearchedPatients([]);
        setNoPatientsFound(false);
        if(searchBarInputReference.current){
            searchBarInputReference.current.value = "";
        }
    }

    useEffect(() => {
        if(isClickedOutside) 
            collapseContainer();
    }, [isClickedOutside]);

    const searchPatients = async () => {
        if(!seachQuery || seachQuery.trim() === "")
            return;
        
        setLoading(true);

        let axiosGetConfig = {
            headers: {
                'Content-Type': '*/*',
                "Access-Control-Allow-Origin": "*",
                "Authorization": `Bearer ${window.localStorage.getItem("accessToken")}`
            }
        };

        var res = await axios.get('http://localhost:8888/api/patients/name/' + seachQuery, axiosGetConfig)
            .catch((err) => {
                console.log("AXIOS ERROR: ");
                console.log(err);
            });

            if(res){

                if(res.data && res.data.length === 0){
                    setNoPatientsFound(true);
                }
                setSearchedPatients(res.data);
                setLoading(false);
            }
    }

    SearchDebounceHook(seachQuery, 500, searchPatients);

    return (
        <SearchBarContainer 
            animate={isExpanded ? "expanded" : "collapsed"} variants={containerVariants}
            transition={searchBarContainerTransition}
            ref = {clickReference}>

            <SearchInputContainer>
                <SearchIcon>
                    <IoSearch/>
                </SearchIcon>

                <SearchInput 
                    placeholder="search for patients" 
                    onFocus={expandContainer}
                    ref = {searchBarInputReference}
                    value = {seachQuery}
                    onChange = {changeInputSearch}/>

                <AnimatePresence>
                    {isExpanded && <CloseIcon 
                        key="close-icon"
                        initial={{ opacity:0 }}
                        animate={{ opacity:1 }}
                        exit={{ opacity:0 }}
                        onClick={collapseContainer}
                        transition={{duration:0.2}}>
                        <IoClose/>
                    </CloseIcon>}
                </AnimatePresence>

            </SearchInputContainer>

            {isExpanded && <LineSeparator/>}
            
            {isExpanded && (
                <SearchContentList>
                    {isLoading && (
                        <LoadingWrapper>
                                <MoonLoader loading color="#000" size={(20)}/>
                        </LoadingWrapper>)}
                    
                    {!isLoading && isEmpty && noPatientsFound &&
                        <WarningMessage>
                            No patients found in database
                        </WarningMessage>
                    }

                    {!isLoading && !isEmpty && (
                        <> 
                            {searchedPatients.map((patient) => <SearchQueryComponentPatient key={patient.id} patient={patient}/>)}
                        </>)}
                </SearchContentList>
            )}

        </SearchBarContainer>
    )
}