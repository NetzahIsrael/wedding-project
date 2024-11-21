import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import { FlowerDesign } from '../services/flowers';

const initialState = {
  flowers: [],
  loading: false,
  error: '',
};

export const fetchFlower = createAsyncThunk('flowers/fetchFlower', async () => {
  try {
    const flowerData = await FlowerDesign();
    return flowerData;
  } catch (error) {
    throw new Error(error.message);
  }
});

const flowersSlice = createSlice({
  name: 'flowers',
  initialState,
  reducers: {
    setSelectedFlowerId(state, action) {
      state.selectedFlowerId = action.payload;
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(fetchFlower.pending, (state) => {
        state.loading = true;
        state.error = '';
      })
      .addCase(fetchFlower.fulfilled, (state, action) => {
        state.flowers = action.payload;
        state.loading = false;
      })
      .addCase(fetchFlower.rejected, (state, action) => {
        state.error = action.error.message;
        state.loading = false;
      });
  },
});

export const { setSelectedFlowerId } = flowersSlice.actions;
export default flowersSlice.reducer;