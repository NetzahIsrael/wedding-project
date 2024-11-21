import React, { useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { fetchFlower } from '../slices/flowersSlice';
import { fetchRecommendations } from '../slices/recommandFlowerSlice';
import { CircularProgress, Typography, Box } from '@mui/material';

export default function FlowerDetails() {
  const dispatch = useDispatch();
  const { id } = useParams();

  useEffect(() => {
    dispatch(fetchFlower());
    dispatch(fetchRecommendations(id));
  }, [dispatch, id]);

  const { flowers, loading: flowersLoading, error: flowersError } = useSelector((state) => state.flowers);
  const { recommendations, loading: recLoading, error: recError } = useSelector((state) => state.flowerRecommendations);

  const selectedFlower = flowers.find((flower) => flower.id === parseInt(id));
  const flowerRecommendations = recommendations?.[id] || [];

  if (flowersLoading || recLoading) return <CircularProgress />;
  if (flowersError || recError) return <Typography color="error">שגיאה: {flowersError || recError}</Typography>;
  if (!selectedFlower) return <Typography>לא נמצא פרח עם מזהה זה</Typography>;

  console.log("Recommendations for flower ID:", id, flowerRecommendations);

  return (
    <Box>
      <Typography variant="h4">{selectedFlower.name}</Typography>
      <Typography>{selectedFlower.description}</Typography>
      <Typography variant="h5">תגובות:</Typography>
      {flowerRecommendations.length > 0 ? (
        flowerRecommendations.map((recommendation) => (
          <Box key={recommendation.id}>
            <Typography>שם הממליץ: {recommendation.reviewerName}</Typography>
            <Typography>דירוג: {recommendation.rating}</Typography>
            <Typography>תגובה: {recommendation.comment}</Typography>
            <Typography>תאריך: {recommendation.date}</Typography>
          </Box>
        ))
      ) : (
        <Typography>אין תגובות לפרח זה.</Typography>
      )}
    </Box>
  );
}