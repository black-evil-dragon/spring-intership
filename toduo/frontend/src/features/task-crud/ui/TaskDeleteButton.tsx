import { Button } from '@mui/joy';
import { useState } from 'react';

import { TaskDeleteModal } from './TaskDeleteModal';


interface TaskDeleteButtonProps {
    taskId: string;
    onSuccess?: () => void;
}

export const TaskDeleteButton = (props: TaskDeleteButtonProps) => {
    const [openModal, setOpenModal] = useState<boolean>(false);

    return (
        <>
            <Button
                variant="soft"
                color={'danger'}
                size={'sm'}
                onClick={() => setOpenModal(true)}
            >
                Удалить задачу
            </Button>

            <TaskDeleteModal
                open={openModal}
                setOpen={setOpenModal}
                {...props}
            />
        </>
    );
};
