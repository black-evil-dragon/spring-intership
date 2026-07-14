import { InfoOutlined } from '@mui/icons-material';
import {
    Button,
    DialogContent,
    DialogTitle,
    FormControl,
    FormHelperText,
    FormLabel,
    Input,
    Modal,
    ModalDialog,
    Stack,
} from '@mui/joy';
import type { Dispatch, SetStateAction } from 'react';
import { Controller, useForm, type SubmitHandler } from 'react-hook-form';
import { useCreateColumnMutation } from '../api';



interface FormInputs {
    name: string;
}

interface ColumnAddModalProps {
    open: boolean;
    setOpen: Dispatch<SetStateAction<boolean>>;

    deskId: string;
    newPosition?: number;
}

export const ColumnAddModal = ({
    open,
    setOpen,
    deskId,
    newPosition,
}: ColumnAddModalProps) => {
    const [createColumn, { error, isError, isLoading }] =
        useCreateColumnMutation();

    const { control, handleSubmit, reset } = useForm<FormInputs>({
        defaultValues: {
            name: '',
        },
    });

    const onSubmit: SubmitHandler<FormInputs> = (data) => {
        createColumn({
            deskId,
            name: data.name,
            newPosition,
        });
        setOpen(false);
        reset();
    };

    return (
        <>
            <Modal open={open} onClose={() => setOpen(false)}>
                <ModalDialog>
                    <DialogTitle>Добавить колонку</DialogTitle>
                    <DialogContent>
                        Название колонки (например, "В работе", "Тестирование")
                    </DialogContent>
                    <form onSubmit={handleSubmit(onSubmit)}>
                        <Stack spacing={2}>
                            <Controller
                                name="name"
                                control={control}
                                rules={{ required: 'Это поле обязательно' }}
                                render={({ field, fieldState: { error } }) => (
                                    <FormControl error={!!error}>
                                        <FormLabel>Название</FormLabel>
                                        <Input {...field} autoFocus />
                                        {error && (
                                            <FormHelperText>
                                                <InfoOutlined />
                                                {error.message}
                                            </FormHelperText>
                                        )}
                                    </FormControl>
                                )}
                            />
                            <Button type="submit">Добавить</Button>
                        </Stack>
                    </form>
                </ModalDialog>
            </Modal>
        </>
    );
};
