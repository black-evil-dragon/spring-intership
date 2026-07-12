import type { Pageable } from "@shared/types";
import { API } from "@shared/api";

import type { DeskSummary, DeskType } from "../types";




const deskAPI = API.injectEndpoints({
    endpoints: (builder) => ({

        getDesks: builder.query<Pageable<DeskSummary[]>, void>({
            query: () => "/desks",
        }),

        getDeskDetail: builder.query<DeskType, string>({
            query: (id) => `desks/${id}`,
            providesTags: (result, error, id) => [{ type: 'Desk', id }],
        }),

    }),
});

export const {
    useGetDeskDetailQuery,
    useGetDesksQuery,
} = deskAPI