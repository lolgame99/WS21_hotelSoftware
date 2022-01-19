import Form from 'react-bootstrap/Form'
import Container from 'react-bootstrap/Container'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import Button from 'react-bootstrap/Button'

import "bootstrap/dist/css/bootstrap.min.css"

import React, {PureComponent} from 'react';

var curr = new Date();
curr.setDate(curr.getDate());
var date = curr.toISOString().substr(0,10);
var baseUrl = window.location.href.split(":")[1];

class CreateBooking extends PureComponent{
	constructor(props){
		super(props);
		
		this.state={
			roomCategories:[],
			freeCategories:[]
		};
		this.dateSelected = this.dateSelected.bind(this);
	}
	
	componentDidMount(){
		fetch("http:"+baseUrl+":8080/api/category/getAll").then(res=>res.json()).then(
			result=>{
				this.setState({roomCategories:result});
			}
		)
		
	}
	
	dateSelected(){
		var checkInDate = document.getElementById("checkInDate").value;
		var checkOutDate = document.getElementById("checkOutDate").value;
		fetch("http:"+baseUrl+":8080/api/room/getAvailableCount?from="+checkInDate+"&to="+checkOutDate).then(res=>res.json()).then(
			result=>{
				this.setState({freeCategories:result});
			}
		)
	}
	
	render(){
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
 									<option value="MALE">Male</option>
  									<option value="FEMALE">Female</option>
 									<option value="DIVERSE">Divers</option>
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
 									<option value="Austria">Austria</option>
  									<option value="Germany">Germany</option>
  									<option value="Switzerland">Switzerland</option>
  									<option value="United Kingdom">United Kingdom</option>
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
      						<Form.Group controllId="createBooking.checkIn">
      							<Form.Label>Check-in date</Form.Label>
      							<Form.Control type="date" defaultValue={date} id="checkInDate" onChange={this.dateSelected} required/>
      						</Form.Group>
      					</Col>
      					<Col>
      						<Col>
	      						<Form.Group controllId="createBooking.checkOut">
	      							<Form.Label>Check-out date</Form.Label>
	      							<Form.Control type="date" defaultValue={date} onChange={this.dateSelected} id="checkOutDate" required/>
	      						</Form.Group>
      						</Col>
      					</Col>
      				</Row>
      				{this.state.roomCategories.map(cat=>(
						<Row>
	      					<Col md={4}>
	      						<br/>
	      						<Form.Group controllId="createBooking.catFamily">
	      							<Form.Control type="text" placeholder={cat.name} data-id={cat.categoryId.id} disabled/>
	      						</Form.Group>
	      					</Col>
							<Col md={2}>
								<br/>
								<Form.Group controllId="createBooking.catFree">
	      							{this.state.freeCategories.map(free=>{
									return free.category===cat.categoryId.id ?
										<Form.Control type="text" placeholder={"Free: "+free.categoryCount} disabled/>
									: null
									
									})} 
	      						</Form.Group>
							</Col>
	      					<Col md={6}>
	      						<br/>
	      						<Form.Group controllId="createBooking.familyRoomCount">
									{this.state.freeCategories.map(free=>{
									return free.category===cat.categoryId.id ?
										<Form.Control type="number" min="0" max={free.categoryCount} />
									: null
									
									})} 
	      							
	      						</Form.Group>
	      					</Col>
	      				</Row>
					))}
      				
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
	}
}


export default CreateBooking;