import { Outlet } from "react-router-dom";


import { Header } from "@widgets/Header";
import { Footer } from '@widgets/Footer';


export function Layout() {

    return (<>
        {/* App header component */}
        <Header />


        {/* App content */}
        <Outlet />

        {/* App footer component */}
        <Footer />
    </>);
}
