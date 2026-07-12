import { useNavigate, useParams } from 'react-router-dom';
import {
    Avatar,
    Breadcrumbs,
    Button,
    Card,
    Chip,
    CircularProgress,
    Container,
    Grid,
    Link,
    Stack,
    Table,
    Typography,
} from '@mui/joy';

import { useGetTaskQuery } from '@features/task-crud/api';

export const TaskDetail = () => {
    const navigate = useNavigate();
    const { id: taskId } = useParams();

    if (!taskId) {
        navigate(-1);
        return;
    }

    const { data, error, isLoading } = useGetTaskQuery(taskId);

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

    if (!data || error) {
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

    const { id, name, description, deadline, status } = data;

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
                    <Card>
                        <Stack gap={2}>
                            <Stack
                                justifyContent={'space-between'}
                                alignItems={'center'}
                                direction={'row'}
                            >
                                <Typography level="title-md">
                                    Исполнитель
                                </Typography>
                                <Avatar />
                            </Stack>

                            <Stack
                                justifyContent={'space-between'}
                                alignItems={'center'}
                                direction={'row'}
                            >
                                <Typography level="title-md">
                                    Постановщик
                                </Typography>
                                <Avatar />
                            </Stack>

                            <Stack
                                justifyContent={'space-between'}
                                alignItems={'center'}
                                direction={'row'}
                            >
                                <Typography level="title-md">
                                    Крайний срок
                                </Typography>
                                <Typography>
                                    {deadline || 'Не задан'}
                                </Typography>
                            </Stack>
                        </Stack>
                    </Card>
                    <Button variant="soft" color={'danger'} size={'sm'}>
                        Удалить
                    </Button>
                </Grid>
            </Grid>
        </Container>
    );
};
