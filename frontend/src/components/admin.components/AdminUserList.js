import React from 'react'
import API from '../util/Api.js'
import { useState, useEffect } from 'react'
import { Card } from 'react-bootstrap'

const AdminUserList = () => {
    const [users, setUsers] = useState([])

    useEffect(() => {
        const getAllUsers = () => {
            API.get('/api/users'
            ).then(res => {
                setUsers(res.data)
            }).catch(e => {
                console.log(e)
            })
        }

        getAllUsers()
    })

    return (
        <div>
            {users.map((user, index) => (
                <div style={{ padding: '1rem' }} key={index}>
                <Card className="w-50" style={{ marginLeft: 'auto', marginRight: 'auto' }}>
                    <Card.Header>{}</Card.Header>
                </Card>
            </div>
            ))}
        </div>
    )
}

export default AdminUserList
