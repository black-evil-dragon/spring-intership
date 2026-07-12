import type { ColorPaletteProp, VariantProp } from "@mui/joy";
import type { TaskStatus } from "..";

interface TaskStatusData {
    label: string,
    color: ColorPaletteProp,
    variant: VariantProp
}

export const STATUSES: Record<TaskStatus, TaskStatusData> = {
    await_work: { label: 'Ожидает выполнения', color: 'danger', variant: 'soft' },
    in_work: { label: 'Выполняется', color: 'primary', variant: 'soft' },
    await_control: { label: 'На проверке', color: 'warning', variant: 'soft' },
    completed: { label: 'Готово', color: 'success', variant: 'solid' },
} as const;


export const getStatusConfig = (statusCode: TaskStatus) => {
    return STATUSES[statusCode] || {
        label: "Неизвестно",
        color: "neutral",
        variant: "soft"
    }
}