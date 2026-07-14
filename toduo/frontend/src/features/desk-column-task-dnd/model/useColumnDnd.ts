
import { KeyboardSensor, PointerSensor, useSensor, useSensors, type Active, type DragEndEvent, type DragStartEvent, type Over } from '@dnd-kit/core';
import { sortableKeyboardCoordinates } from '@dnd-kit/sortable';
import { useState } from 'react';


import type { ColumnType } from '@entities/column';
import { useUpdateColumnMutation } from '@features/column-crud';
import { useUpdateTaskMutation } from '@features/task-crud';

export type DndType = 'TASK' | 'COLUMN';

interface ActiveEntity {
    id: string;
    type: DndType;
    columnId?: string;
}

export const useColumnDnd = (deskId: string, columns: ColumnType[]) => {
    const [activeEntity, setActiveEntity] = useState<ActiveEntity | null>(null);

    const [updateTask] = useUpdateTaskMutation();
    const [updateColumn] = useUpdateColumnMutation();

    const sensors = useSensors(
        useSensor(PointerSensor, { activationConstraint: { distance: 8 } }),
    );

    const handleDragStart = (event: DragStartEvent) => {
        const { active } = event;
        const type = active.data.current?.type as DndType;
        setActiveEntity({
            id: String(active.id),
            type,
            columnId: active.data.current?.columnId,
        });
    };

    const handleDragEnd = async (event: DragEndEvent) => {
        setActiveEntity(null);
        const { active, over } = event;
        if (!over) return;

        const type = active.data.current?.type as DndType;

        if (type === 'COLUMN') {
            await processColumnMove(active, over);
        } else if (type === 'TASK') {
            await processTaskMove(active, over);
        }
    };

    const processColumnMove = async (active: Active, over: Over) => {
        const activeColumnId = active.data.current?.columnId;
        const overColumnId = over.data.current?.columnId || over.id

        if (activeColumnId == overColumnId) return;


        const sortedColumns = [...columns].sort((a, b) => a.position - b.position);

        const overIdx = sortedColumns.findIndex(c => c.id === overColumnId);

        if (overIdx !== -1) {
            const targetPosition = overIdx + 1;

            try {
                await updateColumn({
                    deskId,
                    columnId: activeColumnId,
                    newPosition: targetPosition
                }).unwrap();
            } catch (e) {
                console.error('Ошибка при перемещении колонки:', e);
            }
        }
    };

    const processTaskMove = async (active: any, over: any) => {
        const taskId = active.data.current?.taskId;
        const targetColumnId = over.data.current?.columnId || over.id;

        if (!targetColumnId || active.id === over.id) return;

        await updateTask({ taskId: taskId, columnId: targetColumnId }).unwrap();
    };

    return { sensors, activeEntity, handleDragStart, handleDragEnd };
};
