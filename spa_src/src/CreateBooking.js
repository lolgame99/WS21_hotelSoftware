import Form from 'react-bootstrap/Form'
import Container from 'react-bootstrap/Container'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import Button from 'react-bootstrap/Button'
import Alert from 'react-bootstrap/Alert'

import "bootstrap/dist/css/bootstrap.min.css"

import React, {PureComponent} from 'react';

var baseUrl = window.location.href.split(":")[1];

const initialState = {
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

class CreateBooking extends PureComponent{
	constructor(props){
		super(props);
		
		this.state=initialState;
		this.dateSelected = this.dateSelected.bind(this);
		this.handleChange = this.handleChange.bind(this);
		this.handleDynamicChange = this.handleDynamicChange.bind(this);
		this.submitForm = this.submitForm.bind(this);
		this.clearState = this.clearState.bind(this);
		this.goBack = this.goBack.bind(this);
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
	
	clearState(){
		this.setState({...initialState});
	}
	
	goBack(){
		this.setState({formSubmitted:false});
	}
	
	handleDynamicChange(event,index){
		var value = event.target.value;
		var updatedArray = {...this.state.formData.categoryAmounts};
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
		
		
		var oldCategoryAmounts = {...this.state.formData.categoryAmounts};
		var oldCategoryValues = {...this.state.formData.categoryValues};
		var newCategoryAmounts =[];
		var newCategoryValues = [];
		
		Object.entries(oldCategoryAmounts).map(([key, value]) => {
		    newCategoryAmounts.push(value)
			newCategoryValues.push(oldCategoryValues[key]);
		})
		formData.categoryAmounts = newCategoryAmounts;
		formData.categoryValues = newCategoryValues;
		
		fetch("http:"+baseUrl+":8080/api/booking/create",{
			method: "POST",
			body: JSON.stringify(formData),
			headers: new Headers({'content-type': 'application/json'})
		}).then(res=>res.json()).then(
			res=>{
				res.message = res.message.split("/>")[1];
				this.setState({response:res});
				this.setState({formSubmitted:true});
				
				
			}
		)
	}
	
	dateSelected(){
		var checkInDate = document.getElementById("checkInDate").value;
		var checkOutDate = document.getElementById("checkOutDate").value;
		if(checkInDate!=""&&checkOutDate!=""){
			fetch("http:"+baseUrl+":8080/api/room/getAvailableCount?from="+checkInDate+"&to="+checkOutDate).then(res=>res.json()).then(
			result=>{
				this.setState({freeCategories:result});
			})
		}
	}
	
	render(){
		if(!this.state.formSubmitted){
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
		      							<Form.Select name="gender" onChange={this.handleChange} value={this.state.formData.gender} required>
											<option value="null" hidden>Select gender...</option>
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
		      							<Form.Select name="country" onChange={this.handleChange} value={this.state.formData.country} required>
											<option value="null" hidden>Select country...</option>
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
		      					<h5>Booking information</h5>
		      					
		      				</Row>
		      				<Row>
								<Col>
		      						<Form.Group controllId="createBooking.checkIn">
		      							<Form.Label>Check-in date</Form.Label>
		      							<Form.Control type="date" id="checkInDate" name="checkInDate" value={this.state.formData.checkInDate} onChange={(event) => {this.dateSelected(); this.handleChange(event)}} required/>
		      						</Form.Group>
		      					</Col>
		      					<Col>
		      						<Col>
			      						<Form.Group controllId="createBooking.checkOut">
			      							<Form.Label>Check-out date</Form.Label>
			      							<Form.Control type="date" name="checkOutDate" value={this.state.formData.checkOutDate} onChange={(event) => {this.dateSelected(); this.handleChange(event)}} id="checkOutDate" required/>
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
												<Form.Control type="number" min="0" max={free.categoryCount} defaultValue="0" name="categoryAmounts" onChange={(event)=>this.handleDynamicChange(event,index)} />
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
		}else{
			if(this.state.response.status =="ok"){
				return(
					<Container>
						<br />		
						<Alert variant="success">
							<p class="fs-3 fw-bold">Success</p>
	           				<p class="fs-4">{this.state.response.message}</p>
						</Alert>
					</Container>
				);
			}else{
				return(
					<Container>
						<br />
						<Alert variant="danger">
							<p class="fs-3 fw-bold">The following error occured</p>
	           				<p class="fs-4">{this.state.response.message}</p>
						</Alert>
						<Button variant="primary" type="button" onClick={this.goBack}>
		   							Go Back
		  						</Button>
					</Container>
				);
			}
			
		}
		
	}
}


export default CreateBooking;