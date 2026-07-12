import { Add } from '@mui/icons-material';
import { Button } from '@mui/joy';
import { useState } from 'react';
import { TaskCreateModal } from './TaskCreateModal';


interface TaskAddProps {
    deskId: number
}

export const TaskAdd = ({ deskId }: TaskAddProps) => {
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
                open={openModal}
                setOpen={setOpenModal}
            />
        </>
    );
};
