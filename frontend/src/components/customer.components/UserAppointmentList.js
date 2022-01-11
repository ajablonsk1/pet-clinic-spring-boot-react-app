import React from 'react'
import { useState, useEffect } from 'react'
import API from '../util/Api.js'
import { Card } from 'react-bootstrap'
import image from '../../assets/images/employee1.jpg'

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
    }, [])

    return (
        <>
            {appointments.map((appointment, index) => (
                <div style={{ padding: '1rem' }} key={index}>
                <Card className="w-50" style={{ marginLeft: 'auto', marginRight: 'auto' }}>
                    <Card.Header>{}</Card.Header>
                    <Card.Img variant="top" style={{ width: '10rem', height: '12rem', padding: '1rem', display: 'inline-block' }}
                        src={image} loading='lazy' />
                </Card>
            </div>
            ))}
        </>
    )
}

export default UserAppointmentList
