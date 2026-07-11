

import { Container } from '@mui/joy';

import { Desk } from '@entities/desk';
import { Column } from '@entities/column';
import { TaskCard } from '@entities/task';

import { mockColumns } from '@entities/column/model/mock';
import { mockTaskSummaries } from '@entities/task/model/mock';
import { TaskAdd } from '@features/task-add/ui/TaskAdd';
import { ColumnAdd } from '@features/column-add/ui/ColumnAdd';
import { useState } from 'react';



export const HomePage = () => {

    const [columns, setColumns] = useState(mockColumns)

    return (<>
        <Container maxWidth="xl" sx={{
            height: "100%"
        }}>


            <Desk>
                {columns.map((column, index) => (
                    <Column {...column} key={index} actions={<ColumnAdd />}>
                        <TaskAdd />
                        {column.tasks.map((task, index) => (
                            <TaskCard {...task} key={index} />
                        ))}

                    </Column>
                ))}
            </Desk>
        </Container>
    </>);
};