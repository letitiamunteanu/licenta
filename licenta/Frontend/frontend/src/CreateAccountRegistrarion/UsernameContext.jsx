import { createContext } from "react";

export const UsernameContext = createContext({
    usr: "",
    setUsr: (usr) => {}
});