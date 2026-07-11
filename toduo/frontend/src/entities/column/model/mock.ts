import { mockTaskSummaries } from "@entities/task/model/mock";
import type { ColumnType, ColumnList, ColumnSummary } from "..";

export const mockColumns: ColumnType[] = [
    {
        id: "1",
        name: "Бэклог",
        position: 1,
        tasks: mockTaskSummaries()
    },
    {
        id: "2",
        name: "В работе",
        position: 2,
        tasks: []
    },
    {
        id: "3",
        name: "Готово",
        position: 3,
        tasks: []
    }
];


export function mockColumnSummaries(): ColumnSummary[] {
    return mockColumns.map(({ id, name }) => ({
        id,
        name
    }));
}

export function mockColumnLists(): ColumnList[] {
    return mockColumns.map(({ id, name, position }) => ({
        id,
        name,
        position
    }));
}

