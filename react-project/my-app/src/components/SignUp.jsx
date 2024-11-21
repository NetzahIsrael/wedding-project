// src/components/SignUp.jsx

import React, { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { registerUser } from '../slices/userSlice';
import { Box, TextField, Button, Typography } from '@mui/material';
import { useNavigate } from 'react-router-dom';

function SignUp() {
    //useDispatch לסטור actions שולח פעולות 
    //username, password, email במקרה שלנו 
    const dispatch = useDispatch();
    const navigate=useNavigate();
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [email, setEmail] = useState('');

    //useSelector שולף מידע מהסטור
    // error ,loading במקרה הזה שולך לתוך משתנים את 
    const loading = useSelector((state) => state.user.loading);
    const error = useSelector((state) => state.user.error);

//2 ההןקים הללו משמשים להעברת מידע בין הדפים ללא צורך בפרופס
// שנוצרה בסלייס היא תקבל את המידע שנמצא בסטייט לדוג שם משתמש וסיסמאותבצא את הרישום מול השרתr egisterUser מתבצעת קריאה
    const handleSignUp  = async() => {
try{
       const result =await dispatch(registerUser({ username, password, email }));
       if (result.payload){
        navigate("/homeAfterRegister")
       }
    }
    catch(error){
       console.error("signUp faild ",err) ;
    }
};


    return (
        <div>
            <h1>please sign up</h1>
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

            <input
            type="email"
            placeholder='Email'
            value={email}
            onChange={(e)=>setEmail(e.target.value)}
            />

             <button onClick={handleSignUp}>
                {loading ? "Loading..." : "SignUp"}
            </button>            
        </div>
    );
}

export default SignUp;


// import React, { useState } from 'react';
// import { useDispatch, useSelector } from 'react-redux';
// import { registerUser } from '../slices/userSlice';
// import { Box, TextField, Button, Typography } from '@mui/material';

// function SignUp() {
//     const dispatch = useDispatch();
//     const [username, setUsername] = useState('');
//     const [password, setPassword] = useState('');
//     const [email, setEmail] = useState('');
//     const loading = useSelector((state) => state.user.loading);
//     const error = useSelector((state) => state.user.error);

//     const handleSignUp = () => {
//         dispatch(registerUser({ username, password, email }));
//     };

//     return (
//         <Box
//             sx={{
//                 minHeight: '100vh',
//                 display: 'flex',
//                 flexDirection: 'column',
//                 justifyContent: 'center',
//                 alignItems: 'center',
//                 //background: 'linear-gradient(135deg, black, #ff4081)',
//                 //color: 'white',
//                 padding: '2rem',
//             }}
//         >
//             <Typography variant="h4" sx={{ marginBottom: 3 }}>
//                 הרשמה לאתר קולולו
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
//             <TextField
//                 variant="outlined"
//                 placeholder="אימייל"
//                 value={email}
//                 onChange={(e) => setEmail(e.target.value)}
//                 sx={{ backgroundColor: 'white', marginBottom: 2, width: '300px' }}
//             />
          
//         </Box>
//     );
// }

// export default SignUp;
