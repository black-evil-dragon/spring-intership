import { Add } from '@mui/icons-material';
import { IconButton } from '@mui/joy';
import { useState } from 'react';


import { ColumnAddModal } from './ColumnAddModal';

interface ColumnAddProps {
    deskId: string;
    newPosition?: number;
}

export const ColumnAddIcon = ({ newPosition, deskId }: ColumnAddProps) => {
    const [openModal, setOpenModal] = useState<boolean>(false);

    return (
        <>
            <IconButton
                variant="soft"
                size="sm"
                onClick={() => setOpenModal(true)}
            >
                <Add />
            </IconButton>

            <ColumnAddModal
                open={openModal}
                setOpen={setOpenModal}
                newPosition={newPosition}
                deskId={deskId}
            />
        </>
    );
};
