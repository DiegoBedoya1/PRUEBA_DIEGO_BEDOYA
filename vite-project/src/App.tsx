import { Navigate, Route, Routes } from "react-router-dom";
import { useAuth } from "./context/AuthContext"
import ProtectedRoute from "./components/ProtectedRoute";
import Unauthorized from "./pages/Unathorized";
import Catalog from "./pages/Catalog";
import Login from "./pages/Login";
import CreateProduct from "./pages/CreateProduct";
import RegisterSale from "./pages/RegisterSale";


function App() {
    const { loading } = useAuth();
    if(loading) return <p> Cargando...</p>

    return (
       <Routes>
            <Route path="/" element={<Navigate to="/login" replace />} />
            <Route path="/login" element={<Login />} />
            <Route path="/unauthorized" element={<Unauthorized />} />

            <Route
                path="/products"
                element={
                    <ProtectedRoute allowedRoles={['ADMIN', 'USER']}>
                        <Catalog />
                    </ProtectedRoute>
                }
            />
            <Route
                path="/products/new"
                element={
                    <ProtectedRoute allowedRoles={['ADMIN']}>
                        <CreateProduct />
                    </ProtectedRoute>
                }
            />
            <Route
                path="/sales/register"
                element={
                    <ProtectedRoute allowedRoles={['USER']}>
                        <RegisterSale />
                    </ProtectedRoute>
                }
            />
        </Routes>
    )
 
}

export default App
