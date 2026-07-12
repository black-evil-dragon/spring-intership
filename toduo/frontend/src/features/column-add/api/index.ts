import type { ColumnCreateRequest } from "../types";
import { deskAPI } from "@entities/desk";



const columnCreateAPI = deskAPI.injectEndpoints({

    endpoints: (build) => ({

        createColumn: build.mutation<any, ColumnCreateRequest>({
            query: ({ deskId, ...body }) => ({
                url: `/desks/${deskId}/columns`,
                method: 'POST',
                body,
            }),
            invalidatesTags: (result, error, { deskId }) => [
                { type: 'DeskDetail', id: deskId }
            ],
        }),
    }),

    overrideExisting: false,
})

export const {
    useCreateColumnMutation
} = columnCreateAPI