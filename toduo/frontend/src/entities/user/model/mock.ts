import type { User, UserList, UserSummary } from "./types";

export const mockUsers: User[] = [
    {
        id: "1",
        email: "ivan.ivanov@company.com",
        firstName: "Иван",
        lastName: "Иванов",
        createdAt: "2026-01-15T08:00:00Z",
        updatedAt: "2026-03-10T12:30:00Z"
    },
    {
        id: "2",
        email: "maria.sidorova@company.com",
        firstName: "Мария",
        lastName: "Сидорова",
        createdAt: "2026-02-01T09:15:00Z",
        updatedAt: "2026-02-01T09:15:00Z"
    },
    {
        id: "3",
        email: "alex.petrov@company.com",
        firstName: "Алексей",
        lastName: "Петров",
        createdAt: "2026-03-20T14:00:00Z",
        updatedAt: "2026-05-11T16:45:00Z"
    },
    {
        id: "4",
        email: "elena.kozlova@company.com",
        firstName: "Елена",
        lastName: "Козлова",
        createdAt: "2026-04-12T11:20:00Z",
        updatedAt: "2026-04-12T11:20:00Z"
    },
    {
        id: "5",
        email: "dmitry.fedorov@company.com",
        firstName: "Дмитрий",
        lastName: "Федоров",
        createdAt: "2026-05-05T10:00:00Z",
        updatedAt: "2026-06-01T09:00:00Z"
    }
];


export function mockUserSummaries(): UserSummary[] {
    return mockUsers.map(({ id, firstName, lastName }) => ({
        id,
        firstName,
        lastName
    }));
}

export function mockUserLists(): UserList[] {
    return mockUsers.map(({ id, firstName, lastName }) => ({
        id,
        firstName,
        lastName
    }));
}