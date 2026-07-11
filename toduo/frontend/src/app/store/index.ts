
import { configureStore } from '@reduxjs/toolkit';

import { deskAPI } from '@entities/desk';


export const store = configureStore({
    reducer: {
        [deskAPI.reducerPath]: deskAPI.reducer,
    },
    middleware: (getDefaultMiddleware) =>
        getDefaultMiddleware().concat(
            deskAPI.middleware,
        ),
});


export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;