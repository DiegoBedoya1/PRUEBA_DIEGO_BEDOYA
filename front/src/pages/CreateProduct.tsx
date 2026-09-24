import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import api from '../api/client';

export default function CreateProduct() {
    const [form, setForm] = useState({
        name: '',
        activeIngredient: '',
        storageType: 'ROOM_TEMPERATURE',
        expirationDate: '',
        price: '',
        stock: '',
        requiresPreescription: false,
    });
    const [error, setError] = useState('');
    const navigate = useNavigate();

    function handleChange(e) {
        const { name, value, type, checked } = e.target;
        setForm({ ...form, [name]: type === 'checkbox' ? checked : value });
    }

    async function handleSubmit(e) {
        e.preventDefault();
        setError('');
        try {
            await api.post('/products/new', {
                ...form,
                requierePreescripcion: form.requiresPreescription,
                price: parseFloat(form.price),
                stock: parseInt(form.stock, 10),
            });
            navigate('/products');
        } catch (err) {
            setError('No se pudo crear el producto');
        }
    }

    return (
        <form onSubmit={handleSubmit}>
            <h2>Crear producto</h2>
            <input name="name" placeholder="Nombre" value={form.name} onChange={handleChange} />
            <input name="activeIngredient" placeholder="Principio activo" value={form.activeIngredient} onChange={handleChange} />
            <select name="storageType" value={form.storageType} onChange={handleChange}>
                <option value="ROOM_TEMPERATURE">Temperatura ambiente</option>
                <option value="REFRIGERATED">Refrigerado</option>
            </select>
            <input type="date" name="expirationDate" value={form.expirationDate} onChange={handleChange} />
            <input type="number" step="0.01" name="price" placeholder="Precio" value={form.price} onChange={handleChange} />
            <input type="number" name="stock" placeholder="Stock" value={form.stock} onChange={handleChange} />
            <label>
                <input type="checkbox" name="requiresPreescription" checked={form.requiresPreescription} onChange={handleChange} />
                Requiere receta
            </label>
            {error && <p style={{ color: 'red' }}>{error}</p>}
            <button type="submit">Guardar</button>
        </form>
    );
}