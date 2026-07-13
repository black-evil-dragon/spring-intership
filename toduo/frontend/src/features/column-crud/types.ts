export interface ColumnCreateRequest {
    deskId: string,
    name: string,
    newPosition?: number
}

export interface ColumnDeleteRequest {
    deskId: string,
    columnId: string
}

export interface ColumnUpdateRequest {
    deskId: string,
    columnId: string
    name?: string,
    newPosition?: number
}