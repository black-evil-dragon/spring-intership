import { Route, Routes } from "react-router-dom";


import { DeskList } from "./DeskList";
import { DeskDetail } from "./DeskDetail";

export const DeskPages = () => {
    return (
        <>
            <Routes>
                <Route path="/">
                    <Route index element={<DeskList />} />

                    <Route path={':id'} element={<DeskDetail />} />
                </Route>
            </Routes>
        </>
    );
};