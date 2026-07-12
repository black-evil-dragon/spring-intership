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
    Textarea,
} from '@mui/joy';
import type { Dispatch, SetStateAction } from 'react';
import { Controller, useForm, type SubmitHandler } from 'react-hook-form';

import type { TaskCreateForm } from '../types';
import { TaskStatusSelect } from '@entities/task';
import { useCreateTaskMutation } from '../api';





interface TaskCreateModalProps {
    deskId: number


    open: boolean;
    setOpen: Dispatch<SetStateAction<boolean>>;

}

export const TaskCreateModal = ({
    deskId,
    open,
    setOpen,

}: TaskCreateModalProps) => {

    const [createTask] = useCreateTaskMutation();

    const { control, handleSubmit, reset } = useForm<TaskCreateForm>({
        defaultValues: {
            taskName: '',
            description: '',
            deadline: '',
            status: 'await_work',
        },
    });

    const onSubmit: SubmitHandler<TaskCreateForm> = (data) => {
        createTask({
            deskId: deskId,

            name: data.taskName,
            description: data.description,
            status: data.status,

            authorId: 1,
        });
        setOpen(false);

    };

    return (
        <>
            <Modal open={open} onClose={() => setOpen(false)}>
                <ModalDialog>
                    <DialogTitle>Добавить задачу</DialogTitle>
                    <DialogContent>
                        Заполните поля, чтобы создать задачу
                    </DialogContent>
                    <form onSubmit={handleSubmit(onSubmit)}>
                        <Stack spacing={2}>
                            <Controller
                                name="taskName"
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
                            <Controller
                                name="description"
                                control={control}
                                render={({ field, fieldState: { error } }) => (
                                    <FormControl error={!!error}>
                                        <FormLabel>Описание</FormLabel>
                                        <Textarea {...field} />
                                        {error && (
                                            <FormHelperText>
                                                <InfoOutlined />
                                                {error.message}
                                            </FormHelperText>
                                        )}
                                    </FormControl>
                                )}
                            />
                            <Controller
                                name="deadline"
                                control={control}
                                render={({ field, fieldState: { error } }) => (
                                    <FormControl error={!!error}>
                                        <FormLabel>Крайний срок</FormLabel>
                                        <Input
                                            {...field}
                                            type="datetime-local"
                                        />
                                        {error && (
                                            <FormHelperText>
                                                <InfoOutlined />
                                                {error.message}
                                            </FormHelperText>
                                        )}
                                    </FormControl>
                                )}
                            />

                            <Controller
                                name="status"
                                control={control}
                                render={({ field, fieldState: { error } }) => (
                                    <TaskStatusSelect
                                        value={field.value}
                                        onChange={field.onChange}
                                        error={!!error}
                                        helperText={error?.message}
                                    />
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
