import { configureStore } from '@reduxjs/toolkit';//הפונקציה הזו היא חלק מהרידקס טולקיטס ומגדירה את 
//Middleware הסטור בצורה נוחה ופשוטה במקום להגדיר אותו עם באופן ידני ולהוסיף כלים כמו 
import userReducer from '../slices/userSlice';
import flowerReducer from '../slices/flowersSlice'; // ייבוא נכון של ה-reducer
//import flowerRecommendationsReducer from '../slices/recommandFlowerSlice'
import recommandFlowerReducer from '../slices/recommandFlowerSlice';


const store = configureStore({
  reducer: {
    //user-שם הסלייס
    //userReducer- השם שהגדרנו שיטפל במצבי המשתמש בסלייס
    user: userReducer,
    flowers: flowerReducer,
    //flowerRecommendations: flowerRecommendationsReducer,
    flowerRecommendations: recommandFlowerReducer,


  },
});

export  default store; // ייצוא של store