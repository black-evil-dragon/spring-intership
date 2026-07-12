import { Navigate, useParams } from 'react-router-dom';
import { Chip, CircularProgress, Container, Stack, Typography } from '@mui/joy';

import { TaskAdd } from '@features/task-add/ui/TaskAdd';
import { ColumnAddButton, ColumnAddIcon } from '@features/column-add';

import { DeskColumns, useGetDeskDetailQuery } from '@entities/desk';
import { Column } from '@entities/column';
import { TaskCard } from '@entities/task';
import { ColumnEmpty } from '@entities/column/ui/ColumnEmpty';

export const DeskDetail = () => {
    const { id: deskId } = useParams();

    if (!deskId) {
        <Navigate to={'desks'} />;
    }

    const { data, error, isLoading } = useGetDeskDetailQuery(deskId!);

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

    return (
        <>
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
                                <ColumnAddIcon
                                    newPosition={column.position + 1}
                                    deskId={deskId!}
                                />
                            }
                        >
                            <TaskAdd />
                            {column.tasks.map((task, index) => (
                                <TaskCard {...task} key={index} />
                            ))}
                        </Column>
                    ))}

                    <ColumnEmpty
                        actions={<ColumnAddButton deskId={deskId!} />}
                    />
                </DeskColumns>
            </Container>
        </>
    );
};
