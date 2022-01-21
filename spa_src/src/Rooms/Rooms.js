import React from 'react'
import "bootstrap/dist/css/bootstrap.min.css"
import Container from 'react-bootstrap/Container'
import Carousel from 'react-bootstrap/Carousel'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import Card from 'react-bootstrap/Card'
import Button from 'react-bootstrap/Button'
import './Rooms.css'

const Rooms = () => {
	return (
	<div>
		<Container>
			<h1>
				Accommodation
			</h1>
			<Row>
				<Col>
					<Card style={{ width: '25rem', height: '30rem' }}>
  						<Carousel>
  							<Carousel.Item>
    							<img
      							className="d-block w-100"
     							src="http://via.placeholder.com/286x200"
      							alt="First slide"
    							/>
  							</Carousel.Item>
  							<Carousel.Item>
    						<img
      							className="d-block w-100"
      							src="http://via.placeholder.com/286x200"
      							alt="Second slide"
    							/>
  							</Carousel.Item>
  							<Carousel.Item>
    							<img
      							className="d-block w-100"
      							src="http://via.placeholder.com/286x200"
      							alt="Third slide"
    							/>
  							</Carousel.Item>
						</Carousel>
  						<Card.Body>
    						<Card.Title>Single Room</Card.Title>
    						<Card.Text>
      							Ein Single Room damit man wichsen kann
    						</Card.Text>
    						<br/>
    						<h5 className = "price">75€/day</h5>
  						</Card.Body>
					</Card>
				</Col>
				<Col>
					<Card style={{ width: '25rem', height: '30rem' }}>
  						<Carousel>
  							<Carousel.Item>
    							<img
      							className="d-block w-100"
     							src="http://via.placeholder.com/286x200"
      							alt="First slide"
    							/>
  							</Carousel.Item>
  							<Carousel.Item>
    						<img
      							className="d-block w-100"
      							src="http://via.placeholder.com/286x200"
      							alt="Second slide"
    							/>
  							</Carousel.Item>
  							<Carousel.Item>
    							<img
      							className="d-block w-100"
      							src="http://via.placeholder.com/286x200"
      							alt="Third slide"
    							/>
  							</Carousel.Item>
						</Carousel>
  						<Card.Body>
    						<Card.Title>Double Room</Card.Title>
    						<Card.Text>
      							Ein Single Room damit man zu zweit wichsen kann
    						</Card.Text>
    						<br/>
    						<h5 className = "price">125€/day</h5>
  						</Card.Body>
					</Card>
				</Col>
				<Col>
					<Card style={{ width: '25rem', height: '30rem' }}>
  						<Carousel>
  							<Carousel.Item>
    							<img
      							className="d-block w-100"
     							src="http://via.placeholder.com/286x200"
      							alt="First slide"
    							/>
  							</Carousel.Item>
  							<Carousel.Item>
    						<img
      							className="d-block w-100"
      							src="http://via.placeholder.com/286x200"
      							alt="Second slide"
    							/>
  							</Carousel.Item>
  							<Carousel.Item>
    							<img
      							className="d-block w-100"
      							src="http://via.placeholder.com/286x200"
      							alt="Third slide"
    							/>
  							</Carousel.Item>
						</Carousel>
  						<Card.Body>
    						<Card.Title>Family Suite</Card.Title>
    						<Card.Text>
      							Ein Single Room damit man als Familie wichsen kann
    						</Card.Text>
    						<br/>
    						<h5 className = "price">250€/day</h5>
  						</Card.Body>
					</Card>
				</Col>
			</Row>
		</Container>
    </div>
  )
}

export default Rooms