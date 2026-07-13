import {
    Chip,
    CircularProgress,
    Container,
    Grid,
    Stack,
    Typography
} from '@mui/joy';
import { useNavigate, useParams } from 'react-router-dom';

import { TaskSidebarCard } from '@entities/task';
import { TaskDeleteButton, TaskEditButton } from '@features/task-crud';
import { useGetTaskQuery } from '@features/task-crud/api';

export const TaskDetail = () => {
    const navigate = useNavigate();
    const { id: taskId } = useParams();

    if (!taskId) {
        navigate(-1);
        return;
    }

    const { data: taskData, error, isLoading } = useGetTaskQuery(taskId);

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

    if (!taskData || error) {
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
                <Chip color="danger">
                    Упс, похоже мы не смогли найти задачу
                </Chip>
            </Stack>
        );
    }

    const { id, name, description, deadline, status, deskId } = taskData;

    return (
        <Container>
            <Grid container spacing={3}>
                <Grid xs>
                    <Typography level="h1">{name}</Typography>

                    <Typography>{description}</Typography>
                </Grid>

                <Grid
                    xs={4}
                    sx={{
                        display: 'flex',
                        flexDirection: 'column',
                        gap: 2,
                    }}
                >
                    <TaskSidebarCard {...taskData} />
                    <TaskEditButton task={taskData} />
                    <TaskDeleteButton
                        taskId={id}
                        onSuccess={() => navigate(`/desks/${deskId}`)}
                    />
                </Grid>
            </Grid>
        </Container>
    );
};
