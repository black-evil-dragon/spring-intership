import { API } from "@shared/api";
import type { ColumnCreateRequest, ColumnDeleteRequest } from "../types";

const columnCRUD = API.injectEndpoints({

    endpoints: (build) => ({

        createColumn: build.mutation<any, ColumnCreateRequest>({
            query: ({ deskId, ...body }) => ({
                url: `/desks/${deskId}/columns`,
                method: 'POST',
                body,
            }),
            invalidatesTags: (result, error, { deskId }) => [
                { type: 'Desk', id: deskId }
            ],
        }),

        deleteColumn: build.mutation<any, ColumnDeleteRequest>({
            query: ({ deskId, columnId }) => ({
                url: `/desks/${deskId}/columns/${columnId}`,
                method: 'DELETE',
            }),
            invalidatesTags: (result, error, { deskId }) => [
                { type: 'Desk', id: deskId }
            ],
        }),
    }),

    overrideExisting: false,
})

export const {
    useCreateColumnMutation,
    useDeleteColumnMutation
} = columnCRUD