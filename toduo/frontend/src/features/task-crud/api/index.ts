
import type { Task } from "@entities/task";
import type { TaskCreateRequest, TaskDeleteRequest, TaskUpdateRequest } from "../types";
import { API } from "@shared/api";




const taskCRUD = API.injectEndpoints({

    endpoints: (build) => ({

        getTask: build.query<Task, string>({
            query: (taskId) => ({
                url: `/tasks/${taskId}`,
                method: 'GET',
            }),
            providesTags: (result, error, id) => [{ type: 'Task', id }],
        }),


        createTask: build.mutation<Task, TaskCreateRequest>({
            query: (body) => ({
                url: `/tasks`,
                method: 'POST',
                body,
            }),
            invalidatesTags: (result, error) =>
                result
                    ? [{ type: 'Task', id: result.id }]
                    : [],
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

        updateTask: build.mutation<Task, TaskUpdateRequest>({
            query: ({ taskId, ...body }) => ({
                url: `/tasks/${taskId}`,
                method: 'PATCH',
                body,
            }),
            invalidatesTags: (result, error, { taskId }) => [
                { type: 'Task', id: taskId, },
                { type: 'Desk', id: result?.deskId, }
            ],
        }),
    }),

    overrideExisting: false,
})

export const {
    useGetTaskQuery,
    useCreateTaskMutation,
    useDeleteTaskMutation,
    useUpdateTaskMutation,
} = taskCRUD