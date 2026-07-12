import { type SyntheticEvent } from 'react';
import { Chip, FormControl, FormHelperText, FormLabel, Option, Select } from '@mui/joy';

import type { TaskStatus } from '../types';

import { STATUSES } from '../model/status';


interface TaskStatusSelectProps {
    value: TaskStatus;
    onChange: (value: TaskStatus) => void;
    error?: boolean;
    helperText?: string;
}

export const TaskStatusSelect = ({
    value,
    onChange,
    error,
    helperText,
}: TaskStatusSelectProps) => {
    const handleChange = (
        _event: SyntheticEvent | null,
        newValue: TaskStatus | null,
    ) => {
        if (newValue) onChange(newValue);
    };

    return (
        <FormControl error={error}>
            <FormLabel>Статус</FormLabel>
            <Select
                value={value}
                onChange={handleChange}
                renderValue={(selected) =>
                    selected && (
                        <Chip
                            variant={STATUSES[selected.value].variant}
                            color={STATUSES[selected.value].color}
                            size="sm"
                        >
                            {selected.label}
                        </Chip>
                    )
                }
            >
                {Object.entries(STATUSES).map(([key, value]) => (
                    <Option key={key} value={key} sx={{ py: 1 }}>
                        <Chip
                            variant={value.variant}
                            color={value.color}
                            size="sm"
                        >
                            {value.label}
                        </Chip>
                    </Option>
                ))}
            </Select>
            {helperText && <FormHelperText>{helperText}</FormHelperText>}
        </FormControl>
    );
};
