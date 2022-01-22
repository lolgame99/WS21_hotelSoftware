import React from 'react'
import "bootstrap/dist/css/bootstrap.min.css"
import Container from 'react-bootstrap/Container'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import wifi from '../wifi.png'
import parking from '../parking.png'
import pool from '../pool.png'
import klima from '../klima.png'
import bar from '../bar.png'
import haustiere from '../haustiere.png'
import restaurant from '../restaurant.png'
import Button from 'react-bootstrap/Button'
import pic from '../pic.jpg'
import Image from 'react-bootstrap/Image'
import './Aboutus.css'

const Aboutus = () => {
	return (
	<div className = "aboutus">
  		<Container>
  				<Row>
  					<Col>
  						<Image
      						className="d-block w-100 rounded"
      						src= {pic}
      						alt="Second slide"
    						/>
  					</Col>
  					<Col>
  						<h1>A place to be</h1>
  						<p>Lech is one of the most beautiful places in Austria.
  						 	The naturalness of Lech make this place a must-see for everyone. 
  						 	Whether skiing in winter or hiking in summer in Lech you always have 
  						 	something to experience. 
							We, the Hotel Schwarz, have made it our business to offer our customers 
							an accommodation which is equal to the beauty of Lech. Therefore, we offer 
							our customers an unforgettable experience. Whether alone or with the family. 
							With us, everyone gets their money's worth. In addition to a bar, a sauna and an 
							indoor pool, we also offer our customers an excellent restaurant, where only the best 
							chefs in Austria work. Whether single room, double room or the family suite, we guarantee 
							that have never been accommodated like this.
						</p>
  						<br/>
  						<h2 className = "right">Best ratings</h2>
  						<p className = "comment">,,My family and I live in Vienna, so we haven't seen much of Austria's
  						 nature yet. That's why we decided to go on a nature vacation. Lech seemed to be the best choice. 
  						 While looking for accommodation, we came across the Hotel Schwarz. Due to its consistently good 
  						 rating, they stood out from the competition. Therefore, we decided and for the hotel Schwarz. 
  						 Fortunately, we chose this hotel, it was just perfect. From the welcome at the beginning to the
  						  farewell at the end everything has fit. The location, the food, the service, the offer, the rooms.... 
  						  I can recommend to everyone to visit Lech and even more the Hotel Schwarz."
  						  </p>
  						<p className = "name">Lisa Weiss</p>
  						<br/>
  						<h2>We offer</h2>
  						<Row xs="auto">
  							<Col>
  								<Button variant="primary" type="button" disabled>
  								<img className = "icon"
      							src={wifi}
      							alt="Second slide"
    							/>   Wifi</Button>
  							</Col>
  							<Col>
  								<Button variant="primary" type="button" disabled>
  								<img className = "icon"
      							src={bar}
      							alt="Second slide"
    							/>   Bar</Button>
  							</Col>
  							<Col>
  								<Button variant="primary" type="button" disabled>
  								<img className = "icon"
      							src={haustiere}
      							alt="Second slide"
    							/>   Pets allowed</Button>
  							</Col>
  							<Col>
  								<Button variant="primary" type="button" disabled>
  								<img className = "icon"
      							src={restaurant}
      							alt="Second slide"
    							/>   Restaurant</Button>
  							</Col>
  						</Row>
  						<br/>
  						<Row xs="auto">
  							<Col>
  								<Button variant="primary" type="button" disabled>
  								<img className = "icon"
      							src={klima}
      							alt="Second slide"
    							/>   Air-conditioned</Button>
  							</Col>
  							<Col>
  								<Button variant="primary" type="button" disabled>
  								<img className = "icon"
      							src={parking}
      							alt="Second slide"
    							/>   Parking lots</Button>
  							</Col>
  							<Col>
  								<Button variant="primary" type="button" disabled>
  								<img className = "icon"
      							src={pool}
      							alt="Second slide"
    							/>   Indoor pool</Button>
  							</Col>
  						</Row>
  					</Col>
  				</Row>
  		</Container>
    </div>
  )
}

export default Aboutus