import {
    Box,
    Button,
    Chip,
    CircularProgress,
    Container,
    Grid,
    IconButton,
    Link,
    Stack,
} from '@mui/joy';

import { useGetDesksQuery } from '@entities/desk';
import { DeskCard } from '@entities/desk';

import { NavLink } from 'react-router-dom';
import { Edit } from '@mui/icons-material';

export const DeskList = () => {
    const { data: desksData, isLoading } = useGetDesksQuery();

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

    const { content: desks } = desksData!;

    if (!desks.length || desks.length == 0) {
        return (
            <Stack
                justifyContent={'center'}
                alignItems={'center'}
                gap={2}
                sx={{
                    m: 'auto',
                }}
            >
                <Chip>Вы еще не создавали свое волшебное постранство✨</Chip>
                <Button>Давайте это исправим!</Button>
            </Stack>
        );
    }

    return (
        <>
            <Container
                sx={{
                    height: '100%',
                }}
            >
                <Grid container spacing={2}>
                    {desks &&
                        [...desks, ...desks, ...desks, ...desks].map(
                            (desk, index) => (
                                <Grid xs={3} key={`dc-${desk.id}${index}`}>
                                    <Link
                                        component={NavLink}
                                        to={`/desks/${desk.id}`}
                                        underline="none"
                                        overlay
                                        sx={{
                                            position: 'relative',
                                            display: 'block',
                                        }}
                                    >
                                        <DeskCard
                                            {...desk}
                                            actions={
                                                <>
                                                    <Button variant="soft">
                                                        Перейти
                                                    </Button>
                                                    <IconButton variant="soft">
                                                        <Edit />
                                                    </IconButton>
                                                </>
                                            }
                                        />
                                    </Link>
                                </Grid>
                            ),
                        )}
                </Grid>
            </Container>
        </>
    );
};

