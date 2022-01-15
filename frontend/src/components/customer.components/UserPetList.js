import React from 'react'
import { useState, useEffect } from 'react'
import API from '../util/Api.js'
import { Card } from 'react-bootstrap'
import image from '../../assets/images/employee1.jpg'

const UserPetList = ({ user }) => {
    const [pets, setPets] = useState([])

    useEffect(() => {
        const getPetsFromDb = () => {
            API.get('/api/pets/user', {
                params: { email: user.email }
            }).then(res => {
                setPets(res.data)
            }).catch(e => {
                console.log(e)
            })
        }

        getPetsFromDb();
    }, [user])

    return (
        <>
            {pets.map((pet, index) => (
                <div style={{ padding: '1rem' }} key={index}>
                <Card className="w-50" style={{ marginLeft: 'auto', marginRight: 'auto' }}>
                    <Card.Header>{pet.name}</Card.Header>
                    <Card.Img variant="top" style={{ width: '10rem', height: '12rem', padding: '1rem', display: 'inline-block' }}
                        src={image} loading='lazy' />
                </Card>
            </div>
            ))}
        </>
    )
}

export default UserPetList
