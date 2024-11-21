import React, { useState,useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { LoginUser } from '../slices/userSlice';
import { useNavigate } from 'react-router-dom';

function Login() {
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const loading = useSelector((state) => state.user.loading);
    const error = useSelector((state) => state.user.error);

    const handleLogin = async () => {
        try {
            const result = await dispatch(LoginUser({ username, password }));
            if (result.payload) {
                navigate("/homeAfterRegister"); // נווט לדף הבית
            }
        } catch (err) {
            console.error("Login failed:", err);
        }
    };

    useEffect(() => {
        if (error === "404") {
            navigate("/sign-up", { replace: true }); // נווט לדף ההרשמה (החלף את הנתיב הנוכחי)
        }
    }, [error, navigate]);


    return (
        <>
        <h1>please log in</h1>
            <input
                type="text"
                placeholder="Username"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
            />

            <input
                type="password"
                placeholder="Password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
            />

            <button onClick={handleLogin}>
                {loading ? "Loading..." : "Login"}
            </button>

            {error && <p>{error}</p>}
        </>
    );
}

export default Login;


// import React, { useState } from 'react';
// import { useDispatch, useSelector } from 'react-redux';
// import { LoginUser } from '../slices/userSlice';
// import { Box, TextField, Button, Typography } from '@mui/material';
// import { useNavigate } from 'react-router-dom'; // הוספת ה-hook של useNavigate

// function Login() {
//     const dispatch = useDispatch();
//     const navigate = useNavigate(); // יצירת משתנה navigate
//     const [username, setUsername] = useState('');
//     const [password, setPassword] = useState('');
//     const loading = useSelector((state) => state.user.loading);
//     const error = useSelector((state) => state.user.error);

//     const handleLogin = async () => {
//         try {
//             await dispatch(LoginUser({ username, password }));
//         } catch (err) {
//             // אם קיבלנו שגיאה של 404, נווט לדף ההרשמה
//             if (err.message === 'you are not connected') {
//                 navigate('/Sign-Up');
//             }
//         }
//     };

//     return (
//         <Box
//             sx={{
//                 minHeight: '100vh',
//                 display: 'flex',
//                 flexDirection: 'column',
//                 justifyContent: 'center',
//                 alignItems: 'center',
//                 padding: '2rem',
//             }}
//         >
//             <Typography variant="h4" sx={{ marginBottom: 3 }}>
//                 התחברות לאתר קולולו
//             </Typography>
//             <TextField
//                 variant="outlined"
//                 placeholder="שם משתמש"
//                 value={username}
//                 onChange={(e) => setUsername(e.target.value)}
//                 sx={{ backgroundColor: 'white', marginBottom: 2, width: '300px' }}
//             />
//             <TextField
//                 variant="outlined"
//                 type="password"
//                 placeholder="סיסמה"
//                 value={password}
//                 onChange={(e) => setPassword(e.target.value)}
//                 sx={{ backgroundColor: 'white', marginBottom: 2, width: '300px' }}
//             />
//             <Button
//                 variant="contained"
//                 sx={{
//                     backgroundColor: '#ff4081',
//                     '&:hover': { backgroundColor: '#e91e63' },
//                 }}
//                 onClick={handleLogin}
//                 disabled={loading}
//             >
//                 {loading ? 'טוען...' : 'התחבר'}
//             </Button>
//         </Box>
//     );
// }

// export default Login;

