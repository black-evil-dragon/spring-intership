import type { DeskSummary, DeskType } from "@entities/desk";

import { API } from "@shared/api";
import type { Pageable } from "@shared/types";


const deskCRUD = API.injectEndpoints({

    endpoints: (build) => ({
        getDesksPage: build.query<Pageable<DeskSummary[]>, void>({
            query: () => "/desks",
            providesTags: ['Desk'],
        }),

        getDesk: build.query<DeskType, string>({
            query: (id) => `desks/${id}`,
            providesTags: (result, error, id) => [{ type: 'Desk', id }],
        }),
    }),

    overrideExisting: false,
})

export const {
    useGetDesksPageQuery,
    useGetDeskQuery
} = deskCRUD