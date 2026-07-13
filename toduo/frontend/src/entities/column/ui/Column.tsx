import { useRef, useState, type ReactNode } from 'react';

import { Card, Divider, Input, Stack } from '@mui/joy';
import { Typography } from '@mui/joy';

import type { ColumnType } from '..';

interface ColumnProps extends ColumnType {
    onRename?: (newName: string) => void;

    children?: ReactNode;
    actions?: ReactNode;
}

export const Column = ({
    name,
    children,
    actions,
    onRename,
}: ColumnProps) => {
    const [isEditing, setIsEditing] = useState(false);
    const [newName, setNewName] = useState(name);
    const inputRef = useRef<HTMLInputElement>(null);

    const handleSave = () => {
        setIsEditing?.(false);
        if (newName.trim() && newName !== name) {
            onRename?.(newName.trim());
            console.log(newName.trim());
        } else {
            setNewName(name);
        }
    };

    const handleKeyDown = (e: React.KeyboardEvent) => {
        if (e.key === 'Enter') handleSave();
        if (e.key === 'Escape') {
            setIsEditing?.(false);
            setNewName(name);
        }
    };

    return (
        <Card
            sx={{
                width: 300,
                height: '100%',
                flexShrink: 0,
            }}
        >
            <Stack direction={'row'} justifyContent={'space-between'}>
                {isEditing ? (
                    <Input
                        slotProps={{ input: { ref: inputRef } }}
                        value={newName}
                        onChange={(e) => setNewName(e.target.value)}
                        onBlur={handleSave}
                        onKeyDown={handleKeyDown}
                        variant="outlined"
                        size="sm"
                        sx={{ flexGrow: 1, variant: 'plain' }}
                    />
                ) : (
                    <Typography
                        level="h3"
                        onClick={() => setIsEditing?.(true)}
                        sx={{
                            cursor: 'pointer',
                            flexGrow: 1,
                            '&:hover': { opacity: 0.8 },
                        }}
                    >
                        {name}
                    </Typography>
                )}
                {actions}
            </Stack>
            <Divider />
            {children}
        </Card>
    );
};
