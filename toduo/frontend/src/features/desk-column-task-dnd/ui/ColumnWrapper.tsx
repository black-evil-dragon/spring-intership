import { useSortable } from '@dnd-kit/sortable';
import { CSS } from '@dnd-kit/utilities';
import Box from '@mui/joy/Box';
import type { ReactNode } from 'react';



interface ColumnWrapperProps {
    columnId: string;
    position: number;
    children: ReactNode;
}

export const ColumnWrapper = ({
    columnId,
    position,
    children,
}: ColumnWrapperProps) => {
    const {
        attributes,
        listeners,
        setNodeRef,
        transform,
        transition,
        isDragging,
    } = useSortable({
        id: columnId,
        data: { type: 'COLUMN', columnId, position },
    });

    return (
        <Box
            ref={setNodeRef}
            {...attributes}
            {...listeners}
            sx={{
                transform: CSS.Transform.toString(transform),
                transition,
                opacity: isDragging ? 0.4 : 1,
                cursor: isDragging ? 'grabbing' : 'grab',
                height: '100%',
                display: 'flex',
            }}
        >
            {children}
        </Box>
    );
};
