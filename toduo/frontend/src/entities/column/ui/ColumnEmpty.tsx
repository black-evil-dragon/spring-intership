import type { ReactNode } from 'react';

import { Card, Divider, Stack } from '@mui/joy';
import { Typography } from '@mui/joy';

import type { ColumnType } from '..';

interface ColumnProps {
    actions?: ReactNode;
}

export const ColumnEmpty = ({ actions }: ColumnProps) => {
    return (
        <Card
            sx={{
                width: '300px',
                borderStyle: 'dashed',
                height: '100%',
                flexShrink: 0
            }}
        >
            {actions}
        </Card>
    );
};
