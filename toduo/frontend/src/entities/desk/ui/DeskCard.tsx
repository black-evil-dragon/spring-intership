import {
    Avatar,
    Button,
    Card,
    CardActions,
    CardContent,
    Divider,
    IconButton,
    Typography,
} from '@mui/joy';
import type { DeskSummary } from '../types';
import type { ReactNode } from 'react';

interface DeskCardProps extends DeskSummary {
    actions?: ReactNode
}

export const DeskCard = ({ name, actions }: DeskCardProps) => {
    return (
        <>
            <Card>
                <Avatar />
                <CardContent>
                    <Typography level="h3">{name}</Typography>
                </CardContent>

                <CardActions>
                    {actions}
                </CardActions>
            </Card>
        </>
    );
};
