import React from 'react'
import "bootstrap/dist/css/bootstrap.min.css"
import Container from 'react-bootstrap/Container'
import Navbar from 'react-bootstrap/Navbar'
import logo from '../Logo_Font.png'
import Button from 'react-bootstrap/Button'

const Header = () => {
	return (
	<div>
  		<Navbar bg="dark" variant="dark" fixed="top">
    		<Container>
      			<Navbar.Brand href="#">
        		<img
          			alt="logo"
          			src={logo}
          			width="50"
          			height="50"
          			className="d-inline-block align-top"
        			/>
      				Hotel Schwarz
      			</Navbar.Brand>
      			<Navbar.Brand>
      			<Button variant="primary">Book now</Button>
      			</Navbar.Brand>
    		</Container>
  		</Navbar>
    </div>
  )
}

export default Header