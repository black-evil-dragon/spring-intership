import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";
import type { Desk } from "../types";
import type { Pageable } from "@shared/types";


export const deskAPI = createApi({
    reducerPath: 'deskApi',
    baseQuery: fetchBaseQuery({ baseUrl: 'http://localhost:8080/api/v1' }),
    endpoints: (builder) => ({

        getDesks: builder.query<Pageable<Desk[]>, void>({
            query: () => 'desks',
        }),

    }),
});

export const {
    useGetDesksQuery
} = deskAPI