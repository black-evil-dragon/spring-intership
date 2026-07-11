import { Container, Stack } from "@mui/joy";
import type { ReactNode } from "react";

interface DeskProps {
    children?: ReactNode
}


export const Desk = ({ children }: DeskProps) => {
    return (<>

        <Container>
            <Stack gap={2} direction={'row'} sx={{
                width: 'max-content',
                overflow: 'auto'
            }}>
                {children}
            </Stack>
        </Container>
    </>)
};