package at.fhv.se.hotel.managementSoftware;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import at.fhv.se.hotel.managementSoftware.domain.enums.BookingStatus;
import at.fhv.se.hotel.managementSoftware.domain.enums.Gender;
import at.fhv.se.hotel.managementSoftware.domain.model.Address;
import at.fhv.se.hotel.managementSoftware.domain.model.Booking;
import at.fhv.se.hotel.managementSoftware.domain.model.Customer;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategory;
import at.fhv.se.hotel.managementSoftware.domain.repositories.BookingRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.CustomerRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomCategoryRepository;

@Component
public class TestData implements ApplicationRunner {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private RoomCategoryRepository roomCategoryRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		String[] customerUUID = {UUID.randomUUID().toString().toUpperCase(),UUID.randomUUID().toString().toUpperCase()};
		String[] categoryUUID = {UUID.randomUUID().toString().toUpperCase(),UUID.randomUUID().toString().toUpperCase(),UUID.randomUUID().toString().toUpperCase()};
		String[] bookingUUID = {UUID.randomUUID().toString().toUpperCase(),UUID.randomUUID().toString().toUpperCase(),UUID.randomUUID().toString().toUpperCase()};
		
		customerRepository.addCustomer(new Customer(customerUUID[0], "Ulrich", "Vogler", LocalDate.of(1988, 7, 21), new Address("Kantstrasse", "32", "Rochlitz", "09301", "Deutschland"), "UlrichVogler@rhyta.com", "+493737105579", Gender.MALE));
		customerRepository.addCustomer(new Customer(customerUUID[1], "Michelle", "Eichelberger", LocalDate.of(1991, 2, 15), new Address("Luebecker Strasse", "62", "Seubersdorf", "92358 ", "Deutschland"), "MichelleEichelberger@rhyta.com", "+499497826628", Gender.FEMALE));
		
		roomCategoryRepository.addRoomCategory(new RoomCategory(categoryUUID[0], "Single Room", 1));
		roomCategoryRepository.addRoomCategory(new RoomCategory(categoryUUID[1], "Double Room", 2));
		roomCategoryRepository.addRoomCategory(new RoomCategory(categoryUUID[2], "Family Suite", 4));
		
		
		
		bookingRepository.addBooking(new Booking(
				bookingUUID[0],
				LocalDate.now(),
				LocalDate.now().plusDays(7),
				"5555555555554444",
				customerRepository.getCustomerById(customerUUID[0]).get(),
				2,
				BookingStatus.CONFIRMED,
				new HashMap<RoomCategory, Integer>(){{put(roomCategoryRepository.getRoomCategoryById(categoryUUID[1]).get(),1);}})); 

		bookingRepository.addBooking(new Booking(
				bookingUUID[1],
				LocalDate.now().plusDays(1),
				LocalDate.now().plusDays(10),
				"5555555555554444",
				customerRepository.getCustomerById(customerUUID[1]).get(),
				6,
				BookingStatus.PENDING,
				new HashMap<RoomCategory, Integer>(){{put(roomCategoryRepository.getRoomCategoryById(categoryUUID[2]).get(), 1);
					put(roomCategoryRepository.getRoomCategoryById(categoryUUID[1]).get(), 1);}}));

		bookingRepository.addBooking(new Booking(
				bookingUUID[2],
				LocalDate.now().plusDays(7),
				LocalDate.now().plusDays(20),
				"5555555555554444",
				customerRepository.getCustomerById(customerUUID[0]).get(),
				2,
				BookingStatus.PAID,
				new HashMap<RoomCategory, Integer>(){{put(roomCategoryRepository.getRoomCategoryById(categoryUUID[0]).get(), 2);}}
				));
		
		
		
	}

}
