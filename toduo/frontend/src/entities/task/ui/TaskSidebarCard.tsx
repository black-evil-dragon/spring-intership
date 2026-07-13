import { Avatar, Card, Chip, Stack, Typography } from '@mui/joy';
import type { Task } from '../types';
import { getUserInitials } from '@entities/user';
import type { ReactNode } from 'react';
import { getStatusConfig } from '../model/status';
import { formatDisplayDate } from '@shared/utils/date';

const SidebarRow = ({
    fieldName,
    content,
}: {
    fieldName: string;
    content: ReactNode;
}) => {
    return (
        <Stack
            justifyContent={'space-between'}
            alignItems={'center'}
            direction={'row'}
        >
            <Typography level="title-md">{fieldName}</Typography>
            {content}
        </Stack>
    );
};

interface TaskSidebarCard extends Partial<Task> {}

export const TaskSidebarCard = ({
    author,
    assignee,
    deadline,
    status,
}: TaskSidebarCard) => {
    const authorInitials =
        author &&
        getUserInitials({
            firstName: author.firstName,
            lastName: author.lastName,
        });

    const assigneeInitials =
        assignee &&
        getUserInitials({
            firstName: assignee.firstName,
            lastName: assignee.lastName,
        });

    const statusData = status && getStatusConfig(status)

    return (
        <>
            <Card>
                <Stack gap={2}>
                    <SidebarRow
                        fieldName={'Исполнитель'}
                        content={
                            author ? (
                                <Avatar>{authorInitials}</Avatar>
                            ) : (
                                'Не задан'
                            )
                        }
                    />

                    <SidebarRow
                        fieldName={'Постановщик'}
                        content={
                            assignee ? (
                                <Avatar>{assigneeInitials}</Avatar>
                            ) : (
                                'Не задан'
                            )
                        }
                    />

                    <SidebarRow
                        fieldName={'Крайний срок'}
                        content={
                            <Typography>
                                {formatDisplayDate(deadline) || 'Не задан'}
                            </Typography>
                        }
                    />

                    {statusData && (
                        <SidebarRow
                            fieldName={'Статус'}
                            content={
                                <Chip
                                    variant={statusData.variant}
                                    color={statusData.color}
                                    size="sm"
                                >
                                    {statusData.label}
                                </Chip>
                            }
                        />
                    )}
                </Stack>
            </Card>
        </>
    );
};
