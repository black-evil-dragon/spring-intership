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