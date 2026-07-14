//* React
import { Routes, Route, BrowserRouter } from "react-router-dom";
import { Suspense } from "react";

//* App layout
import { Layout } from '@app/layout';



//* Pages
import { NoPage } from "@pages/404";
import { DeskPages } from "@pages/desks";
import { TaskPages } from "@pages/tasks/ui/TaskPages";



export function Routing() {
    return (
        <BrowserRouter>
            <Suspense>
                <Routes>
                    <Route path="/" element={<Layout />}>
                        <Route index element={<DeskPages />} />
                        <Route path="/desks/*" element={<DeskPages />} />
                        <Route path="/tasks/*" element={<TaskPages />} />

                        <Route path="/404" element={<NoPage />} />
                        <Route path="*" element={<NoPage />} />
                    </Route>
                </Routes>
            </Suspense>
        </BrowserRouter>
    );
}
