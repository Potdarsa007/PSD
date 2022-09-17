import React from "react";
import ReactDOM from "react-dom";
import App from './App';
import 'bootstrap/dist/css/bootstrap.min.css';
import { ThemeProvider } from "react-bootstrap";

ReactDOM.render(
  <ThemeProvider
    breakpoints={['xxxl', 'xxl', 'xl', 'lg', 'md', 'sm', 'xs', 'xxs']}
  >
    <App />
  </ThemeProvider>
  , document.getElementById('root')
);