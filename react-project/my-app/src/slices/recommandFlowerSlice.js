import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import { recommandFlower } from '../services/recommandFlowers';

const initialState = {
  recommendations: {},
  loading: false,
  error: '',
};

export const fetchRecommendations = createAsyncThunk(
  'flowerRecommendations/fetchRecommendations',
  async (flowerId) => {
    try {
      const recommendationData = await recommandFlower(flowerId);
      return { flowerId, recommendations: recommendationData };
    } catch (error) {
      throw new Error(error.message);
    }
  }
);

const flowerRecommendationsSlice = createSlice({
  name: 'flowerRecommendations',
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchRecommendations.pending, (state) => {
        state.loading = true;
      })
      builder
      .addCase(fetchRecommendations.fulfilled, (state, action) => {
        const { flowerId, recommendations } = action.payload;
        console.log("Saving recommendations in Redux:", { flowerId, recommendations }); // הדפסה לבדיקה
        state.recommendations[flowerId] = recommendations;
        state.loading = false;
      })
    
      .addCase(fetchRecommendations.rejected, (state, action) => {
        state.error = action.error.message;
        state.loading = false;
      });
  },
});

export default flowerRecommendationsSlice.reducer;