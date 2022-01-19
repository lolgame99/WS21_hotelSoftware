import Form from 'react-bootstrap/Form'
import Container from 'react-bootstrap/Container'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import Button from 'react-bootstrap/Button'

import "bootstrap/dist/css/bootstrap.min.css"

import React from 'react';

const CreateBooking = () => {
	return(
		<Container>
      	<br/>
      	<h3>Create booking</h3>
   		<Form>
      		<Row>
      			<Col xs={6}>
      				<Row>
      					<h5>Customer information</h5>
    				</Row>
      				<Row>
      					<Col>
      						<Form.Group controllId="createBooking.firstName">
      							<Form.Label>First name</Form.Label>
      							<Form.Control type="text" required/>
      						</Form.Group>
      					</Col>
      					<Col>
      						<Form.Group controllId="createBooking.middleName">
      							<Form.Label>Middle name</Form.Label>
      							<Form.Control type="text"/>
      						</Form.Group>
      					</Col>
      					<Col>
      						<Form.Group controllId="createBooking.lastName">
      							<Form.Label>Last name</Form.Label>
      							<Form.Control type="text" required/>
      						</Form.Group>
      					</Col>
      				</Row>
      				<Row>
      					<Col>
      						<Form.Group controllId="createBooking.gender">
      							<Form.Label>Gender</Form.Label>
      							<Form.Select>
 									<option value="1">Male</option>
  									<option value="2">Female</option>
 									<option value="3">Divers</option>
								</Form.Select>
      						</Form.Group>
      					</Col>
      					<Col>
      						<Form.Group controllId="createBooking.birthDate">
      							<Form.Label>Date of birth</Form.Label>
      							<Form.Control type="date" required/>
      						</Form.Group>
      					</Col>
      				</Row>
      				<Row>
      					<Col>
      						<Form.Group controllId="createBooking.email">
      							<Form.Label>Email</Form.Label>
      							<Form.Control type="email" required/>
      						</Form.Group>
      					</Col>
      					<Col>
      						<Form.Group controllId="createBooking.phoneNumber">
      							<Form.Label>Phone number</Form.Label>
      							<Form.Control type="text" required/>
      						</Form.Group>
      					</Col>
      				</Row>
      				<Row>
      					<Col xs={9}>
      						<Form.Group controllId="createBooking.streetName">
      							<Form.Label>Street name</Form.Label>
      							<Form.Control type="text" required/>
      						</Form.Group>
      					</Col>
      					<Col>
      						<Form.Group controllId="createBooking.streetNumber">
      							<Form.Label>Street number</Form.Label>
      							<Form.Control type="text" required/>
      						</Form.Group>
      					</Col>
      				</Row>
      				<Row>
      					<Col xs={9}>
      						<Form.Group controllId="createBooking.city">
      							<Form.Label>City</Form.Label>
      							<Form.Control type="text" required/>
      						</Form.Group>
      					</Col>
      					<Col>
      						<Form.Group controllId="createBooking.postcode">
      							<Form.Label>Postcode</Form.Label>
      							<Form.Control type="text" required/>
      						</Form.Group>
      					</Col>
      				</Row>
      				<Row>
      					<Col>
      						<Form.Group controllId="createBooking.country">
      							<Form.Label>Country</Form.Label>
      							<Form.Select>
 									<option value="1">Austria</option>
  									<option value="2">Germany</option>
  									<option value="3">Switzerland</option>
  									<option value="4">United Kingdom</option>
								</Form.Select>
      						</Form.Group>
      					</Col>
      				</Row>
      			</Col>
      			<Col xs={6}>
      				<Row>
      					<h5>Customer information</h5>
      					
      				</Row>
      				<Row>
      					<Col>
      						<Col>
      						<Form.Group controllId="createBooking.checkOut">
      							<Form.Label>Check-out date</Form.Label>
      							<Form.Control type="date" required/>
      						</Form.Group>
      					</Col>
      					</Col>
      					<Col>
      						<Form.Group controllId="createBooking.checkIn">
      							<Form.Label>Check-in date</Form.Label>
      							<Form.Control type="date" required/>
      						</Form.Group>
      					</Col>
      				</Row>
      				
      				<Row>
      					<Col>
      						<br/>
      						<Form.Group controllId="createBooking.catFamily">
      							<Form.Control type="text" placeholder="Family suite (4 beds)" disabled/>
      						</Form.Group>
      					</Col>
      					<Col>
      						<br/>
      						<Form.Group controllId="createBooking.familyRoomCount">
      							<Form.Control type="number"/>
      						</Form.Group>
      					</Col>
      				</Row>
      				<Row>
      					<Col>
      						<br/>
      						<Form.Group controllId="createBooking.numberAdults">
      							<Form.Label>Number of adults</Form.Label>
      							<Form.Control type="number" required/>
      						</Form.Group>
      					</Col>
      					<Col>
      						<br/>
      						<Form.Group controllId="createBooking.numberChildren">
      							<Form.Label>Number of children (4-17 years)</Form.Label>
      							<Form.Control type="number"/>
      						</Form.Group>
      					</Col>
      				</Row>
      				<Row>
      					<Col xs={9}>
      						<Form.Group controllId="createBooking.city">
      							<Form.Label>Creditcard number</Form.Label>
      							<Form.Control type="text" required/>
      						</Form.Group>
      					</Col>
      					<Col>
      						<Form.Group controllId="createBooking.postcode">
      							<Form.Label>Valid until</Form.Label>
      							<Form.Control type="text" placeholder="MM/YY" required/>
      						</Form.Group>
      					</Col>
      				</Row>
      			</Col>
      			<Row>
      				<Col>
      					<br/>
      					<Button variant="primary" type="submit">
   							Submit
  						</Button>
  						<br/><p/>
      				</Col>
      			</Row>
      		</Row>
      	</Form>
      </Container>
	);
};

export default CreateBooking;