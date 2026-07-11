import { Add } from "@mui/icons-material";
import { Button } from "@mui/joy";



export const TaskAdd = () => {
    return (<>
        <Button
            variant="soft"
            color="neutral"
            startDecorator={<Add />}
        >
            Добавить задачу
        </Button>
    </>);
};