import React from 'react'
import { useEffect } from 'react'
import Home from './Home.js'

const Logout = ( {userProps} ) => {

    useEffect(() => {
        userProps.setUser(localStorage.removeItem('user'))
        userProps.setIsCustomer(false)
        userProps.setIsEmployee(false)
        userProps.setIsAdmin(false)
      });

    return (
        <div>
            <Home></Home>
        </div>
    )
}

export default Logout
