import axios from "axios";


  export async function recommandFlower(flowerId) {
    const response = await axios.get(
      `http://localhost:8080/api/flowers/recommendations/getRecommendations/${flowerId}`
    );
    console.log("Recommendations from server:", response.data); // הדפסת הנתונים מהשרת
    return response.data;
  }