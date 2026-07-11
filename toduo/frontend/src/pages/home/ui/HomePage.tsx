

import { Container, Typography } from '@mui/joy';

import { Desk } from '@entities/desk';
import { Column } from '@entities/column';
import { TaskCard } from '@entities/task';

import { mockColumns } from '@entities/column/model/mock';
import { mockTaskSummaries } from '@entities/task/model/mock';
import { TaskAdd } from '@features/task-add/ui/TaskAdd';
import { ColumnAdd } from '@features/column-add/ui/ColumnAdd';
import { useState } from 'react';
import { useGetDesksQuery } from '@entities/desk/api';



export const HomePage = () => {

    const { data } = useGetDesksQuery()

    return (<>
        <Container maxWidth="xl" sx={{
            height: "100%"
        }}>


        </Container>
    </>);
};


{/* <Desk>
    {columns.map((column, index) => (
        <Column {...column} key={index} actions={<ColumnAdd />}>
            <TaskAdd />
            {column.tasks.map((task, index) => (
                <TaskCard {...task} key={index} />
            ))}
        </Column>
    ))}
</Desk>; */}