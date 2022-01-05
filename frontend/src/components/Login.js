import React from 'react'
import {Form, Button} from 'react-bootstrap';
import { useState } from 'react';

const Login = () => {
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')

    return (
        <div style={{marginTop: '7rem'}}>
            <Form className="w-50" style={{marginLeft: 'auto', marginRight: 'auto'}}>
                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>Email address</Form.Label>
                    <Form.Control type="email" placeholder="Enter email" />
                </Form.Group>
                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label>Password</Form.Label>
                    <Form.Control type="password" placeholder="Password" />
                </Form.Group>
                <Button variant="primary" type="submit">
                    Submit
                </Button>
            </Form>
        </div>
    )
}

export default Login
