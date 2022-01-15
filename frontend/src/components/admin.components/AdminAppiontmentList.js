import React from 'react'
import { useState, useEffect } from 'react'
import API from '../util/Api.js'
import { Card } from 'react-bootstrap'

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


    return (
        <div>
            {appointments.map((appointment, index) => (
                <div style={{ padding: '1rem' }} key={index}>
                <Card className="w-50" style={{ marginLeft: 'auto', marginRight: 'auto' }}>
                    <Card.Header>{}</Card.Header>
                </Card>
            </div>
            ))}
        </div>
    )
}

export default AdminAppiontmentList
