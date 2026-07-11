import type { AuditableEntity } from "@shared/types/entities";



export interface User extends AuditableEntity {
    id: string,

    email: string,

    firstName: string,
    lastName: string,
}

export interface UserSummary {
    id: string,
    firstName: string,
    lastName: string,
}

export interface UserList {
    id: string,
    firstName: string,
    lastName: string,
}