
import { TaskCard } from '@entities/task/ui/TaskCard';
import { mockTaskSummaries } from '@entities/task/model/mock';
import { Container } from '@mui/joy';



export const HomePage = () => {

    return (<>
        <Container maxWidth="xl" sx={{
            height: "100%"
        }}>
            {mockTaskSummaries().map((task, index) => (
                <TaskCard {...task} key={index} />
            ))}
        </Container>
    </>);
};