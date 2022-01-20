import React from 'react'
import API from '../util/Api.js'
import { useState, useEffect } from 'react'
import { Card, Button, Stack } from 'react-bootstrap'

const AdminEmployeeList = () => {
    const [employees, setEmployees] = useState([])

    useEffect(() => {
        const getAllEmployees = () => {
            API.get('/api/employees'
            ).then(res => {
                setEmployees(res.data)
            }).catch(e => {
                console.log(e)
            })
        }

        getAllEmployees()
    }, [])

    const onDeleteClick = (e) => {
        API.delete('/api/employee', {params: {email: e.target.value}}
            ).then(res => {
                console.log(res)
            }).catch(e => {
                console.log(e)
            })
            setEmployees(employees => employees.filter(user => user.email !== e.target.value))
    }

    return (
        <div>
            {employees.length > 0 ? 
            employees.map((employee, index) => (
                <div style={{ padding: '1rem' }} key={index}>
                    <Card className="w-50" style={{ marginLeft: 'auto', marginRight: 'auto' }}>
                        <Card.Header>{employee.firstname} {employee.lastname}, {employee.rank}</Card.Header>
                        <Card.Body>
                            <Stack direction="horizontal" gap={2}>
                                <Card.Text>Email: <span style={{color: '#4d4d4d'}}>{employee.email}</span></Card.Text>
                                <div className="ms-auto"></div>
                                <Button value={employee.email} onClick={onDeleteClick.bind(this)} variant="danger" className='d-inline'>Delete</Button>
                            </Stack>
                            <Card.Text className='w-75'>Description: <span style={{color: '#4d4d4d'}}>{employee.description}</span></Card.Text>
                        </Card.Body>
                    </Card>
                </div>
            )) : 
            <div style={{ padding: '1rem' }}>
                <Card className="w-50" style={{ marginLeft: 'auto', marginRight: 'auto', textAlign: 'center' }}>
                    <Card.Body>No employees has been found!</Card.Body>
                </Card>
            </div>}
        </div>
    )
}

export default AdminEmployeeList
