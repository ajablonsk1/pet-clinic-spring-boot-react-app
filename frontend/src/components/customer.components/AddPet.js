import React from 'react'
import { Form, Button } from 'react-bootstrap'
import { useState } from 'react'
import DatePicker from "react-datepicker";
import API from '../util/Api.js'
import "react-datepicker/dist/react-datepicker.css";

const AddPet = ({ user }) => {
    const [name, setName] = useState('')
    const [type, setType] = useState('')
    const [gender, setGender] = useState('Male')
    const [date, setDate] = useState(new Date())

    const savePet = () => {
        date.setHours(0, 0, 0)
        API.post('/api/pets/save/owner', {
            email: user.email,
            pet: {
                name: name,
                type: type,
                gender: gender, 
                birthDay: date
            }
        }).then(res => {
            console.log(res.data)
        }).catch(e => {
            console.log(e)
        })
    }

    const onSubmit = (e) => {
        e.preventDefault()
        savePet()
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
                    <Form.Group className="mb-3" controlId="formBasicEmail">
                        <Form.Label>Name</Form.Label>
                        <Form.Control
                            type="text"
                            placeholder="Name"
                            value={name}
                            onChange={(e) => setName(e.target.value)}
                        />
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="formBasicPassword">
                        <Form.Label>Type (Dog, Cat, etc.)</Form.Label>
                        <Form.Control
                            type="text"
                            placeholder="Name"
                            value={type}
                            onChange={(e) => setType(e.target.value)}
                        />
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="formBasicPassword">
                        <Form.Label>Date of birth</Form.Label>
                        <DatePicker selected={date} onChange={(date) => setDate(date)} />
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="formBasicPassword">
                        <Form.Label>Gender</Form.Label>
                        <Form.Select
                            value={gender}
                            onChange={(e) => setGender(e.target.value)}
                        >
                            <option>Male</option>
                            <option>Female</option>
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

export default AddPet
