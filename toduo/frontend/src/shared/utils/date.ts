export const formatToInstant = (localDateTime: string | undefined | null): string | null => {
    if (!localDateTime) return null;

    return localDateTime.includes('Z') ? localDateTime : `${localDateTime}:00Z`;
};
export const formatDisplayDate = (isoString: string | undefined | null): string => {
    if (!isoString) return '';

    const date = new Date(isoString);

    return new Intl.DateTimeFormat('ru-RU', {
        day: 'numeric',
        month: 'long',
        year: 'numeric',
        hour: '2-digit',
        minute: '2-digit',
    }).format(date);
};

export const formatToDatetimeLocal = (isoString: string | undefined | null): string => {
    if (!isoString) return '';
    const date = new Date(isoString);
    if (isNaN(date.getTime())) return '';

    const timezoneOffset = date.getTimezoneOffset() * 60000;
    const localISOTime = new Date(date.getTime() - timezoneOffset).toISOString().slice(0, 16);

    return localISOTime;
};