import Avatar from '@mui/joy/Avatar';
import { Box, Card, Chip, Stack, Typography } from '@mui/joy';

import { getUserInitials } from "@entities/user";


import type { TaskSummary } from "../types";
import { getStatusConfig } from '../model/status';
import { ArrowForward } from '@mui/icons-material';




interface TaskCardProps extends TaskSummary {

}

export const TaskCard = (taskSummary: TaskCardProps) => {

    const { name, author, assignee, status } = taskSummary



    const statusData = getStatusConfig(status)
    const authorInitials = getUserInitials({ firstName: author.firstName, lastName: author.lastName })
    const asigneeInitials = assignee ? getUserInitials({ firstName: assignee.firstName, lastName: assignee.lastName }) : null



    return (
        <Card
            sx={{ p: 2 }}
        >
            {/* Статус задачи */}
            <Chip color={statusData.color}>
                {statusData.label}
            </Chip>

            {/* Название задачи */}
            <Typography level='title-lg'>{name}</Typography>


            {/* Постановщик и испольнитель задачи */}
            <Stack direction={'row'} alignItems={'center'} gap={.5}>
                <Avatar
                    color="neutral"
                    size="md"
                    variant="soft"
                >
                    {authorInitials}
                </Avatar>



                {assignee && <>
                    <ArrowForward /><Avatar
                        color="neutral"
                        size="md"
                        variant="soft"
                    >
                        {asigneeInitials}
                    </Avatar>
                </>}
            </Stack>
        </Card>
    );
};