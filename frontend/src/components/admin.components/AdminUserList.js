import React from 'react'
import API from '../util/Api.js'
import { useState, useEffect } from 'react'
import { Card, Button, Stack } from 'react-bootstrap'

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

    }, [])

    const onDeleteClick = (e) => {
        API.delete('/api/user', {params: {email: e.target.value}}
            ).then(res => {
                console.log(res)
            }).catch(e => {
                console.log(e)
            })
        setUsers(users => users.filter(user => user.email !== e.target.value))
    }

    return (
        <div>
            {users.length > 0 ? users.map((user, index) => (
                <div style={{ padding: '1rem' }} key={index}>
                    <Card className="w-50" style={{ marginLeft: 'auto', marginRight: 'auto' }}>
                        <Card.Header>{user.email}</Card.Header>
                        <Card.Body>
                            <Stack direction="horizontal" gap={2}>
                                <Card.Text className='d-inline'>{'Roles: '} </Card.Text>
                                {user.roles.map((role, index) => (
                                    <Card.Text className='d-inline' key={index} style={{color: '#4d4d4d'}}>{role.name};</Card.Text>
                                ))}
                                <div className="ms-auto"></div>
                                <Button value={user.email} onClick={onDeleteClick.bind(this)} variant="danger" className='d-inline'>Delete</Button>
                            </Stack>
                        </Card.Body>
                    </Card>
                </div>
            )) : 
            <div style={{ padding: '1rem' }}>
                <Card className="w-50" style={{ marginLeft: 'auto', marginRight: 'auto', textAlign: 'center' }}>
                    <Card.Body>No users has been found!</Card.Body>
                </Card>
            </div>}
        </div>
    )
}

export default AdminUserList
