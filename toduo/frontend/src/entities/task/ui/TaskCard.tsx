import { Paper, Typography } from "@mui/material";


import type { TaskSummary } from "../model/types";


interface TaskCardProps extends TaskSummary {

}

export const TaskCard = ({ name }: TaskCardProps) => {


    return (
        <Paper
            sx={{ p: 2, cursor: 'grab', '&:active': { cursor: 'grabbing' } }}
        >
            <Typography>{name}</Typography>
        </Paper>
    );
};