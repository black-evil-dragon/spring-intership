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

import { useDeleteColumnMutation } from '../api';

interface ColumnAddModalProps {
    open: boolean;
    setOpen: Dispatch<SetStateAction<boolean>>;

    deskId: string;
    columnId: string;
}

export const ColumnDeleteModal = ({
    open,
    setOpen,
    deskId,
    columnId,
}: ColumnAddModalProps) => {
    const [deleteColumn] = useDeleteColumnMutation();
    const { handleSubmit } = useForm();

    const onSubmit: SubmitHandler<{}> = () => {
        deleteColumn({
            deskId,
            columnId,
        });
        setOpen(false);
    };

    return (
        <Modal open={open} onClose={() => setOpen(false)}>
            <ModalDialog>
                <DialogTitle>Удалить колонку</DialogTitle>
                <DialogContent sx={{ maxWidth: '400px' }}>
                    Убедитесь, что в ней не осталось задач. Задачи из удаленной
                    колонки попадут в первую колонку
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
