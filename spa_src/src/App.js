import React, { Component } from "react";

import Booking from './CreateBooking'
import Homepage from './Homepage'
import "bootstrap/dist/css/bootstrap.min.css"
import Container from 'react-bootstrap/Container'
import Navbar from 'react-bootstrap/Navbar'
import Button from 'react-bootstrap/Button'

class App extends Component {
	constructor() {
	    super();
	    this.state = {
	      showBooking: false
	    };
	    this.switchView = this.switchView.bind(this);
	 }
	
	switchView() {
		this.setState({showBooking: !this.state.showBooking})
	}

	render(){
		if(this.state.showBooking==false){
			return(
				<div>
					<div>
			  		<Navbar bg="dark" variant="dark" fixed="top">
			    		<Container>
			      			<Navbar.Brand>
			      				Hotel Schwarz
			      			</Navbar.Brand>
			      			<Navbar.Brand>
			      			<Button variant="primary" onClick={() => this.switchView()}>Book now</Button>
			      			</Navbar.Brand>
			    		</Container>
			  		</Navbar>
			    </div>
				<Homepage />
				</div>
			)
		}else{
			return(
				<div>
					<div>
			  		<Navbar bg="dark" variant="dark" fixed="top">
			    		<Container>
			      			<Navbar.Brand>
			      				Hotel Schwarz
			      			</Navbar.Brand>
			      			<Navbar.Brand>
			      			<Button variant="primary" onClick={() => this.switchView()}>Homepage</Button>
			      			</Navbar.Brand>
			    		</Container>
			  		</Navbar>
			    </div>
				<br />
				<br />
				<Booking />
				</div>
			)
		}
		
		
	}
}

export default App;
