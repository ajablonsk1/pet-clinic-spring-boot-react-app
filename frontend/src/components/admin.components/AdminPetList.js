import React from 'react'
import API from '../util/Api.js'
import { useState, useEffect } from 'react'
import { Card } from 'react-bootstrap'
const AdminPetList = () => {
    const [pets, setPets] = useState([])

    useEffect(() => {
        const getAllPets = () => {
            API.get('/api/pets'
            ).then(res => {
                setPets(res.data)
            }).catch(e => {
                console.log(e)
            })
        }

        getAllPets()
    })

    return (
        <div>
            {pets.map((pet, index) => (
                <div style={{ padding: '1rem' }} key={index}>
                <Card className="w-50" style={{ marginLeft: 'auto', marginRight: 'auto' }}>
                    <Card.Header>{}</Card.Header>
                </Card>
            </div>
            ))}
        </div>
    )
}

export default AdminPetList
