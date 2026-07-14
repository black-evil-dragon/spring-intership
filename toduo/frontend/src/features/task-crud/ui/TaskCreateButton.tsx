import { Add } from '@mui/icons-material';
import { Button } from '@mui/joy';
import { useState } from 'react';


import { TaskCreateModal } from './TaskCreateModal';


interface TaskCreateButtonProps {
    deskId: string;
    columnId: string;
}

export const TaskCreateButton = ({ deskId, columnId }: TaskCreateButtonProps) => {
    const [openModal, setOpenModal] = useState<boolean>(false);

    return (
        <>
            <Button
                variant="soft"
                color="neutral"
                startDecorator={<Add />}
                onClick={() => setOpenModal(true)}
            >
                Добавить задачу
            </Button>

            <TaskCreateModal
                deskId={deskId}
                columnId={columnId}
                open={openModal}
                setOpen={setOpenModal}
            />
        </>
    );
};
