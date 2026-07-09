import { Route, Routes } from "react-router-dom";


import { UserList } from "./UserList";



export const UserPages = () => {
    return (<>
        <Routes>
            <Route index element={<UserList />} />
        </Routes>
    </>);
};