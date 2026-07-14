import { Breadcrumbs, Link, Typography } from "@mui/joy";
import type { BreacrumbsItem } from '@shared/types';
import { NavLink } from "react-router-dom";

export const PageBreadcrumbs = ({
    breadcrumbs,
}: {
    breadcrumbs: BreacrumbsItem[];
}) => {
    return (
        <Breadcrumbs sx={{ px: 0}}>
            {breadcrumbs.map((item) =>
                item.url ? (
                    <Link key={item.label} component={NavLink} to={item.url}>
                        {item.label}
                    </Link>
                ) : (
                    <Typography key={item.label}>{item.label}</Typography>
                ),
            )}
        </Breadcrumbs>
    );
};