import Header from './components/Header.js';
import Home from './components/Home.js';
import EmployeeList from './components/EmployeeList.js';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import AboutUs from './components/AboutUs.js';
import Login from './components/Login.js';

function App() {
  return (
    <Router>
      <div className='mt-5'>
        <Header/>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path='/employees' element={<EmployeeList />} />
          <Route path="/about" element={<AboutUs />} />
          <Route path="/login" element={<Login />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
