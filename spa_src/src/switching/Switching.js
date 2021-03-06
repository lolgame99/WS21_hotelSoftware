import React from 'react'
import "bootstrap/dist/css/bootstrap.min.css"
import Carousel from 'react-bootstrap/Carousel'
import pan1 from '../pan1.jpg'
import pan2 from '../pan2.jpg'
import pan3 from '../pan3.jpg'
import './Switching.css'

const Switching = () => {
	return (
	<div>
		<Carousel>
  			<Carousel.Item>
    			<img
      			className="d-block w-100 pic"
     			src= {pan1}
      			alt="First slide"
    			/>
  			</Carousel.Item>
  			<Carousel.Item>
    			<img
      			className="d-block w-100 pic"
      			src= {pan2}
      			alt="Second slide"
    			/>
  			</Carousel.Item>
  			<Carousel.Item>
    			<img
      			className="d-block w-100 pic"
      			src= {pan3}
      			alt="Third slide"
    			/>
  			</Carousel.Item>
		</Carousel>
    </div>
  )
}

export default Switching