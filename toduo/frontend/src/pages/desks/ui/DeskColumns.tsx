import { DndContext, rectIntersection } from '@dnd-kit/core';

import {
    horizontalListSortingStrategy,
    SortableContext,
    verticalListSortingStrategy,
} from '@dnd-kit/sortable';

import { Box, Link, Stack } from '@mui/joy';

import {
    ColumnAddButton,
    ColumnAddIcon,
    ColumnDeleteIcon,
    useUpdateColumnMutation,
} from '@features/column-crud';

import {
    ColumnWrapper,
    DeskWrapper,
    TaskWrapper,
    useColumnDnd,
} from '@features/desk-column-task-dnd';

import { TaskCreateButton } from '@features/task-crud';

import { Column, ColumnEmpty, type ColumnType } from '@entities/column';
import { TaskCard, type TaskSummary } from '@entities/task';
import { NavLink } from 'react-router-dom';

interface DeskColumnsProps {
    deskId: string;
    columns: ColumnType[];
}

export const DeskColumns = ({ deskId, columns }: DeskColumnsProps) => {
    const [updateColumn] = useUpdateColumnMutation();
    const { sensors, activeEntity, handleDragStart, handleDragEnd } =
        useColumnDnd(deskId, columns);

    const columnIds = columns.map((c) => c.id);

    const onColumnRename = (
        deskId: string,
        columnId: string,
        newName: string,
    ) => {
        updateColumn({
            deskId,
            columnId,
            name: newName,
        });
    };

    return (
        <DndContext
            sensors={sensors}
            collisionDetection={rectIntersection}
            onDragStart={handleDragStart}
            onDragEnd={handleDragEnd}
        >
            <SortableContext
                items={columnIds}
                strategy={horizontalListSortingStrategy}
            >
                <Box
                    component={Stack}
                    gap={2}
                    direction="row"
                    sx={{
                        width: '100%',
                        height: '85vh',
                        flexGrow: 1,
                        overflowX: 'auto',
                        overflowY: 'hidden',
                        alignItems: 'stretch',

                        scrollbarWidth: 'thin',
                        '&::-webkit-scrollbar': { height: '8px' },
                        '&::-webkit-scrollbar-thumb': {
                            bgcolor: 'neutral.solidBg',
                            borderRadius: 'lg',
                        },
                    }}
                >
                    {columns.map((column) => {
                        const taskIds = column.tasks.map(
                            (t: TaskSummary) => t.id,
                        );

                        return (
                            <SortableContext
                                key={column.id}
                                items={taskIds}
                                strategy={verticalListSortingStrategy}
                            >
                                <ColumnWrapper
                                    columnId={column.id}
                                    position={column.position}
                                >
                                    {/* UI Колонки */}
                                    <Column
                                        {...column}
                                        onRename={(newName) =>
                                            onColumnRename(
                                                deskId,
                                                column.id,
                                                newName,
                                            )
                                        }
                                        // Действия в шапке колонки
                                        actions={
                                            <Stack direction="row" gap={1}>
                                                <ColumnAddIcon
                                                    newPosition={
                                                        column.position + 1
                                                    }
                                                    deskId={deskId}
                                                />
                                                <ColumnDeleteIcon
                                                    columnId={column.id}
                                                    deskId={deskId}
                                                />
                                            </Stack>
                                        }
                                    >
                                        {/* Кнопка создания задачи */}
                                        <TaskCreateButton
                                            deskId={deskId}
                                            columnId={column.id}
                                        />

                                        {/* Содержимое колонок */}
                                        {column.tasks.map((task: any) => (
                                            <TaskWrapper
                                                key={task.id}
                                                taskId={task.id}
                                                columnId={column.id}
                                            >
                                                <Link
                                                    component={NavLink}
                                                    to={`/tasks/${task.id}`}
                                                    sx={{ display: 'block', position: 'relative' }}
                                                    underline='none'
                                                    overlay
                                                >
                                                    <TaskCard {...task} />
                                                </Link>
                                            </TaskWrapper>
                                        ))}
                                    </Column>
                                </ColumnWrapper>
                            </SortableContext>
                        );
                    })}

                    {/* Пустая колонка с кнопкой создания */}
                    <ColumnEmpty
                        actions={<ColumnAddButton deskId={deskId} />}
                    />
                </Box>
            </SortableContext>

            <DeskWrapper activeEntity={activeEntity} columns={columns} />
        </DndContext>
    );
};
