import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";

export default function Login(){
    const [mail, setMail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");
    const { login } = useAuth();
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        console.log("ENTRÓ AL SUBMIT"); // <-- agrega esto
        setError("");
        try{
            await login(mail,password);
            navigate("/products");
        }catch(err){
            console.log("ERROR REAL:", err); // <-- y esto
            setError("Correo o contraseña incorrectos");
        }
    }

    return (
        <form onSubmit = {handleSubmit}>
            <h2> Iniciar sesión</h2>
            <input
                type = "email"
                placeholder = "Correo"
                value = {mail}
                onChange = {(e) => setMail(e.target.value)}
            />
            <input
                type = "password"
                placeholder = "Contraseña"
                value = {password}
                onChange = {e => setPassword(e.target.value)}
            />
            {error && <p style= {{color: "red"}}> {error}</p>}
            <button type = "submit"> Entrar</button>
        </form>
    );
}