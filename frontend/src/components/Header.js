import React from 'react'
import { Navbar, Container, Nav, NavDropdown } from 'react-bootstrap'
import { MdPets } from "react-icons/md";

const Header = (props) => {

    return (
        <Navbar fixed="top" bg="light" expand="lg">
            <Container>
                <Navbar.Brand><MdPets className="mb-1" />{props.title}</Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="me-auto">
                        <Nav.Link href="/">{props.home}</Nav.Link>
                        <Nav.Link href="/employees">{props.empoyees}</Nav.Link>
                        <Nav.Link href="/about">{props.about}</Nav.Link>
                        {props.userProps.isCustomer ?
                            <NavDropdown title="Customer" id="basic-nav-dropdown">
                                <NavDropdown.Item href="/pets/user">Your pets</NavDropdown.Item>
                                <NavDropdown.Item href="/appointments/customer">Your Appointments</NavDropdown.Item>
                                <NavDropdown.Item href="/register/customer">Register as a customer</NavDropdown.Item>
                            </NavDropdown> : ''}
                        {props.userProps.isEmployee ?
                            <NavDropdown title="Employee" id="basic-nav-dropdown">
                                <NavDropdown.Item href="/appointments/employee">Your Appointments</NavDropdown.Item>
                            </NavDropdown> : ''}
                        {props.userProps.isAdmin ? <NavDropdown title="Admin" id="basic-nav-dropdown">
                            <NavDropdown.Item href="/users/admin">Users</NavDropdown.Item>
                            <NavDropdown.Item href="/customers/admin">Customers</NavDropdown.Item>
                            <NavDropdown.Item href="/employees/admin">Employees</NavDropdown.Item>
                            <NavDropdown.Item href="/pets/admin">Pets</NavDropdown.Item>
                            <NavDropdown.Item href="/appointments/admin">Appointments</NavDropdown.Item>
                        </NavDropdown> : ''}
                    </Nav>
                </Navbar.Collapse>
                <Navbar.Collapse className="justify-content-end">
                    <Nav className="me">
                        {props.isAuth ? <Nav.Link href="/logout">{props.logout}</Nav.Link>
                            : <Nav.Link href="/login">{props.login}</Nav.Link>}
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
