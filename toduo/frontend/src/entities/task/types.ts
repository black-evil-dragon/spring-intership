import type { ColumnSummary } from "@entities/column";
import type { UserList, UserSummary } from "@entities/user";
import type { AuditableEntity } from "@shared/types/entities";


export type TaskStatus = "await_work" | "await_control" | "in_work" | "completed"


export interface Task extends AuditableEntity {
    id: string,

    column: ColumnSummary,

    name: string,
    description: string,

    status: TaskStatus,

    author: UserSummary,
    assignee?: UserSummary,
}



export interface TaskSummary {
    id: string,

    columnId: number,

    name: string,
    status: TaskStatus,

    author: UserSummary,
    assignee?: UserSummary,
}


export interface TaskList {
    id: string,

    name: string,

    author: UserList,
    assignee?: UserList,
}