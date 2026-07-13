
import { DragOverlay } from '@dnd-kit/core';
import Box from '@mui/joy/Box';


import { TaskCard } from '@entities/task';
import { Column } from '@entities/column';

interface OverlayProps {
    activeEntity: { id: string; type: 'TASK' | 'COLUMN' } | null;
    columns: any[];
}

export const DeskWrapper = ({ activeEntity, columns }: OverlayProps) => {
    if (!activeEntity) return null;

    const renderContent = () => {
        if (activeEntity.type === 'TASK') {
            const taskCleanId = activeEntity.id.replace('task-', '');
            const task = columns
                .flatMap((c) => c.tasks)
                .find((t) => String(t.id) === taskCleanId);
            return task ? (
                <Box sx={{ transform: 'rotate(3deg)', width: '100%' }}>
                    <TaskCard {...task} />
                </Box>
            ) : null;
        }

        if (activeEntity.type === 'COLUMN') {
            const colCleanId = activeEntity.id.replace('column-', '');
            const column = columns.find((c) => String(c.id) === colCleanId);
            return column ? (
                <Box
                    sx={{
                        transform: 'rotate(2deg)',
                        height: '100%',
                        opacity: 0.9,
                    }}
                >
                    <Column {...column} actions={null}>
                        {column.tasks.map((t: any) => (
                            <TaskCard key={t.id} {...t} />
                        ))}
                    </Column>
                </Box>
            ) : null;
        }
        return null;
    };

    return <DragOverlay dropAnimation={null}>{renderContent()}</DragOverlay>;
};
