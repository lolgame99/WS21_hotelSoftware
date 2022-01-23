import React from 'react';

import Header from './header/Header'
import Switching from './switching/Switching'
import Rooms from './Rooms/Rooms'
import Aboutus from './Aboutus/Aboutus'

function Homepage() {
  return (
    <div className="App">
    	<Header/>
    	<Switching/>
    	<br/>
    	<Aboutus/>
    	<br/>
    	<Rooms/>
    	<br/>
    </div>
  );
}

export default Homepage;