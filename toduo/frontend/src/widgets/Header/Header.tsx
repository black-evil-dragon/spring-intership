import {
    Avatar,
    Badge,
    Container,
    Divider,
    Dropdown,
    Link,
    Menu,
    MenuButton,
    MenuItem,
    Sheet,
    Stack,
    Typography,
} from '@mui/joy';


import { NavLink } from 'react-router-dom';


export const Header = () => {
    return (
        <>
            <Sheet variant="soft" color="neutral" sx={{ mb: 3 }}>
                <Container sx={{ p: 2, display: 'flex', alignItems: 'center' }}>
                    {/* Navigation */}
                    <Stack direction={'row'} gap={5}>
                        <Link component={NavLink} to={'/'} underline="none">
                            <Typography level="h3">TO/DuO</Typography>
                        </Link>

                        <Badge badgeContent={1} sx={{ px: 0.5 }}>
                            <Link>
                                <Typography>Доски</Typography>
                            </Link>
                        </Badge>
                        <Badge badgeContent={2} sx={{ px: 0.5 }}>
                            <Link>
                                <Typography>Задачи</Typography>
                            </Link>
                        </Badge>
                    </Stack>

                    {/* User actions */}
                    <Stack direction={'row'} gap={5} sx={{ ml: 'auto' }}>
                        <Dropdown>
                            <MenuButton
                                variant="plain"
                                color="neutral"
                                endDecorator={
                                    <Avatar variant="outlined">СГ</Avatar>
                                }
                            >
                                Семён Голган
                            </MenuButton>

                            <Menu variant="soft">
                                <MenuItem>Профиль</MenuItem>
                                <Divider />
                                <MenuItem color="danger">Выйти</MenuItem>
                            </Menu>
                        </Dropdown>
                    </Stack>
                </Container>
                <Divider />
            </Sheet>
        </>
    );
};
