import React, { useEffect, useState } from "react";


export function SearchDebounceHook(inputSearchBarValue, timeout, callbackFunction) {

    const [timer, setTimer] = useState(null);
    
    const clearTimer = () => {
        if(timer)
            clearTimeout(timer);
    }

    useEffect(() => {
        clearTimer();

        if(inputSearchBarValue && callbackFunction){

            const newTimeout = setTimeout(callbackFunction, timeout);
            setTimer(newTimeout);
        }
    }, [inputSearchBarValue]);


}