// import React, { useEffect } from "react";
// import { useDispatch, useSelector } from "react-redux";
// import { fetchFlower } from "../slices/flowersSlice";
// import { CircularProgress, Typography, Box, Button, Grid } from "@mui/material";
// import { useNavigate } from "react-router-dom";

// export default function Flowers() {
//   const dispatch = useDispatch();
//   const navigate = useNavigate(); // הוספת useNavigate לניווט
//   const { flowers, loading, error } = useSelector((state) => state.flowers);

//   useEffect(() => {
//     dispatch(fetchFlower());
//   }, [dispatch]);

//   if (loading) return <CircularProgress />;
//   if (error) return <Typography color="error">שגיאה: {error}</Typography>;

//   if (flowers.length === 0) {
//     return <Typography>אין פרחים להצגה</Typography>;
//   }

//   return (
//     <Box sx={{ padding: "16px" }}>
//       <Grid container spacing={2}>
//         {flowers.map((flower) => (
//           <Grid item xs={12} sm={6} md={4} key={flower.id}>
//             <Box
//               sx={{
//                 border: "1px solid #ddd",
//                 borderRadius: "8px",
//                 padding: "16px",
//                 textAlign: "center",
//               }}
//             >
//               <h3>{flower.name}</h3>
//               <p>{flower.description}</p>
//               <Button
//                 variant="contained"
//                 color="primary"
//                 onClick={() => navigate(`/flower/${flower.id}`)} // ניווט לדף החדש
//               >
//                 פרטים נוספים
//               </Button>
//             </Box>
//           </Grid>
//         ))}
//       </Grid>
//     </Box>
//   );
// }


import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { fetchFlower, setSelectedFlowerId } from '../slices/flowersSlice';
import { CircularProgress, Typography, Box, Button, Grid } from '@mui/material';
import { useNavigate } from 'react-router-dom';

export default function Flowers() {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const { flowers, loading, error } = useSelector((state) => state.flowers);

  useEffect(() => {
    dispatch(fetchFlower());
  }, [dispatch]);

  const handleViewDetails = (id) => {
    dispatch(setSelectedFlowerId(id));
    navigate(`/flowers/${id}/details`);
  };

  if (loading) return <CircularProgress />;
  if (error) return <Typography color="error">שגיאה: {error}</Typography>;
  if (flowers.length === 0) return <Typography>אין פרחים להצגה</Typography>;

  return (
    <Box sx={{ padding: '16px' }}>
      <Grid container spacing={2}>
        {flowers.map((flower) => (
          <Grid item xs={12} sm={6} md={4} key={flower.id}>
            <Box
              sx={{
                border: '1px solid #ddd',
                borderRadius: '8px',
                padding: '16px',
                textAlign: 'center',
              }}
            >
              <Typography variant="h5">{flower.name}</Typography>
              <Typography>{flower.description}</Typography>
              <Button
                variant="contained"
                color="primary"
                onClick={() => handleViewDetails(flower.id)}
              >
                פרטים נוספים
              </Button>
            </Box>
          </Grid>
        ))}
      </Grid>
    </Box>
  );
}