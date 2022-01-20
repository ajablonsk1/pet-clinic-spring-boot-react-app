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
        <div>
            {employees.length > 0 ? 
            employees.map((employee, index) => (
                <div style={{ padding: '1rem' }} key={index}>
                    <Card className="w-50" style={{ marginLeft: 'auto', marginRight: 'auto' }}>
                        <Card.Header>{employee.firstname} {employee.lastname}, {employee.rank}</Card.Header>
                        <Card.Body>  
                        <div className='container'>
                            <div className="row">
                                <div className="col-9">
                                    <Card.Text>Email: {employee.email}</Card.Text>
                                    <Card.Text>{employee.description}</Card.Text>
                                </div>
                                <div className="col-3">
                                    <Card.Img variant="top" style={{ width: '10rem', height: '12rem', padding: '1rem', display: 'inline-block' }}
                                    src={image} className='d-inline' loading='lazy' />
                                </div>
                            </div>
                        </div>
                        </Card.Body>
                    </Card>
                </div>
            )) : 
            <div style={{ padding: '1rem' }}>
                <Card className="w-50" style={{ marginLeft: 'auto', marginRight: 'auto', textAlign: 'center' }}>
                    <Card.Body>No employees has been found!</Card.Body>
                </Card>
            </div>}
        </div>
    )

}

export default EmployeeList
