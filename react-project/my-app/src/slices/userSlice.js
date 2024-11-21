import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import { signUpUser,SigninUser } from "../services/user"; 

//מגדיר את המצב ההתחלתי של המתמש כרגע
//currentUser - מאחסן בתוכו את נתוני המתמש העכשווי
//loading- אם ישנה פעולה שנטענת-מתבצעת כרגע 
//והודעת שגיאה במידה שיש  
const initialState = {
    currentUser: {},
    loading: false,
    error: ""
};

// userData הפונקציה הזו קוראת לשרת ומחירה את התוצאה שהתקבלה ממנו לתוך 
//  שהיא מבצעת את הקריאות לשרת signUpUser בתוכה היא קוראת ל
//registerUser-  createAsyncThunk המזהה של הפעולה 
export const registerUser = createAsyncThunk("user/registerUser", async (user) => {
    const userData = await signUpUser(user);  
    return userData;
});

export const LoginUser=createAsyncThunk("user/LoginUser",async(user)=>{
    const userData=await SigninUser(user);
    return userData;
});

const userSlice = createSlice({
    name: "user",//השם של הסלייס משתמש לזיוי הסלייס והפעולות שלובתוך הסטור
    initialState,
    reducers: {},//ריק , כי מגדיר פעולות סינכרוניות ואנו השתמשנו באסינכרוניות

    //builder זה החלק שבו מנהלים את האסינכרוניות בעזרת
    extraReducers: (builder) => {
        builder
        //pending-  מתחילהA PI מופעל כשקריאה ל
        // לסמל שמידע נטען true לכן מאותחל ל
            .addCase(registerUser.pending, (state) => {
                state.loading = true;
            })

            //fulfilled- הצליחה API מופעלת כשהקריאה ל-
            // action.payload באמצעות currentUser המידע נשמר ב
            // false מוחזר ל loading ה
            .addCase(registerUser.fulfilled, (state, action) => {
                state.currentUser = action.payload;
                state.loading = false;
            })
            //rejected -נכשלת API מופעלת כשהקריאה ל
            //הודעת השגיאה נשמרת ב-error (באמצעות action.error.message).
            .addCase(registerUser.rejected, (state, action) => {
                state.error = action.error.message;
                state.loading = false;
            })


            .addCase(LoginUser.pending,(state)=>{
                state.loading=true;
            })
            .addCase(LoginUser.fulfilled, (state, action) => {
                state.currentUser = action.payload;
                state.loading = false;
            })
            
            
            // .addCase(LoginUser.rejected,(state,action)=>{
            //     if(action.error == 404){
            //         state.error=action.error;
            //     }
            //     state.error=action.error.message;
            //     state.loading=false;

            // })
            .addCase(LoginUser.rejected, (state, action) => {
                if (action.error.message === "The username or password is incorrect") {
                    state.error = "שם המשתמש או הסיסמה שגויים";
                } else if (action.error.message === "404") {
                    state.error = "404"; // מציין שהמשתמש לא נמצא
                } else {
                    state.error = action.error.message || "Unknown error";
                }
                state.loading = false;
            });
            
           
    }
});

export default userSlice.reducer;
