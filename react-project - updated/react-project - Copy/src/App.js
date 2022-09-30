import React from 'react'
import Navigation from './components/Navigation';
import "./App.css";

const App = () => {
    return(
        <>
        <Navigation/>
        <a href="https://wa.me/917354648440" className="whatsapp_float" target="_blank"> <i className="fa fa-whatsapp whatsapp-icon"></i></a>
        </>
    )
}
export default App;