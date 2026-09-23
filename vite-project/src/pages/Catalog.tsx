import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import api from '../api/client';

export default function Catalog() {
    const [products, setProducts] = useState([]);
    const [error, setError] = useState('');
    const { user, logout } = useAuth();
    const navigate = useNavigate();

    useEffect(() => {
        api.get('/products/all')
            .then((res) => setProducts(res.data))
            .catch(() => setError('No se pudo cargar el catálogo'));
    }, []);

    function handleLogout() {
        logout();
        navigate('/login');
    }

    return (
        <div>
            <h2>Catálogo de productos</h2>
            <p>Hola, {user.name} ({user.role})</p>
            <button onClick={handleLogout}>Cerrar sesión</button>

            {error && <p style={{ color: 'red' }}>{error}</p>}

            <table border="1" cellPadding="8">
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Principio activo</th>
                        <th>Precio</th>
                        <th>Stock</th>
                        <th>Vence</th>
                    </tr>
                </thead>
                <tbody>
                    {products.map((p) => (
                        <tr key={p.id}>
                            <td>{p.name}</td>
                            <td>{p.activeIngredient}</td>
                            <td>${p.price}</td>
                            <td>{p.stock}</td>
                            <td>{p.expirationDate}</td>
                        </tr>
                    ))}
                </tbody>
            </table>

            {user.role === 'ADMIN' && (
                <button onClick={() => navigate('/products/new')}>Crear producto</button>
            )}
            {user.role === 'USER' && (
                <button onClick={() => navigate('/sales/register')}>Registrar venta</button>
            )}
        </div>
    );
}