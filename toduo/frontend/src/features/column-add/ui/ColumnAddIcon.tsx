import { Add } from '@mui/icons-material';
import { Button, IconButton } from '@mui/joy';

export const ColumnAddIcon = () => {
    return (
        <>
            <IconButton variant="soft" size="sm">
                <Add />
            </IconButton>
        </>
    );
};

export const ColumnAdd = () => {
    return (
        <>
            <Button
                variant="soft"
                color="neutral"
                size="sm"
                startDecorator={<Add />}
            >
                Добавить колонку
            </Button>
        </>
    );
};