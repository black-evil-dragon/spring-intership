
import { configureStore } from '@reduxjs/toolkit';

import { deskAPI } from '@entities/desk';
import { columnAPI } from '@entities/column/api';


export const store = configureStore({
    reducer: {
        [deskAPI.reducerPath]: deskAPI.reducer,
        [columnAPI.reducerPath]: columnAPI.reducer,
    },
    middleware: (getDefaultMiddleware) =>
        getDefaultMiddleware().concat(
            deskAPI.middleware,
            columnAPI.middleware,
        ),
});


export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;