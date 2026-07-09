import type { TaskSummary } from "@entities/task";


export interface DeskColumn {
    id: string,

    name: string,

    position: number,

    tasks: TaskSummary[]
}

export interface DeskColumnSummary {
    id: string,
    name: string,
}


export interface DeskColumnList {
    id: string,
    name: string,
    position: number,
}