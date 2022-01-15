import React from 'react'
import { Form, Button } from 'react-bootstrap';
import { useState } from 'react';
import { Link, useNavigate  } from 'react-router-dom';
import API from './util/Api.js'

const Login = ( {userProps} ) => {
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const navigate = useNavigate ()

    const isFormValid = () => {
        if (!email) {
            alert("Please add email")
            return false
        }
        else if (!password) {
            alert("Please add pasword")
            return false
        }
        return true
    }

    const onSubmit = (e) => {
        e.preventDefault()

        if (!isFormValid) {
            return
        }
        API.post('/api/signIn', {
            email: email,
            password: password
        }).then(res => {
            if (res.data.token) {
                const user = res.data
                userProps.setUser(user)
                localStorage.setItem("user", JSON.stringify(user))
                console.log(user)
            }
        }).catch(err => {
            console.log(err)
        })
        navigate('/')
    }

    return (
        <div style={{ marginTop: '7rem' }}>
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
                <Button variant="primary" type="submit">
                    Submit
                </Button>
                <Form.Label style={{ marginLeft: '10px' }}>Don't have an account? CLick <Link to={'/register'}>here</Link>to sing up!</Form.Label>
            </Form>
        </div>
    )
}

export default Login
