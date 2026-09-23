import { useNavigate } from 'react-router-dom';

export default function Unauthorized() {
    const navigate = useNavigate();

    return (
        <div>
            <h2>No tienes permiso para ver esta página</h2>
            <button onClick={() => navigate('/products')}>Volver al catálogo</button>
        </div>
    );
}