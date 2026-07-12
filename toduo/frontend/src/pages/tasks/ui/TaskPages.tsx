import { Route, Routes } from "react-router-dom";


import { TaskDetail } from "./TaskDetail";



export const TaskPages = () => {
    return (
        <Routes>
            <Route path="/">
                <Route path={':id'} element={<TaskDetail />} />
            </Route>
        </Routes>
    );
};