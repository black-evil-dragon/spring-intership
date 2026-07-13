import {
    Chip,
    CircularProgress,
    Container,
    Stack,
    Typography
} from '@mui/joy';
import { Navigate, useParams } from 'react-router-dom';



import { useGetDeskQuery } from '@features/desk-crud/api';

import { DeskColumns } from './DeskColumns';



export const DeskDetail = () => {
    const { id: deskId } = useParams();

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




    return (
        deskId && (
            <Container>
                <Typography level="h2" sx={{ mb: 3 }}>
                    {data?.name}
                </Typography>

                <DeskColumns
                    deskId={deskId}
                    columns={data?.columns || []}
                />
            </Container>
        )
    );
};
