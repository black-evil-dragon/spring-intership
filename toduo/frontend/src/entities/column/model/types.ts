import type { TaskSummary } from "@entities/task";


export interface ColumnType {
    id: string,

    name: string,

    position: number,

    tasks: TaskSummary[]
}

export interface ColumnSummary {
    id: string,
    name: string,
}


export interface ColumnList {
    id: string,
    name: string,
    position: number,
}