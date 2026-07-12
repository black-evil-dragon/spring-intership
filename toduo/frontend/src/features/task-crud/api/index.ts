
import type { TaskCreateRequest, TaskDeleteRequest } from "../types";
import { API } from "@shared/api";




const taskCRUD = API.injectEndpoints({

    endpoints: (build) => ({

        createTask: build.mutation<any, TaskCreateRequest>({
            query: (body) => ({
                url: `/tasks`,
                method: 'POST',
                body,
            }),
        }),

        deleteTask: build.mutation<any, TaskDeleteRequest>({
            query: ({ taskId }) => ({
                url: `/tasks/${taskId}`,
                method: 'DELETE',
            }),
            invalidatesTags: (result, error, { taskId }) => [
                { type: 'Task', id: taskId }
            ],
        }),
    }),

    overrideExisting: false,
})

export const {
    useCreateTaskMutation,
    useDeleteTaskMutation,
} = taskCRUD