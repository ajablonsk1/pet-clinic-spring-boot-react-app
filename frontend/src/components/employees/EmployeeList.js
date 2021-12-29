import React, { useState, useEffect } from 'react';
import API from '../api';

const EmployeeList = () => {
    const [employees, setEmployees] = useState([]);

    const fetchEmployees = () => {
        API.get('/employees')
            .then(res => {
                setEmployees(res.data);
            }).catch(error => {
                console.error("Error fetching employee data: ", error);
            }) 
    };

    useEffect(() => {
        fetchEmployees();
    }, [])

    return employees.map((employee, index) => {
        return(
            <div key={index}>
                <p>{employee.firstname}</p>
            </div>
        )
    })
}

export default EmployeeList
