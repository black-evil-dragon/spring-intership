import '@fontsource/roboto/300.css';
import '@fontsource/roboto/400.css';
import '@fontsource/roboto/500.css';
import '@fontsource/roboto/700.css';


import { ThemeProvider } from '@emotion/react';
import { CssVarsProvider, extendTheme } from '@mui/joy/styles';
import CssBaseline from '@mui/joy/CssBaseline';
import { Provider } from 'react-redux';


import { Routing } from './router';
import { store } from './store';



const theme = extendTheme();

export const App = () => {
    return (
        <Provider store={store}>
            <ThemeProvider theme={theme}>
                <CssVarsProvider>
                    <CssBaseline />
                    <Routing />
                </CssVarsProvider>
            </ThemeProvider>
        </Provider>
    );
};
