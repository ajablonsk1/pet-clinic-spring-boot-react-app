import React from 'react'
import { useState, useEffect } from 'react'
import API from '../util/Api.js'
import { Card, Stack } from 'react-bootstrap'

const EmloyeeAppointmentList = ( {user} ) => {
    const [appointments, setAppointments] = useState([])

    useEffect(() => {
        const getAppointmentsFromDb = () => {
            API.get('/api/appointments/employee' , {params: {email: user.email}
        }).then(res => {
            setAppointments(res.data)
        }).catch(e => {
            console.log(e)
        })
        }

        getAppointmentsFromDb()
    }, [user])

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

export default EmloyeeAppointmentList
