import { createContext } from "react";
import type { NotificationColor } from "../types";


interface NotificationContextType {
    showError: (message: string) => void;
    showSuccess: (message: string) => void;
    showNotification: (message: string, color: NotificationColor) => void;
}

export const NotificationContext = createContext<NotificationContextType | undefined>(
    undefined,
);