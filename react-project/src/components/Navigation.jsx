import React, { useEffect, useState } from "react";
import { Navbar, Nav, Container } from "react-bootstrap";
import { BrowserRouter, Routes, Route, Link, NavLink } from "react-router-dom";
import About from "./About";
import Contact from "./Contact";
import Home from "../components/home/Home";
import Register from "../components/register/RegisterForm";
import Login from "./login/Login";
import UserHome from "./user/UserHome";
import "../components/home/Home.css";
import Footer from "./Footer";
import BookServices from "./BookServices";
import ErrorPage from "./error/ErrorPage";
import FeedbackForm from "./FeedbackForm";
import RegisterEmp from "./employee/RegisterEmp";
import AddProfession from "./user/AddProfession";
import ForgetPassword from "./ForgetPassword.jsx";
import NotAuthorized from "./NotAuthorized";
import logo from "../images/logo.png";

const Navigation = () => {
  const myuserobj = localStorage.getItem("user");
  const user = JSON.parse(myuserobj);

  const [userobj, setUserobj] = useState(false);

  useEffect(() => {
    const loggedIn = localStorage.getItem("user");
    if (loggedIn) {
      setUserobj(true);
    }
  }, []);

  const logout = () => {
    localStorage.removeItem("user");
    setUserobj(false);
  };
  return (
    <div>
      <BrowserRouter>
        <div className="App">
          <Navbar className="mynavbar">
            <Container>
              <Navbar.Brand href="/">
                <img
                  src={logo}
                  className="d-inline-block align-top logo"
                  alt="psd logo"
                />
              </Navbar.Brand>
              <Nav className="me-auto">
                <NavLink className="nav-link" to="/">
                  Home
                </NavLink>
                <NavLink className="nav-link" to="/About">
                  About Us
                </NavLink>
                <NavLink className="nav-link" to="/Contact">
                  Contact Us
                </NavLink>
                {userobj ? (
                  <Nav.Link as={Link} to="/api/psd/user/Profile">
                    Profile
                  </Nav.Link>
                ) : (
                  ""
                )}
              </Nav>
              <Nav>
                {!userobj ? (
                  <NavLink
                    className="nav-link"
                    to="/api/psd/employee"
                  >
                    Register As A Professional
                  </NavLink>
                ) : (
                  ""
                )}
                {!userobj ? (
                  <Nav.Link as={Link} to="/api/psd/register">
                    Register
                  </Nav.Link>
                ) : (
                  ""
                )}
                {!userobj ? (
                  <Nav.Link as={Link} to="/api/psd/user">
                    Login
                  </Nav.Link>
                ) : (
                  ""
                )}
                {userobj ? (
                  <Nav.Link as={Link} to="/" onClick={logout}>
                    Logout
                  </Nav.Link>
                ) : (
                  ""
                )}
              </Nav>
            </Container>
          </Navbar>
          <div>
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/about" element={<About />} />
                <Route path="/contact" element={<Contact />} />
                <Route
                  path="/api/psd/register"
                  element={<Register />}
                />
                <Route path="/api/psd/user" element={<Login />} />
                {userobj && (user.role=="ADMIN" | user.role=="CUSTOMER")?<Route
                  path="/api/psd/user/Profile"
                  element={<UserHome />}
                />:<Route
                path="/api/psd/user/Profile"
                element={<Login />}
              />}
                <Route path="/bookservice" element={<BookServices />} />
                <Route path="/feedback" element={<FeedbackForm />} />
                <Route
                  path="/api/psd/employee"
                  element={<RegisterEmp />}
                />
                <Route
                  path="/api/psd/forgetpassword"
                  element={<ForgetPassword />}
                />
                {userobj && (user.role=="ADMIN")?<Route path="addprofession" element={<AddProfession />} />:<Route path="addprofession" element={<NotAuthorized />} />}
                <Route path="*" element={<ErrorPage />} />
            </Routes>
          </div>
          <Footer />  
        </div>
      </BrowserRouter>
    </div>
  );
};

export default Navigation;
