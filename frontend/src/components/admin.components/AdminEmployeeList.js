import React from 'react'
import API from '../util/Api.js'
import { useState, useEffect } from 'react'
import { Card } from 'react-bootstrap'

const AdminEmployeeList = () => {
    const [employees, setEmployees] = useState([])

    useEffect(() => {
        const getAllEmployees = () => {
            API.get('/api/customers'
            ).then(res => {
                setEmployees(res.data)
            }).catch(e => {
                console.log(e)
            })
        }

        getAllEmployees()
    })

    return (
        <div>
            {employees.map((employee, index) => (
                <div style={{ padding: '1rem' }} key={index}>
                <Card className="w-50" style={{ marginLeft: 'auto', marginRight: 'auto' }}>
                    <Card.Header>{}</Card.Header>
                </Card>
            </div>
            ))}
        </div>
    )
}

export default AdminEmployeeList
