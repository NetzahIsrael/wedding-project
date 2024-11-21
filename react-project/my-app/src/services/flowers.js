import axios from "axios";

export async function FlowerDesign() {
    const response = await axios.get("http://localhost:8080/api/flowers/getFlowers");
    console.log("Data from server:", response.data); // בדיקת נתונים
    return response.data;
  }