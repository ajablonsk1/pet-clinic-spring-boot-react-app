import React from 'react'
import { useState, useEffect } from 'react'
import API from '../util/Api.js'
import { Card, Stack, Button } from 'react-bootstrap'

const UserAppointmentList = ( {user} ) => {
    const [appointments, setAppointments] = useState([])

    useEffect(() => {
        const getAppointmentsFromDb = () => {
            API.get('/api/appointments/customer', { params:{email: user.email}
        }).then(res => {
            console.log(res.data)
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

    return (
        <div>
            <div style={{ padding: '1rem' }}>
                <Card className="w-50" style={{ marginLeft: 'auto', marginRight: 'auto', textAlign: 'center' }}>
                    <Card.Body>Click <a href='/appointments/book' style={{ textDecoration: 'none'}}>here</a> to book an appointment!</Card.Body>
                </Card>
            </div>
            {appointments.length > 0 ? appointments.map((appointment, index) => (
                <div style={{ padding: '1rem' }} key={index}>
                    <Card className="w-50" style={{ marginLeft: 'auto', marginRight: 'auto' }}>
                        <Card.Header>{getDate(appointment.date)}</Card.Header>
                        <Card.Body>
                            <Stack direction="horizontal" gap={2}>
                                <Card.Text className='d-inline'>Customer: <span style={{color: '#4d4d4d'}}> {appointment.customer.firstname} {appointment.customer.lastname}</span> </Card.Text>
                                <div className="ms-auto"></div>
                                <Button value={appointment.id} onClick={onDeleteClick.bind(this)} variant="danger" className='d-inline'>Cancel</Button>
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

export default UserAppointmentList
