import React from 'react'
import "bootstrap/dist/css/bootstrap.min.css"
import Container from 'react-bootstrap/Container'
import Navbar from 'react-bootstrap/Navbar'
import Button from 'react-bootstrap/Button'

const Header = () => {
	return (
	<div>
  		<Navbar bg="dark" variant="dark" fixed="top">
    		<Container>
      			<Navbar.Brand>
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