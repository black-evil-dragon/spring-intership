import '@fontsource/roboto/300.css';
import '@fontsource/roboto/400.css';
import '@fontsource/roboto/500.css';
import '@fontsource/roboto/700.css';

import '@app/styles/base.scss';

import { ThemeProvider } from '@emotion/react';
import { CssVarsProvider, extendTheme } from '@mui/joy/styles';
import CssBaseline from '@mui/joy/CssBaseline';
import { Provider } from 'react-redux';

import { Routing } from './router';
import { store } from './store';
import { NotificationProvider } from '@features/notification';

const theme = extendTheme();

export const App = () => {
    return (
        <ThemeProvider theme={theme}>
            <CssVarsProvider>
                <CssBaseline />

                <Provider store={store}>
                    <NotificationProvider>
                        <Routing />
                    </NotificationProvider>
                </Provider>
            </CssVarsProvider>
        </ThemeProvider>
    );
};
