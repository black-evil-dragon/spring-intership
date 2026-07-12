import { Add } from "@mui/icons-material";
import { Button } from "@mui/joy";
import { useState } from "react";
import { ColumnAddModal } from "./ColumnAddModal";


interface ColumnAddProps {
    deskId: string;
}


export const ColumnAddButton = ({ deskId }: ColumnAddProps) => {
    const [openModal, setOpenModal] = useState<boolean>(false);

    return (
        <>
            <Button
                variant="soft"
                color="neutral"
                size="sm"
                startDecorator={<Add />}
                onClick={() => setOpenModal(true)}
            >
                Добавить колонку
            </Button>

            <ColumnAddModal
                open={openModal}
                setOpen={setOpenModal}
                deskId={deskId}
            />
        </>
    );
};
