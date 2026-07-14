import type { ColumnSummary } from "@entities/column";
import type { UserList, UserSummary } from "@entities/user";
import type { AuditableEntity } from "@shared/types/entities";


export type TaskStatus = "await_work" | "await_control" | "in_work" | "completed"

export interface TaskUsers<T = UserSummary> {
    author: T,
    assignee?: T,
}

export interface Task extends AuditableEntity, TaskUsers {
    id: string,

    deskId: string,
    column: ColumnSummary,

    name: string,
    description?: string,
    deadline?: string,

    status: TaskStatus,
}



export interface TaskSummary extends TaskUsers {
    id: string,

    columnId: number,

    name: string,
    status: TaskStatus,
}


export interface TaskList extends TaskUsers<UserList> {
    id: string,

    name: string,
}