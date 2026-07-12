import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";

export const API = createApi({
    reducerPath: 'api',
    // TODO: заменить на axios
    baseQuery: fetchBaseQuery({ baseUrl: 'http://localhost:8080/api/v1' }),

    tagTypes: [
        'Desk',
        'Column',
        'Task'
    ],


    endpoints: () => ({})
});