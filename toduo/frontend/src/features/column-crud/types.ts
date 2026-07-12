export interface ColumnCreateRequest {
    deskId: string,
    name: string,
    newPosition?: number
}

export interface ColumnDeleteRequest {
    deskId: string,
    columnId: string
}