import React, { useState, useEffect } from 'react';
import API from './util/Api.js';
import { Card } from 'react-bootstrap';
import image from '../assets/images/employee1.jpg'
import authHeader from './util/AuthHeader';

const EmployeeList = () => {
    const [employees, setEmployees] = useState([]);
 
    const fetchEmployees = () => {


        API.get('api/employees', {headers :authHeader()})
            .then(res => {
                setEmployees(res.data);
            }).catch(error => {
                console.error("Error fetching employee data: ", error);
            })
    };

    useEffect(() => {
        fetchEmployees();
    }, [])

    return (
        <>
            {employees.map((employee, index) => (
                <div style={{ padding: '1rem' }} key={index}>
                    <Card className="w-50" style={{ marginLeft: 'auto', marginRight: 'auto' }}>
                        <Card.Header>{employee.firstname} {employee.lastname}, {employee.rank}</Card.Header>
                        <Card.Img variant="top" style={{ width: '10rem', height: '12rem', padding: '1rem', display: 'inline-block' }}
                            src={image} loading='lazy' />
                        <Card.Body style={{ display: 'inline-block' }}>
                            <Card.Text>Email: {employee.email}</Card.Text>
                            <div>
                                <Card.Text>Description: {employee.description} </Card.Text>
                            </div>
                        </Card.Body>
                    </Card>
                </div>
            ))}
        </>
    )

}

export default EmployeeList
