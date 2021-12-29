import React from 'react'
import { Navbar, Container, Nav } from 'react-bootstrap'
import { MdPets } from "react-icons/md";

const Header = (props) => {
    return (
        <Navbar bg="light" expand="lg">
            <Container>
                <Navbar.Brand><MdPets class="mb-1"/>{props.title}</Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="me-auto">
                        <Nav.Link href="#home">{props.home}</Nav.Link>
                        <Nav.Link href="#link">{props.empoyees}</Nav.Link>
                        <Nav.Link href="#link">{props.about}</Nav.Link>
                    </Nav>
                </Navbar.Collapse>
                <Navbar.Collapse className="justify-content-end">
                    <Nav className="me">
                        <Nav.Link href="#link">{props.login}</Nav.Link>
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    )
}

Header.defaultProps = {
    title: 'Pet Clinic',
    home: 'Home',
    empoyees: 'Employees',
    about: 'About us',
    appointment: 'Your appointments',
    login: 'Login',
    logout: 'Logout'
}

export default Header
