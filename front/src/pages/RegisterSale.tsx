import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import api from '../api/client';

export default function RegisterSale() {
    const [products, setProducts] = useState([]);
    const [productId, setProductId] = useState('');
    const [quantity, setQuantity] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        api.get('/products/all').then((res) => setProducts(res.data));
    }, []);

    async function handleSubmit(e) {
        e.preventDefault();
        setError('');
        try {
            await api.post('/sales/register', {
                product: { id: parseInt(productId, 10) },
                quantity: parseInt(quantity, 10),
            });
            navigate('/products');
        } catch (err) {
            setError(err.response?.data?.mensaje || 'No se pudo registrar la venta');
        }
    }

    return (
        <form onSubmit={handleSubmit}>
            <h2>Registrar venta</h2>
            <select value={productId} onChange={(e) => setProductId(e.target.value)}>
                <option value="">Selecciona un producto</option>
                {products.map((p) => (
                    <option key={p.id} value={p.id}>{p.name} (stock: {p.stock})</option>
                ))}
            </select>
            <input
                type="number"
                placeholder="Cantidad"
                value={quantity}
                onChange={(e) => setQuantity(e.target.value)}
            />
            {error && <p style={{ color: 'red' }}>{error}</p>}
            <button type="submit">Vender</button>
        </form>
    );
}