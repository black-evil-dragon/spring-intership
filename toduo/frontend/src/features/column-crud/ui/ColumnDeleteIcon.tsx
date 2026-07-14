import { Delete } from '@mui/icons-material';
import { IconButton } from '@mui/joy';
import { useState } from 'react';


import { ColumnDeleteModal } from './ColumnDeleteModal';

interface ColumnAddProps {
    deskId: string;
    columnId: string;
}

export const ColumnDeleteIcon = ({ columnId, deskId }: ColumnAddProps) => {
    const [openModal, setOpenModal] = useState<boolean>(false);

    return (
        <>
            <IconButton
                variant="soft"
                color="danger"
                size="sm"
                onClick={() => setOpenModal(true)}
            >
                <Delete />
            </IconButton>

            <ColumnDeleteModal
                open={openModal}
                setOpen={setOpenModal}
                columnId={columnId}
                deskId={deskId}
            />
        </>
    );
};
