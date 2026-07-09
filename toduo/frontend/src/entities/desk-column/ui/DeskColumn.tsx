import type { ReactNode } from "react";

import { Box, Stack, Typography } from "@mui/material";

import type { DeskColumn as DeskColumnType } from "..";


interface DeskColumnProps extends DeskColumnType {
    children: ReactNode
}


export const DeskColumn = ({ name, children,  }: DeskColumnProps) => {

    return (
        <Box
            sx={{
                width: 250,
                p: 2,
                borderRadius: 2,
                bgcolor: 'grey.100',
                transition: 'background-color 0.2s',
            }}
        >
            <Typography variant="h6" sx={{ mb: 2, fontWeight: 'bold' }}>{name}</Typography>
            <Stack spacing={2}>{children}</Stack>
        </Box>
    );
};