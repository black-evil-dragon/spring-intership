import type { TaskStatus } from "@entities/task"

export interface TaskCreateRequest {
    deskId: string
    columnId?: string

    name: string,
    status: TaskStatus

    description?: string,
    deadline?: string

    authorId: string,
    assigneeId?: string
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