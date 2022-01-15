import React from 'react'
import API from '../util/Api.js'
import { useState, useEffect } from 'react'
import { Card } from 'react-bootstrap'

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

    return (
        <div>
            {customers.map((customer, index) => (
                <div style={{ padding: '1rem' }} key={index}>
                <Card className="w-50" style={{ marginLeft: 'auto', marginRight: 'auto' }}>
                    <Card.Header>{}</Card.Header>
                </Card>
            </div>
            ))}
        </div>
    )
}

export default AdminCustomerList
