//* React
import { Routes, Route, BrowserRouter } from "react-router-dom";
import { Suspense } from "react";

//* App layout
import { Layout } from '@app/layout';



//* Pages
import { HomePage } from "@pages/home";
import { NoPage } from "@pages/404";
import { DeskPages } from "@pages/desks";



export function Routing() {
    return (
        <BrowserRouter>
            <Suspense>
                <Routes>
                    <Route path="/" element={<Layout />}>
                        <Route index element={<DeskPages />} />
                        <Route path="/desks/*" element={<DeskPages />} />

                        <Route path="/404" element={<NoPage />} />
                        <Route path="*" element={<NoPage />} />
                    </Route>
                </Routes>
            </Suspense>
        </BrowserRouter>
    );
}
