import { InfoOutlined } from '@mui/icons-material';
import {
    Box,
    Button,
    Drawer,
    FormControl,
    FormHelperText,
    FormLabel,
    Input,
    Stack,
    Textarea,
    Typography,
} from '@mui/joy';
import type { Dispatch, SetStateAction } from 'react';
import { Controller, useForm, type SubmitHandler } from 'react-hook-form';

import { TaskStatusSelect, type Task } from '@entities/task';

import type { TaskUpdateForm } from '../types';
import { useUpdateTaskMutation } from '../api';
import { formatToInstant } from '@shared/utils/date';

interface TaskEditPanelProps {
    open: boolean;
    setOpen: Dispatch<SetStateAction<boolean>>;

    task: Task;
    onSuccess?: () => void;
}

export const TaskEditPanel = ({ open, setOpen, task }: TaskEditPanelProps) => {

    const [updateTask] = useUpdateTaskMutation()

    const {
        control,
        handleSubmit,
        reset,
        formState: { dirtyFields },
    } = useForm<TaskUpdateForm>({
        defaultValues: {
            name: task.name,
            description: task.description,
            deadline: task.deadline ?? '',
            status: task.status,
        },
    });

    const onSubmit: SubmitHandler<TaskUpdateForm> = (data) => {
        const changedFields: Partial<TaskUpdateForm> = {};

        Object.keys(dirtyFields).forEach((key) => {
            const fieldKey = key as keyof TaskUpdateForm;
            Object.assign(changedFields, { [fieldKey]: data[fieldKey] });
        });

        updateTask({
            taskId: task.id,
            ...changedFields,
            deadline:
                changedFields.deadline &&
                formatToInstant(changedFields.deadline) || "",
        });
        setOpen(false);
        // reset();
    };

    return (
        <Drawer open={open} onClose={() => setOpen(false)} anchor={'right'}>
            <Box sx={{ p: 2 }}>
                <Typography level="title-lg">Изменить задачу</Typography>
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
                                        value={field.value ?? ''}
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

                        <Button type="submit">Изменить</Button>
                    </Stack>
                </form>
            </Box>
        </Drawer>
    );
};
