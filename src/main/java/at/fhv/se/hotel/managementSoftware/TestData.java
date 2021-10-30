package at.fhv.se.hotel.managementSoftware;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import at.fhv.se.hotel.managementSoftware.domain.model.Booking;
import at.fhv.se.hotel.managementSoftware.domain.model.Customer;
import at.fhv.se.hotel.managementSoftware.domain.model.Customer.Gender;
import at.fhv.se.hotel.managementSoftware.domain.repositories.BookingRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.CustomerRepository;

@Component
public class TestData implements ApplicationRunner{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		customerRepository.addCustomer(new Customer("1", "Ulrich", "Vogler", "Kantstraﬂe", "32", "Rochlitz", "09301", "Deutschland", "UlrichVogler@rhyta.com", "+493737105579", Gender.MALE));
		customerRepository.addCustomer(new Customer("2", "Michelle", "Eichelberger", "Luebecker Strasse", "62", "Seubersdorf", "92358 ", "Deutschland", "MichelleEichelberger@rhyta.com", "++499497826628", Gender.FEMALE));
		
		bookingRepository.addBooking(new Booking("1", LocalDate.of(2021, 11, 3), LocalDate.of(2021, 11, 10), "5555555555554444", "1", 2)
				.addCategory("Doppelzimmer", 1));
		bookingRepository.addBooking(new Booking("2", LocalDate.of(2021, 11, 3), LocalDate.of(2021, 11, 15), "5555555555554444", "2", 6)
				.addCategory("Doppelzimmer", 2)
				.addCategory("Einzelzimmer", 2));
		bookingRepository.addBooking(new Booking("3", LocalDate.of(2021, 11, 10), LocalDate.of(2021, 11, 20), "5555555555554444", "1", 2)
				.addCategory("Suite", 1));
		
		
	}

}
