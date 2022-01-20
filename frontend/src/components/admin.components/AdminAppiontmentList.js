import React from 'react'
import { useState, useEffect } from 'react'
import API from '../util/Api.js'
import { Card, Button, Stack } from 'react-bootstrap'

const AdminAppiontmentList = () => {
    const [appointments, setAppointments] = useState([])

    useEffect(() => {
        const getAllAppointments = () => {
            API.get('/api/appointments'
            ).then(res => {
                setAppointments(res.data)
            }).catch(e => {
                console.log(e)
            })
        }

        getAllAppointments()
    }, [])

    const onDeleteClick = (e) => {
        API.delete('/api/appointment', {params: {id: e.target.value}}
            ).then(res => {
                console.log(res.data)
            }).catch(e => {
                console.log(e)
            })
        // eslint-disable-next-line eqeqeq
        setAppointments(appointments => appointments.filter(appointment => appointment.id != e.target.value))
    }

    const getDate = (dateInMilis) => {
        var date = new Date(dateInMilis)
        return date.toUTCString()
    }

    return (
        <div>
            {appointments.length > 0 ? appointments.map((appointment, index) => (
                <div style={{ padding: '1rem' }} key={index}>
                    <Card className="w-50" style={{ marginLeft: 'auto', marginRight: 'auto' }}>
                        <Card.Header>{getDate(appointment.date)}</Card.Header>
                        <Card.Body>
                            <Stack direction="horizontal" gap={2}>
                                <Card.Text className='d-inline'>Customer: <span style={{color: '#4d4d4d'}}> {appointment.customer.firstname} {appointment.customer.lastname}</span> </Card.Text>
                                <div className="ms-auto"></div>
                                <Button value={appointment.id} onClick={onDeleteClick.bind(this)} variant="danger" className='d-inline'>Delete</Button>
                            </Stack>
                            <Card.Text>Pet:<span style={{color: '#4d4d4d'}}> {appointment.pet.name}</span></Card.Text>
                            <Card.Text>Employee:<span style={{color: '#4d4d4d'}}> {appointment.employee.firstname} {appointment.employee.lastname}</span></Card.Text>
                        </Card.Body>
                    </Card>
                </div>
            )) : 
            <div style={{ padding: '1rem' }}>
                <Card className="w-50" style={{ marginLeft: 'auto', marginRight: 'auto', textAlign: 'center' }}>
                    <Card.Body>No appointments has been found!</Card.Body>
                </Card>
            </div>}
        </div>
    )
}

export default AdminAppiontmentList
