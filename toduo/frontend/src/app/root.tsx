
import '@fontsource/roboto/300.css';
import '@fontsource/roboto/400.css';
import '@fontsource/roboto/500.css';
import '@fontsource/roboto/700.css';


import { Routing } from './router';
import { ThemeProvider } from '@emotion/react';
import { CssVarsProvider, extendTheme } from '@mui/joy/styles';
import CssBaseline from '@mui/joy/CssBaseline';

const theme = extendTheme();

export const App = () => {
  return (
    <ThemeProvider theme={theme}>
      <CssVarsProvider>
        <CssBaseline />
        <Routing />
      </CssVarsProvider>
    </ThemeProvider>
  );
};
