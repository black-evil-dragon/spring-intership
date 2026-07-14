import { useSortable } from "@dnd-kit/sortable";
import { CSS } from "@dnd-kit/utilities";
import { Box } from "@mui/joy";
import type { ReactNode } from "react";

interface TaskWrapperProps {
    taskId: string;
    columnId: string;
    children: ReactNode;
}

export const TaskWrapper = ({ taskId, columnId, children }: TaskWrapperProps) => {
    const {
        attributes,
        listeners,
        setNodeRef,
        transform,
        transition,
        isDragging,
    } = useSortable({
        id: taskId,
        data: { type: 'TASK', taskId, columnId },
    });

    return (
        <Box
            ref={setNodeRef}
            {...attributes}
            {...listeners}
            sx={{
                transform: CSS.Transform.toString(transform),
                transition,
                opacity: isDragging ? 0.2 : 1,
                cursor: isDragging ? 'grabbing' : 'grab',
                width: '100%',
            }}
        >
            {children}
        </Box>
    );
};
