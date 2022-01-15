import React from 'react'
import { Form, Button } from 'react-bootstrap'
import { useState, useEffect } from 'react'
import API from '../util/Api.js'
import "react-datepicker/dist/react-datepicker.css";
import DatePicker from "react-datepicker";

const BookAppointment = ({ user }) => {
    const [pets, setPets] = useState([])
    const [employees, setEmployees] = useState([])
    const [date, setDate] = useState(new Date())
    const [pet, setPet] = useState('{}')
    const [employee, setEmployee] = useState('')
    const [hour, setHour] = useState('')
    const [dates, setDates] = useState([])
    const [hours, setHours] = useState(['10:00', '10:30', '11:00', '11:30', '12:00', '12:30',
        '13:00', '13:30', '14:00', '14:30', '15:00', '15:30', '16:00', '16:30', '17:00', '17:30'])

    useEffect(() => {
        const getAllUserPets = () => {
            API.get('/api/pets/user', {
                params: { email: user.email }
            }).then(res => {
                setPets(res.data)
            }).catch(e => {
                console.log(e)
            })
        }

        const getAllEmployees = () => {
            API.get('/api/employees'
            ).then(res => {
                setEmployees(res.data)
            }).catch(e => {
                console.log(e)
            })
        }

        getAllUserPets()
        getAllEmployees()

    }, [user])


    useEffect(() => {
        const getDates = () => {
            API.get('/api/appointments/dates', {
                params: { currDate: date }
            }).then(res => {
                setDates(res.data.map(data => new Date(data)))
                setDates(dates => dates.map((date) => {
                    if(date.getMinutes() < 10){
                        return date.getHours() + ':0' + date.getMinutes()
                    } else {
                        return date.getHours() + ':' + date.getMinutes()
                    }
                }))
            }).catch(e => {
                console.log(e)
            })
        }

        getDates()
    }, [date])

    useEffect(() => {
        setHours(hours => hours.filter((hour) => {
            if(dates.includes(hour)){
                return false
            }
            return true
        }))
    },[date, dates])


    const onSubmit = (e) => {
        e.preventDefault()
        if (hour) {
            const h = hour.slice(0,2)
            const m = hour.slice(3)
            date.setHours(parseInt(h), parseInt(m), 0, 0)
        }

        API.post('/api/appointments/add', {
            customerEmail: user.email,
            employeeEmail: employee,
            petName: pet,
            appointment: {
                date: date
            }
        }).then(res => {
            console.log(res.data)
        }).catch(e => {
            console.log(e)
        })

        setEmployee('')
        setPet('')
        setDate(new Date())
        setHour('')
    }

    return (
        <div>
            <div style={{ marginTop: '6rem' }}>
                <Form
                    className="w-50"
                    style={{ marginLeft: 'auto', marginRight: 'auto' }}
                    onSubmit={onSubmit}
                    id='form'
                >
                    <Form.Group className="mb-3" controlId="formBasicPassword">
                        <Form.Label>Select pet</Form.Label>
                        <Form.Select
                            value={pet}
                            onChange={(e) => setPet(e.target.value)}
                        >
                            <option></option>
                            {pets.map((pet, index) => (
                                <option value={pet.name} key={index}>{pet.name}</option>
                            ))}
                        </Form.Select>
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="formBasicPassword">
                        <Form.Label>Select doctor</Form.Label>
                        <Form.Select
                            value={employee}
                            onChange={(e) => setEmployee(e.target.value)}
                        >
                            <option></option>
                            {employees.map((employee, index) => (
                                <option value={employee.email} key={index}>{employee.firstname}, {employee.rank}</option>
                            ))}
                        </Form.Select>
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="formBasicPassword">
                        <Form.Label>Select date</Form.Label>
                        <DatePicker selected={date} onChange={(date) => setDate(date)} />
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="formBasicPassword">
                        <Form.Label>Select hour</Form.Label>
                        <Form.Select
                            value={hour}
                            onChange={(e) => setHour(e.target.value)}
                        >
                            <option></option>
                            {hours.map((hour, index) => (
                                <option key={index}>{hour}</option>
                            ))}
                        </Form.Select>
                    </Form.Group>
                    <Button variant="primary" type="submit">
                        Submit
                    </Button>
                </Form>
            </div>
        </div>
    )
}

export default BookAppointment
