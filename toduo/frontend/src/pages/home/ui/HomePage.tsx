import { Container } from '@mui/material';

import { Desk } from '@entities/desk/ui/Desk';



export const HomePage = () => {

    return (<>
        <Container maxWidth="xl" sx={{
            height: "100%"
        }}>
            <Desk />
        </Container>
    </>);
};