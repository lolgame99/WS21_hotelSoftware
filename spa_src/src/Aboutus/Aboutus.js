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
import disco from '../disco.png'
import haustiere from '../haustiere.png'
import restaurant from '../restaurant.png'
import Button from 'react-bootstrap/Button'
import './Aboutus.css'

const Aboutus = () => {
	return (
	<div className = "aboutus">
  		<Container>
  				<Row>
  					<Col>
  						<img
      						className="d-block w-100"
      						src="http://via.placeholder.com/400x450"
      						alt="Second slide"
    						/>
  					</Col>
  					<Col>
  						<h1>A place to be</h1>
  						<p>Lech is the shit to be in. The whole world talks about Lech
  							But when you visit such a schönes place, you also have to have
  							a Unterkunft, which is such a schönen Stadt ebenwürdig. Egal ob alleine oder in der Familie
  							Sie haben immer Spaß. unser motto: "es kommt aus dem arsch wie kacke"</p>
  						<br/>
  						<h2 className = "right">Beste Bewertungen</h2>
  						<p className = "comment">,,Ich habe mit meiner Familie ein Wochenende im Hotel Schwarz verbracht.
  												Es hat von der Decke getropft, die Fenster gingen nicht zu
  												und die Toilettenspülung ging nicht. Trotzdem muss ich sagen,
  												dass das das beste Wochenende in meinem Leben war, da ich sonst
  												in einer Höhle lebe"</p>
  												<p className = "name">Höhlen Frau</p>
  						<br/>
  						<h2>Unser Angebot</h2>
  						<Row xs="auto">
  							<Col>
  								<Button variant="primary" type="button" disabled>
  								<img className = "icon"
      							src={wifi}
      							alt="Second slide"
    							/>   Wlan</Button>
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
      							src={disco}
      							alt="Second slide"
    							/>   Disco</Button>
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
    							/>   Klimatisiert</Button>
  							</Col>
  							<Col>
  								<Button variant="primary" type="button" disabled>
  								<img className = "icon"
      							src={parking}
      							alt="Second slide"
    							/>   Parkplätze</Button>
  							</Col>
  							<Col>
  								<Button variant="primary" type="button" disabled>
  								<img className = "icon"
      							src={pool}
      							alt="Second slide"
    							/>   Pool</Button>
  							</Col>
  							<Col>
  								<Button variant="primary" type="button" disabled>
  								<img className = "icon"
      							src={haustiere}
      							alt="Second slide"
    							/>   Haustiere</Button>
  							</Col>
  						</Row>
  					</Col>
  				</Row>
  		</Container>
    </div>
  )
}

export default Aboutus