import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

import path from "path";

const DEBUG = process.env.NODE_ENV == 'development';

// https://vite.dev/config/
export default defineConfig({
  server: {
    host: '0.0.0.0',
    port: 3000,
    hmr: {
      clientPort: 3000
    },
    watch: {
      usePolling: !!DEBUG,
      interval: 1000
    }
  },

  plugins: [react()],


  resolve: {
    alias: {
      "@app": path.resolve(__dirname, "./src/app"),
      "@shared": path.resolve(__dirname, "./src/shared"),
      "@entities": path.resolve(__dirname, "./src/entities"),
      "@features": path.resolve(__dirname, "./src/features"),
      "@widgets": path.resolve(__dirname, "./src/widgets"),
      "@pages": path.resolve(__dirname, "./src/pages"),

      "@styles": path.resolve(__dirname, "./src/app/styles"),
    },
    extensions: ['.ts', '.tsx', '.js', '.jsx', '.json', '.scss', '.css']
  },
})
