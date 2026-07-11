import type { Task, TaskList, TaskSummary } from "../types";


export const mockTasks: Task[] = [
    {
        id: "1",
        name: "Разработать дизайн авторизации",
        description: "Создать фигма-макеты страницы входа.",
        status: "completed",
        column: { id: "1", name: "Бэклог" },
        author: { id: "1", firstName: "Иван", lastName: "Иванов" },
        assignee: { id: "2", firstName: "Мария", lastName: "Сидорова" },
        createdAt: "2026-07-01T10:00:00Z",
        updatedAt: "2026-07-03T15:30:00Z"
    },
    {
        id: "2",
        name: "Интеграция платежного шлюза",
        description: "Подключить Stripe API.",
        status: "in_work",
        column: { id: "2", name: "В работе" },
        author: { id: "2", firstName: "Мария", lastName: "Сидорова" },
        assignee: { id: "1", firstName: "Иван", lastName: "Иванов" },
        createdAt: "2026-07-05T09:15:00Z",
        updatedAt: "2026-07-10T14:00:00Z"
    }
];



export function mockTaskSummaries(): TaskSummary[] {
    return mockTasks.map(task => ({
        id: task.id,
        columnId: parseInt(task.column.id, 10),
        name: task.name,
        status: task.status,
        author: task.author,
        assignee: task.assignee
    }));
}

export function mockTaskLists(): TaskList[] {
    return mockTasks.map(task => ({
        id: task.id,
        name: task.name,
        author: { id: task.author.id, firstName: task.author.firstName, lastName: task.author.lastName },
        assignee: task.assignee
            ? { id: task.assignee.id, firstName: task.assignee.firstName, lastName: task.assignee.lastName }
            : undefined
    }));
}

