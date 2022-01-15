import React from 'react'
import { Form, Button } from 'react-bootstrap'
import { useState } from 'react'
import { Link } from 'react-router-dom'
import API from './util/Api.js'


const Register = () => {
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [confitmPassword, setConfirmPassword] = useState('')

    const isFormValid = () => {
        if (!email) {
            alert("Please add email!")
            return false;
        }
        else if (!password) {
            alert("Please add password!")
            return false;
        }
        else if (!confitmPassword) {
            alert("Please confirm password!")
            return false;
        }
        return true;
    }

    const onSubmit = (e) => {
        e.preventDefault();

        if (!isFormValid()) {
            return
        }
        API.post('/api/register', {
            email: email,
            password: password
        }).then(res => {
            console.log(res)
            alert(res.data.message)
        }).catch(error => {
            console.log(error)
        })
    }

    return (
        <div style={{ marginTop: '6rem' }}>
            <Form
                className="w-50"
                style={{ marginLeft: 'auto', marginRight: 'auto' }}
                onSubmit={onSubmit}
            >
                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>Email address</Form.Label>
                    <Form.Control
                        type="email"
                        placeholder="Enter email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                    />
                </Form.Group>
                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label>Password</Form.Label>
                    <Form.Control
                        type="password"
                        placeholder="Password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                </Form.Group>
                <Form.Group className="mb-3" controlId="formBasicConfirmPassword">
                    <Form.Label>Confirm password</Form.Label>
                    <Form.Control
                        type="password"
                        placeholder="Password"
                        value={confitmPassword}
                        onChange={(e) => setConfirmPassword(e.target.value)}
                    />
                </Form.Group>
                <Button variant="primary" type="submit">
                    Submit
                </Button>
                <Form.Label style={{ marginLeft: '10px' }}>Have an account? CLick <Link to={'/login'}>here</Link> to sign in!</Form.Label>
            </Form>
        </div>
    )
}

export default Register
