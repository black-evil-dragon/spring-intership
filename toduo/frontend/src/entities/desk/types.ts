import type { Column } from "@entities/column/types";
import type { UserList } from "@entities/user/types";
import type { AuditableEntity } from "@shared/types/entities";

export interface Desk extends AuditableEntity {
    id: string,

    name: string,
    owner: UserList,
    columns: Column[]
}


export interface DeskSummary {
    id: string,
    name: string,
}

export interface DeskList {
    id: string,
    name: string,
}