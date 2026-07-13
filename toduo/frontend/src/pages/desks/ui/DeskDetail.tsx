import { Navigate, NavLink, useParams } from 'react-router-dom';
import {
    Chip,
    CircularProgress,
    Container,
    Link,
    Stack,
    Typography,
} from '@mui/joy';

import { TaskCreateButton } from '@features/task-crud';
import { useUpdateColumnMutation } from '@features/column-crud/api';
import {
    ColumnAddButton,
    ColumnAddIcon,
    ColumnDeleteIcon,
} from '@features/column-crud';

import { DeskColumns  } from '@entities/desk';
import { Column } from '@entities/column';
import { TaskCard } from '@entities/task';
import { ColumnEmpty } from '@entities/column';
import { useGetDeskQuery } from '@features/desk-crud/api';


export const DeskDetail = () => {
    const { id: deskId } = useParams();
    const [updateColumn] = useUpdateColumnMutation();

    if (!deskId) {
        <Navigate to={'desks'} />;
    }

    const { data, error, isLoading } = useGetDeskQuery(deskId!);

    if (isLoading) {
        return (
            <Stack
                justifyContent={'center'}
                alignItems={'center'}
                sx={{
                    m: 'auto',
                }}
            >
                <CircularProgress />
            </Stack>
        );
    }

    if (error) {
        return (
            <Stack
                justifyContent={'center'}
                alignItems={'center'}
                gap={2}
                sx={{
                    m: 'auto',
                }}
            >
                <Typography level="h1">404</Typography>
                <Chip color="danger">Упс, похоже мы не смогли найти доску</Chip>
            </Stack>
        );
    }


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
        deskId && (
            <Container>
                <Typography level="h2" sx={{ mb: 3 }}>
                    {data?.name}
                </Typography>
                <DeskColumns>
                    {data?.columns.map((column, index) => (
                        <Column
                            {...column}
                            key={index}
                            actions={
                                <Stack direction={'row'} gap={1}>
                                    <ColumnAddIcon
                                        newPosition={column.position + 1}
                                        deskId={deskId!}
                                    />
                                    <ColumnDeleteIcon
                                        columnId={column.id}
                                        deskId={deskId}
                                    />
                                </Stack>
                            }
                            onRename={(newName) => onColumnRename(deskId, column.id, newName)}
                        >
                            <TaskCreateButton
                                deskId={deskId}
                                columnId={column.id}
                            />
                            {column.tasks.map((task, index) => (
                                <Link
                                    key={`ct-${column.id}${task.id}${index}`}
                                    component={NavLink}
                                    to={`/tasks/${task.id}`}
                                    overlay
                                    underline="none"
                                    sx={{
                                        position: 'relative',
                                        display: 'block',
                                    }}
                                >
                                    <TaskCard {...task} />
                                </Link>
                            ))}
                        </Column>
                    ))}

                    <ColumnEmpty
                        actions={<ColumnAddButton deskId={deskId!} />}
                    />
                </DeskColumns>
            </Container>
        )
    );
};
