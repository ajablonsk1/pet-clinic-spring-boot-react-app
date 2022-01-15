import Header from './components/Header.js';
import Home from './components/Home.js';
import EmployeeList from './components/EmployeeList.js';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import AboutUs from './components/AboutUs.js';
import Login from './components/Login.js';
import Register from './components/Register.js';
import Logout from './components/Logout.js';
import { useState, useEffect } from 'react';
import RegisterAsCustomer from './components/customer.components/RegisterAsCustomer.js';
import UserPetList from './components/customer.components/UserPetList.js';
import UserAppointmentList from './components/customer.components/UserAppointmentList.js';
import EmloyeeAppointmentList from './components/employee.components/EmloyeeAppointmentList.js';
import AdminAppiontmentList from './components/admin.components/AdminAppiontmentList.js';
import AdminUserList from './components/admin.components/AdminUserList.js';
import AdminEmployeeList from './components/admin.components/AdminEmployeeList.js';
import AdminPetList from './components/admin.components/AdminPetList.js';
import AdminCustomerList from './components/admin.components/AdminCustomerList.js';
import AddPet from './components/customer.components/AddPet.js';
import BookAppointment from './components/customer.components/BookAppointment.js';

function App() {
  const [isAuth, setIsAuth] = useState(false)
  const [user, setUser] = useState(JSON.parse(localStorage.getItem('user')))
  const [isAdmin, setIsAdmin] = useState(false)
  const [isEmployee, setIsEmployee] = useState(false)
  const [isCustomer, setIsCustomer] = useState(false)

  const userProps = {user, setUser, isAdmin, setIsAdmin, isEmployee, setIsEmployee, isCustomer, setIsCustomer}

  useEffect(() => {
    if(user){
      setIsAuth(true)
      if(user.roles.includes("ROLE_ADMIN")){
        setIsAdmin(true)
      }
      if(user.roles.includes("ROLE_EMPLOYEE")){
        setIsEmployee(true)
      }
      if(user.roles.includes("ROLE_CUSTOMER")){
        setIsCustomer(true)
      }
    }
    else{
      setIsAuth(false)
    }
  }, [user])

  return (
    <Router>
      <div className='mt-5'>
        <Header isAuth={isAuth} userProps={userProps} />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path='/employees' element={<EmployeeList />} />
          <Route path="/about" element={<AboutUs />} />
          <Route path="/login" element={<Login userProps={userProps}/>} />
          <Route path="/register" element={<Register />} />
          <Route path="/logout" element={<Logout userProps={userProps}/>} />
          <Route path="/register/customer" element={<RegisterAsCustomer user={user}/>} />
          <Route path="/pets/user" element={<UserPetList user={user}/>} />
          <Route path="/appointments/customer" element={<UserAppointmentList user={user}/>} />
          <Route path="/appointments/employee" element={<EmloyeeAppointmentList user={user}/>} />
          <Route path="/appointments/admin" element={<AdminAppiontmentList/>} />
          <Route path="/users/admin" element={<AdminUserList/>} />
          <Route path="/employees/admin" element={<AdminEmployeeList/>} />
          <Route path="/pets/admin" element={<AdminPetList/>} />
          <Route path="/customers/admin" element={<AdminCustomerList/>} />
          <Route path="/pets/add" element={<AddPet user={user}/>} />
          <Route path="/appointments/book" element={<BookAppointment user={user}/>} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
