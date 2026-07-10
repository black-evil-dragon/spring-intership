import { mockTaskSummaries } from "@entities/task/model/mock";
import type { DeskColumn, DeskColumnList, DeskColumnSummary } from "..";

export const mockDeskColumns: DeskColumn[] = [
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


export function mockDeskColumnSummaries(): DeskColumnSummary[] {
    return mockDeskColumns.map(({ id, name }) => ({
        id,
        name
    }));
}

export function mockDeskColumnLists(): DeskColumnList[] {
    return mockDeskColumns.map(({ id, name, position }) => ({
        id,
        name,
        position
    }));
}

