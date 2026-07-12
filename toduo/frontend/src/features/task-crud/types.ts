import type { TaskStatus } from "@entities/task"

export interface TaskCreateRequest {
    deskId: number
    columnId?: number

    name: string,
    status: TaskStatus

    description?: string,
    deadline?: string

    authorId: number,
    assigneeId?: number
}

export interface TaskDeleteRequest {
    taskId: string
}


export interface TaskCreateForm {
    taskName: string;
    status: TaskStatus
    description?: string;
    deadline?: string
}