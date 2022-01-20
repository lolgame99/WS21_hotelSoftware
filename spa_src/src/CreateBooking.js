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
			freeCategories:[],
			formData:{
				middleName:"",
				categoryAmounts:[0,0,0],
				customerId:"",
				companyName:"",
				discountRate:null,
				bookingId:null,
				bookingStatus:null
			},
			formSubmitted:false
		};
		this.dateSelected = this.dateSelected.bind(this);
		this.handleChange = this.handleChange.bind(this);
		this.handleDynamicChange = this.handleDynamicChange.bind(this);
		this.submitForm = this.submitForm.bind(this);
	}
	
	componentDidMount(){
		const categoryValues = [];
		fetch("http:"+baseUrl+":8080/api/category/getAll").then(res=>res.json()).then(
			result=>{
				this.setState({roomCategories:result});
				result.forEach((element, index)=>{
					categoryValues[index] = element.categoryId.id;
				});
			}
		)
		this.setState({formData: {categoryValues: categoryValues}});
		
		
	}
	
	handleChange(event){
		var value = event.target.value;
		var name = event.target.name;
		this.setState(state => ({
		  formData: {
		    ...state.formData,          
		    [name]: value
		  }
		}));
	}
	
	handleDynamicChange(event,index){
		var value = event.target.value;
		const updatedArray = {...this.state.formData.categoryAmounts};
		updatedArray[index] = Number(value);
		this.setState(prevState => ({
		  formData: {
		    ...prevState.formData,          
		    categoryAmounts: updatedArray
		  }
		}));
	}
	
	submitForm(event){
		event.preventDefault();
		var formData = {...this.state.formData};
		this.setState({formSubmitted:true});
		
		var counter = 0;
		var categoryAmounts = {...this.state.formData.categoryAmounts};
		var newArray =  [];
		categoryAmounts.forEach((element, index)=>{
			console.log(index);
		});
		
		fetch("http:"+baseUrl+":8080/api/booking/create",{
			method: "POST",
			body: JSON.stringify(formData),
			headers: new Headers({'content-type': 'application/json'})
		}).then(res=>res.json()).then(
			res=>{
				console.log(res)
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
		console.log(this.state.formData.categoryAmounts);
		return(
		<Container>
      	<br/>
      	<h3>Create booking</h3>
   		<Form onSubmit={(event)=>this.submitForm(event)}>
      		<Row>
      			<Col xs={6}>
      				<Row>
      					<h5>Customer information</h5>
    				</Row>
      				<Row>
      					<Col>
      						<Form.Group controllId="createBooking.firstName">
      							<Form.Label>First name</Form.Label>
      							<Form.Control type="text" required name="firstName" onChange={this.handleChange} value={this.state.formData.firstName} />
      						</Form.Group>
      					</Col>
      					<Col>
      						<Form.Group controllId="createBooking.middleName">
      							<Form.Label>Middle name</Form.Label>
      							<Form.Control type="text" name="middleName" onChange={this.handleChange} value={this.state.formData.middleName}/>
      						</Form.Group>
      					</Col>
      					<Col>
      						<Form.Group controllId="createBooking.lastName">
      							<Form.Label>Last name</Form.Label>
      							<Form.Control type="text" required name="lastName" onChange={this.handleChange} value={this.state.formData.lastName}/>
      						</Form.Group>
      					</Col>
      				</Row>
      				<Row>
      					<Col>
      						<Form.Group controllId="createBooking.gender">
      							<Form.Label>Gender</Form.Label>
      							<Form.Select name="gender" onChange={this.handleChange} value={this.state.formData.gender}>
 									<option value="MALE">Male</option>
  									<option value="FEMALE">Female</option>
 									<option value="DIVERSE">Divers</option>
								</Form.Select>
      						</Form.Group>
      					</Col>
      					<Col>
      						<Form.Group controllId="createBooking.birthDate">
      							<Form.Label>Date of birth</Form.Label>
      							<Form.Control type="date" required name="birthdate" onChange={this.handleChange} value={this.state.formData.birthdate}/>
      						</Form.Group>
      					</Col>
      				</Row>
      				<Row>
      					<Col>
      						<Form.Group controllId="createBooking.email">
      							<Form.Label>Email</Form.Label>
      							<Form.Control type="email" required name="email" onChange={this.handleChange} value={this.state.formData.email}/>
      						</Form.Group>
      					</Col>
      					<Col>
      						<Form.Group controllId="createBooking.phoneNumber">
      							<Form.Label>Phone number</Form.Label>
      							<Form.Control type="text" required name="phoneNumber" onChange={this.handleChange} value={this.state.formData.phoneNumber}/>
      						</Form.Group>
      					</Col>
      				</Row>
      				<Row>
      					<Col xs={9}>
      						<Form.Group controllId="createBooking.streetName">
      							<Form.Label>Street name</Form.Label>
      							<Form.Control type="text" required name="streetName" onChange={this.handleChange} value={this.state.formData.streetName}/>
      						</Form.Group>
      					</Col>
      					<Col>
      						<Form.Group controllId="createBooking.streetNumber">
      							<Form.Label>Street number</Form.Label>
      							<Form.Control type="text" required name="streetNumber" onChange={this.handleChange} value={this.state.formData.streetNumber}/>
      						</Form.Group>
      					</Col>
      				</Row>
      				<Row>
      					<Col xs={9}>
      						<Form.Group controllId="createBooking.city">
      							<Form.Label>City</Form.Label>
      							<Form.Control type="text" required name="city" onChange={this.handleChange} value={this.state.formData.city}/>
      						</Form.Group>
      					</Col>
      					<Col>
      						<Form.Group controllId="createBooking.postcode">
      							<Form.Label>Postcode</Form.Label>
      							<Form.Control type="text" required name="postcode" onChange={this.handleChange} value={this.state.formData.postcode}/>
      						</Form.Group>
      					</Col>
      				</Row>
      				<Row>
      					<Col>
      						<Form.Group controllId="createBooking.country">
      							<Form.Label>Country</Form.Label>
      							<Form.Select name="country" onChange={this.handleChange} value={this.state.formData.country}>
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
      							<Form.Control type="date" defaultValue={date} id="checkInDate" name="checkInDate" value={this.state.formData.checkInDate} onChange={(event) => {this.dateSelected(); this.handleChange(event)}} required/>
      						</Form.Group>
      					</Col>
      					<Col>
      						<Col>
	      						<Form.Group controllId="createBooking.checkOut">
	      							<Form.Label>Check-out date</Form.Label>
	      							<Form.Control type="date" defaultValue={date} name="checkOutDate" value={this.state.formData.checkOutDate} onChange={(event) => {this.dateSelected(); this.handleChange(event)}} id="checkOutDate" required/>
	      						</Form.Group>
      						</Col>
      					</Col>
      				</Row>
      				{this.state.roomCategories.map((cat,index)=>(
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
										<Form.Control type="number" min="0" max={free.categoryCount} name="categoryAmounts" onChange={(event)=>this.handleDynamicChange(event,index)} />
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
      							<Form.Label>Number of guests</Form.Label>
      							<Form.Control type="number" name="guestCount" onChange={this.handleChange} value={this.state.formData.guestCount} required/>
      						</Form.Group>
      					</Col>
      				</Row>
      				<Row>
      					<Col xs={9}>
      						<Form.Group controllId="createBooking.city">
      							<Form.Label>Creditcard number</Form.Label>
      							<Form.Control type="text" name="creditCardNumber" onChange={this.handleChange} value={this.state.formData.creditCardNumber} required/>
      						</Form.Group>
      					</Col>
      					<Col>
      						<Form.Group controllId="createBooking.postcode">
      							<Form.Label>Valid until</Form.Label>
      							<Form.Control type="text" placeholder="MM/YY" required name="creditCardValid" onChange={this.handleChange} value={this.state.formData.creditCardValid}/>
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