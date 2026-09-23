import {createContext, useContext, useEffect, useState} from "react";
import api from "../api/client";

const AuthContext = createContext(null);

export default function AuthProvider({children}){
    const [user, setUser] = useState(null);
    const[loading, setLoading] = useState(true);

    useEffect(() => {
        const token = localStorage.getItem("token");
        if(!token){
            setLoading(false);
            return;
        }
        api.get("/auth/me")
            .then(res => setUser(res.data))
            .catch(() => {
                localStorage.removeItem("token");
                setUser(null);
            })
            .finally(() => setLoading(false));
    },[]);
    const login = async (mail,password) => {
        const {data} = await api.post("/auth/login", {mail, password});
        localStorage.setItem("token", data.token);

        const me = await api.get("/auth/me");
        setUser(me.data);
        return me.data;
    };

    const logout = () => {
        localStorage.removeItem("token");
        setUser(null);
    }

    return (
        <AuthContext.Provider value = {{user,login,logout, loading, setLoading}}>
            {children}
        </AuthContext.Provider>
    );
}

export function useAuth(){
    return useContext(AuthContext);
}