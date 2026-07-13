import { Button } from "@mui/joy";
import { useState } from "react";
import { TaskEditPanel } from "./TaskEditPanel";
import { Edit } from "@mui/icons-material";
import type { Task } from "@entities/task";


interface TaskEditButtonProps {
    task: Task
}

export const TaskEditButton = (props: TaskEditButtonProps) => {
    const [open, setOpen] = useState(false)
    return (
        <>
            <Button onClick={() => setOpen(true)} startDecorator={<Edit />}>Редактировать</Button>

            <TaskEditPanel open={open} setOpen={setOpen} {...props} />
        </>
    );
};