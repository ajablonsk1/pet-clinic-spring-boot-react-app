import React from 'react'
import API from '../util/Api.js'
import { useState, useEffect } from 'react'
import { Card, Button, Stack } from 'react-bootstrap'

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
    }, [])

    const onDeleteClick = (e) => {
        API.delete('/api/pet', {params: {id: e.target.value}}
            ).then(res => {
                console.log(res.data)
            }).catch(e => {
                console.log(e)
            })
            // eslint-disable-next-line eqeqeq
            setPets(pets => pets.filter(pet => pet.id != e.target.value))
    }

    const getDate = (dateInMilis) => {
        var date = new Date(dateInMilis)
        return date.toUTCString()
    }

    return (
        <div>
            {pets.length > 0 ? pets.map((pet, index) => (
                <div style={{ padding: '1rem' }} key={index}>
                    <Card className="w-50" style={{ marginLeft: 'auto', marginRight: 'auto' }}>
                        <Card.Header>{pet.name}</Card.Header>
                        <Card.Body>
                            <Stack direction="horizontal" gap={2}>
                                <Card.Text className='d-inline'>Type: <span style={{color: '#4d4d4d'}}> {pet.type}</span> </Card.Text>
                                <div className="ms-auto"></div>
                                <Button value={pet.id} onClick={onDeleteClick.bind(this)} variant="danger" className='d-inline'>Delete</Button>
                            </Stack>
                            <Card.Text>Gender:<span style={{color: '#4d4d4d'}}> {pet.gender}</span></Card.Text>
                            <Card.Text>Owner:<span style={{color: '#4d4d4d'}}> {pet.owner.firstname} {pet.owner.lastname}</span></Card.Text>
                            <Card.Text>Date of birth:<span style={{color: '#4d4d4d'}}> {getDate(pet.birthDay)}</span></Card.Text>
                        </Card.Body>
                    </Card>
                </div>
            )) : 
            <div style={{ padding: '1rem' }}>
                <Card className="w-50" style={{ marginLeft: 'auto', marginRight: 'auto', textAlign: 'center' }}>
                    <Card.Body>No pets has been found!</Card.Body>
                </Card>
            </div>}
        </div>
    )
}

export default AdminPetList
