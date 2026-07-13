import type { TaskStatus } from "@entities/task"


// * =============== REQUESTS ===============
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

export interface TaskUpdateRequest {
    taskId?: string
    columnId?: string
    name?: string,
    status?: TaskStatus

    description?: string,
    deadline?: string

    authorId?: string,
    assigneeId?: string
}


// * =============== FORMS ===============
export interface TaskCreateForm {
    taskName: string;
    status: TaskStatus
    description?: string;
    deadline?: string
}

export interface TaskUpdateForm {
    name?: string;
    status: TaskStatus
    description?: string;
    deadline?: string

    // authorId?: string;
    // assigneeId?: string;
}