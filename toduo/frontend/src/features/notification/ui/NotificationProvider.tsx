import { CheckCircleOutlineOutlined, InfoOutlined } from '@mui/icons-material';
import { Snackbar } from '@mui/joy';
import { useState, type ReactNode } from 'react';


import { NotificationContext } from '../model/NotificationContext';
import type { NotificationColor } from '../types';




export const NotificationProvider = ({ children }: { children: ReactNode }) => {
    const [open, setOpen] = useState(false);
    const [message, setMessage] = useState('');
    const [color, setColor] = useState<NotificationColor>('neutral');

    const showNotification = (msg: string, col: NotificationColor) => {
        setMessage(msg);
        setColor(col);
        setOpen(true);
    };

    const showError = (msg: string) => showNotification(msg, 'danger');
    const showSuccess = (msg: string) => showNotification(msg, 'success');

    const handleClose = () => setOpen(false);

    const getIcon = () => {
        if (color === 'danger') return <InfoOutlined />;
        if (color === 'success') return <CheckCircleOutlineOutlined />;
        return null;
    };

    return (
        <NotificationContext.Provider
            value={{ showError, showSuccess, showNotification }}
        >
            {children}
            <Snackbar
                autoHideDuration={4000}
                open={open}
                variant="solid"
                color={color}
                startDecorator={getIcon()}
                onClose={handleClose}
                anchorOrigin={{ vertical: 'bottom', horizontal: 'right' }}
            >
                {message}
            </Snackbar>
        </NotificationContext.Provider>
    );
};