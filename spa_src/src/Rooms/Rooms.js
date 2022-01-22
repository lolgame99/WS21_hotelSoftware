import React from 'react'
import "bootstrap/dist/css/bootstrap.min.css"
import Container from 'react-bootstrap/Container'
import Carousel from 'react-bootstrap/Carousel'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import Card from 'react-bootstrap/Card'
import double1 from '../double1.jpg'
import double2 from '../double2.jpg'
import double3 from '../double3.jpg'
import single1 from '../single1.jpg'
import single2 from '../single2.jpg'
import single3 from '../single3.jpg'
import family1 from '../family1.jpg'
import family2 from '../family2.jpg'
import family3 from '../family3.jpg'
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
      							className="d-block w-100 room"
     							src={single3}
      							alt="First slide"
    							/>
  							</Carousel.Item>
  							<Carousel.Item>
    						<img
      							className="d-block w-100 room"
      							src= {single1}
      							alt="Second slide"
    							/>
  							</Carousel.Item>
  							<Carousel.Item>
    							<img
      							className="d-block w-100 room"
      							src= {single2}
      							alt="Third slide"
    							/>
  							</Carousel.Item>
						</Carousel>
  						<Card.Body>
    						<Card.Title>Single Room</Card.Title>
    						<Card.Text>
      							Our single rooms are perfect for anyone
      							 who wants to get away from it all and spend 
      							 some time with themselves. The rooms are up to 
      							 date and offer an unforgettable view.
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
      							className="d-block w-100 room"
     							src= {double1}
      							alt="First slide"
    							/>
  							</Carousel.Item>
  							<Carousel.Item>
    						<img
      							className="d-block w-100 room"
      							src= {double2}
      							alt="Second slide"
    							/>
  							</Carousel.Item>
  							<Carousel.Item>
    							<img
      							className="d-block w-100 room"
      							src= {double3}
      							alt="Third slide"
    							/>
  							</Carousel.Item>
						</Carousel>
  						<Card.Body>
    						<Card.Title>Double Room</Card.Title>
    						<Card.Text>
      							The double rooms offer the perfect opportunity
      							 for some togetherness. You and your partner are
      							  guaranteed to have a great time.
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
      							className="d-block w-100 room"
     							src= {family1}
      							alt="First slide"
    							/>
  							</Carousel.Item>
  							<Carousel.Item>
    						<img
      							className="d-block w-100 room"
      							src= {family2}
      							alt="Second slide"
    							/>
  							</Carousel.Item>
  							<Carousel.Item>
    							<img
      							className="d-block w-100 room"
      							src= {family3}
      							alt="Third slide"
    							/>
  							</Carousel.Item>
						</Carousel>
  						<Card.Body>
    						<Card.Title>Family Suite</Card.Title>
    						<Card.Text>
      							Our family suite is perfect for any family. 
      							A double bed and 2 single beds perfectly match 
      							the interior design. If needed, one more bed can 
      							be placed in the room.
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