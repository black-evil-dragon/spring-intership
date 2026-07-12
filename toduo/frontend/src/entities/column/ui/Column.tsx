import type { ReactNode } from 'react';

import { Card, Divider, Stack } from '@mui/joy';
import { Typography } from '@mui/joy';

import type { ColumnType } from '..';

interface ColumnProps extends ColumnType {
    children?: ReactNode;
    actions?: ReactNode;
}

export const Column = ({ name, children, actions }: ColumnProps) => {
    return (
        <Card
            sx={{
                width: 300,
                height: '100%',
                flexShrink: 0,
            }}
        >
            <Stack direction={'row'} justifyContent={'space-between'}>
                <Typography level="h3">{name}</Typography>
                {actions}
            </Stack>
            <Divider />
            {children}
        </Card>
    );
};
