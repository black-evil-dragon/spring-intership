import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";
import type { DeskSummary, DeskType } from "../types";
import type { Pageable } from "@shared/types";


const resource = "desks"


export const deskAPI = createApi({
    reducerPath: 'deskApi',
    baseQuery: fetchBaseQuery({ baseUrl: 'http://localhost:8080/api/v1' }),
    tagTypes: ['DeskDetail', 'Columns'],
    endpoints: (builder) => ({

        getDesks: builder.query<Pageable<DeskSummary[]>, void>({
            query: () => resource,
        }),

        getDeskDetail: builder.query<DeskType, string>({
            query: (id) => `${resource}/${id}`,
            providesTags: (result, error, id) => [{ type: 'DeskDetail', id }],
        }),

    }),
});

export const {
    useGetDesksQuery,
    useGetDeskDetailQuery
} = deskAPI