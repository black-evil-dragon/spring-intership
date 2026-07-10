interface getInitialsProps {
    firstName: string
    lastName: string
}
export const getUserInitials = ({ firstName, lastName }: getInitialsProps): string => {
    return `${firstName.charAt(0) }${lastName.charAt(0) }`.toLocaleUpperCase()
}