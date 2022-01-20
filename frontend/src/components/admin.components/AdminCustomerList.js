import React from 'react'
import API from '../util/Api.js'
import { useState, useEffect } from 'react'
import { Card, Button, Stack } from 'react-bootstrap'

const AdminCustomerList = () => {
    const [customers, setCustomers] = useState([])

    useEffect(() => {
        const getAllCustomers = () => {
            API.get('/api/customers'
            ).then(res => {
                setCustomers(res.data)
            }).catch(e => {
                console.log(e)
            })
        }

        getAllCustomers()
    }, [])

    const onDeleteClick = (e) => {
        API.delete('/api/customer', {params: {email: e.target.value}}
            ).then(res => {
                console.log(res)
            }).catch(e => {
                console.log(e)
            })
            setCustomers(customers => customers.filter(user => user.email !== e.target.value))
    }

    return (
        <div>
            {customers.length ? customers.map((customer, index) => (
                <div style={{ padding: '1rem' }} key={index}>
                    <Card className="w-50" style={{ marginLeft: 'auto', marginRight: 'auto' }}>
                        <Card.Header>{customer.email}</Card.Header>
                        <Card.Body>
                            <Stack direction="horizontal" gap={2}>
                                <Card.Text>Firstname: <span style={{color: '#4d4d4d'}}>{customer.firstname}</span></Card.Text>
                                <div className="ms-auto"></div>
                                <Button value={customer.email} onClick={onDeleteClick.bind(this)} variant="danger" className='d-inline'>Delete</Button>
                            </Stack>
                            <Card.Text>Lastname: <span style={{color: '#4d4d4d'}}>{customer.lastname}</span></Card.Text>
                            <Card.Text>Phone number: <span style={{color: '#4d4d4d'}}>{customer.phoneNumber}</span></Card.Text>
                        </Card.Body>
                    </Card>
                </div>
            )) : 
            <div style={{ padding: '1rem' }}>
                <Card className="w-50" style={{ marginLeft: 'auto', marginRight: 'auto', textAlign: 'center' }}>
                    <Card.Body>No customers has been found!</Card.Body>
                </Card>
            </div>}
        </div>
    )
}

export default AdminCustomerList
