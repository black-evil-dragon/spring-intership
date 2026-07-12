import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";


export const columnAPI = createApi({
    reducerPath: 'columnApi',
    baseQuery: fetchBaseQuery({ baseUrl: 'http://localhost:8080/api/v1' }),
    tagTypes: ['Columns'],
    endpoints: (builder) => ({

    }),
});

export const {
} = columnAPI