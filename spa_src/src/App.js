import React from 'react';

import Header from './header/Header'
import Booking from './CreateBooking'
import Switching from './switching/Switching'
import Rooms from './Rooms/Rooms'
import Aboutus from './Aboutus/Aboutus'

function App() {
  return (
    <div className="App">
    	<Header/>
    	<Switching/>
    	<br/>
    	<Aboutus/>
    	<br/>
    	<Rooms/>
    </div>
  );
}

export default App;
