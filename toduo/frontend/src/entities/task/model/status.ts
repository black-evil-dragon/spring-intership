import type { ColorPaletteProp } from "@mui/joy";
import type { TaskStatus } from "..";

interface TaskStatusData {
    label: string,
    color: ColorPaletteProp,
}

export const getStatusConfig = (statusCode: TaskStatus) => {

    const mapping: Record<TaskStatus, TaskStatusData>  = {
        'await_control': {
            label: "Ожидает контроля",
            color: "warning"
        },
        'await_work': {
            label: "Ожидает выполнения",
            color: "warning"
        },
        'in_work': {
            label: "Выполняется",
            color: "primary"
        },
        'completed': {
            label: "Завершено",
            color: "warning"
        },
    }

    return mapping[statusCode] || {
        label: "Неизвестно",
        color: "neutral"
    }
}