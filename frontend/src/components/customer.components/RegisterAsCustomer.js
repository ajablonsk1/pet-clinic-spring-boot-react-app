import React from 'react'
import { Form, Button } from 'react-bootstrap'
import { useState, useEffect } from 'react'
import API from '../util/Api.js'

const RegisterAsCustomer = ( user ) => {
    const [firstname, setFirstname] = useState('')
    const [lastname, setLastname] = useState('')
    const [phoneNumber, setphoneNumber] = useState('')
    const [thisUser, setThisUser] = useState('')

    const isFormValid = () => {
        if (!firstname) {
            alert("Please add firstname!")
            return false;
        }
        else if (!lastname) {
            alert("Please add lastname!")
            return false;
        }
        else if (!phoneNumber) {
            alert("Please add phoneNumber!")
            return false;
        }
        return true;
    }

    useEffect(() => {
        const getUserFromDb = () => {
            API.get('/api/user',
             {params: {email: user.email}
            }).then(res => {
                setThisUser(res.data)
            }).catch(e => {
                console.log(e)
            })
        }

        getUserFromDb();
    }, [])

    const onSubmit = (e) => {
        e.preventDefault();
        if (!isFormValid()) {
            return
        }
        API.post('/api/customer/save', {
            firstname: firstname,
            lastname: lastname,
            phoneNumber: phoneNumber,
            email: thisUser.email,
            appointments: null,
            pets: null,
            appUser: thisUser 
        }).then(res => {
            console.log(res)
            alert(res.data.message)
        }).catch(error => {
            console.log(error)
        })
    }

    return (
        <div>
            <div style={{ marginTop: '6rem' }}>
                <Form
                    className="w-50"
                    style={{ marginLeft: 'auto', marginRight: 'auto' }}
                    onSubmit={onSubmit}
                >
                    <Form.Group className="mb-3" controlId="formBasicEmail">
                        <Form.Label>Firstname</Form.Label>
                        <Form.Control
                            type="firstname"
                            placeholder="Firstname"
                            value={firstname}
                            onChange={(e) => setFirstname(e.target.value)}
                        />
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="formBasicPassword">
                        <Form.Label>Lastname</Form.Label>
                        <Form.Control
                            type="lastname"
                            placeholder="Lastname"
                            value={lastname}
                            onChange={(e) => setLastname(e.target.value)}
                        />
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="formBasicPassword">
                        <Form.Label>Phone number</Form.Label>
                        <Form.Control
                            type="phoneNumber"
                            placeholder="Phone number"
                            value={phoneNumber}
                            onChange={(e) => setphoneNumber(e.target.value)}
                        />
                    </Form.Group>
                    <Button variant="primary" type="submit">
                        Submit
                    </Button>
                </Form>
            </div>
        </div>
    )
}

export default RegisterAsCustomer
