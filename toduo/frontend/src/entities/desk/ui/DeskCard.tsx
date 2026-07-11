import { Card, Link } from "@mui/joy";
import type { DeskSummary } from "../types";
import { NavLink } from "react-router-dom";


interface DeskCardProps extends DeskSummary {}

export const DeskCard = ({ id, name }: DeskCardProps) => {
    return (<>
        <Link component={NavLink} to={`/desks/${id}`} overlay>
            <Card>
                {name}
            </Card>
        </Link>
    </>);
};