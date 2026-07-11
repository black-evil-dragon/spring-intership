import { Stack } from '@mui/joy';
import type { ReactNode } from 'react';

interface DeskColumnsProps {
    children?: ReactNode;
}

export const DeskColumns = ({ children }: DeskColumnsProps) => {
    return (
        <>
            <Stack
                gap={2}
                direction={'row'}
                sx={{
                    width: '100%',
                    height: '90vh',
                    flexGrow: 1,
                    overflowX: 'auto',
                    overflowY: 'hidden',
                    scrollbarWidth: 'thin',

                    alignItems: 'stretch',
                }}
            >
                {children}
            </Stack>
        </>
    );
};
