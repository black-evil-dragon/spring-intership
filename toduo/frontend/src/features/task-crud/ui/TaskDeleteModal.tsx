import {
    Button,
    DialogContent,
    DialogTitle,
    Modal,
    ModalDialog,
    Stack,
} from '@mui/joy';
import type { Dispatch, SetStateAction } from 'react';
import { useForm, type SubmitHandler } from 'react-hook-form';


import { useDeleteTaskMutation } from '../api';



interface TaskDeleteModalProps {
    open: boolean;
    setOpen: Dispatch<SetStateAction<boolean>>;

    taskId: string;
    onSuccess?: () => void
}

export const TaskDeleteModal = ({
    open,
    setOpen,
    taskId,
    onSuccess,
}: TaskDeleteModalProps) => {
    const [deleteTask] = useDeleteTaskMutation();
    const { handleSubmit } = useForm();

    const onSubmit: SubmitHandler<{}> = () => {
        deleteTask({ taskId });
        onSuccess?.();
        setOpen(false);
    };

    return (
        <Modal open={open} onClose={() => setOpen(false)}>
            <ModalDialog>
                <DialogTitle>Удалить задачу</DialogTitle>
                <DialogContent sx={{ maxWidth: '400px' }}>
                    Вы уверены, что хотите удалить задачу?
                </DialogContent>
                <form onSubmit={handleSubmit(onSubmit)}>
                    <Stack spacing={2} direction={'row'}>
                        <Button type="submit" color="danger">
                            Подтвердить
                        </Button>
                        <Button
                            onClick={() => setOpen(false)}
                            variant="soft"
                            color="neutral"
                        >
                            Отмена
                        </Button>
                    </Stack>
                </form>
            </ModalDialog>
        </Modal>
    );
};
