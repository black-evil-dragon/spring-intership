import type { DeskColumn } from "@entities/desk-column/model/types";
import type { UserList } from "@entities/user/model/types";
import type { AuditableEntity } from "@shared/types/entities";

export interface Desk extends AuditableEntity {
    id: string,

    name: string,
    owner: UserList,
    columns: DeskColumn[]
}


export interface DeskSummary {
    id: string,
    name: string,
}

export interface DeskList {
    id: string,
    name: string,
}