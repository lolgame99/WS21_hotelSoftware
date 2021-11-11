package at.fhv.se.hotel.managementSoftware;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import at.fhv.se.hotel.managementSoftware.domain.enums.BookingStatus;
import at.fhv.se.hotel.managementSoftware.domain.enums.Gender;
import at.fhv.se.hotel.managementSoftware.domain.model.Booking;
import at.fhv.se.hotel.managementSoftware.domain.model.BookingId;
import at.fhv.se.hotel.managementSoftware.domain.model.Customer;
import at.fhv.se.hotel.managementSoftware.domain.model.CustomerId;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategory;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategoryId;
import at.fhv.se.hotel.managementSoftware.domain.repositories.BookingRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.CustomerRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomCategoryRepository;
import at.fhv.se.hotel.managementSoftware.domain.valueObjects.Address;

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
		CustomerId[] customerUUID = new CustomerId[6];
		for (int i = 0; i < customerUUID.length; i++) {
			customerUUID[i] = new CustomerId(UUID.randomUUID().toString().toUpperCase());
		}
		RoomCategoryId[] categoryUUID = new RoomCategoryId[3];
		for (int i = 0; i < categoryUUID.length; i++) {
			categoryUUID[i] = new RoomCategoryId(UUID.randomUUID().toString().toUpperCase());
		}
		BookingId[] bookingUUID = new BookingId[6];
		for (int i = 0; i < bookingUUID.length; i++) {
			bookingUUID[i] = new BookingId(UUID.randomUUID().toString().toUpperCase());
		}
		
		customerRepository.addCustomer(Customer.create(customerUUID[0], "Ulrich", "Vogler", LocalDate.of(1988, 7, 21), new Address("Kantstrasse", "32", "Rochlitz", "09301", "Germany"), "UlrichVogler@rhyta.com", "+493737105579", Gender.MALE));
		customerRepository.addCustomer(Customer.create(customerUUID[1], "Michelle", "Eichelberger", LocalDate.of(1991, 2, 15), new Address("Luebecker Strasse", "62", "Seubersdorf", "92358 ", "Germany"), "MichelleEichelberger@rhyta.com", "+499497826628", Gender.FEMALE));
		customerRepository.addCustomer(Customer.create(customerUUID[2], "Ursula", "Eichelberger", LocalDate.of(1991, 2, 15), new Address("Luebecker Strasse", "62", "Seubersdorf", "92358 ", "Germany"), "MichelleEichelberger@rhyta.com", "+499497826628", Gender.FEMALE));
		customerRepository.addCustomer(Customer.create(customerUUID[3], "Erling", "Haaland", LocalDate.of(2000, 5, 28), new Address("Marktplatz", "21", "Dortmund", "44135 ", "Germany"), "Haaland.goat@rhyta.com", "+495863126628", Gender.MALE));
		customerRepository.addCustomer(Customer.create(customerUUID[4], "Cristiano", "Ronaldo", LocalDate.of(1985, 2, 5), new Address("Oxford Street", "12", "Manchester", "M1", "United Kingdom"), "CristianoRonaldo@rhyta.com", "+449497823332", Gender.MALE));
		customerRepository.addCustomer(Customer.create(customerUUID[5], "Conchita", "Wurst", LocalDate.of(1994, 10, 11), new Address("Wurst Strasse", "3", "Wien", "1010", "Austria"), "Conchita@wurst.com", "+436642135879", Gender.DIVERSE));

		
		roomCategoryRepository.addRoomCategory(RoomCategory.createWithoutDescription(categoryUUID[0], "Single Room", 1));
		roomCategoryRepository.addRoomCategory(RoomCategory.createWithoutDescription(categoryUUID[1], "Double Room", 2));
		roomCategoryRepository.addRoomCategory(RoomCategory.createWithoutDescription(categoryUUID[2], "Family Suite", 4));
		
		
		bookingRepository.addBooking(Booking.create(
				bookingUUID[0],
				LocalDate.now(),
				LocalDate.now().plusDays(7),
				"5555555555554444",
				customerUUID[0],
				2,
				BookingStatus.CONFIRMED,
				new HashMap<RoomCategory, Integer>(){{put(roomCategoryRepository.getRoomCategoryById(categoryUUID[1]).get(),1);}})); 

		bookingRepository.addBooking(Booking.create(
				bookingUUID[1],
				LocalDate.now(),
				LocalDate.now().plusDays(10),
				"5555555555554444",
				customerUUID[1],
				6,
				BookingStatus.PENDING,
				new HashMap<RoomCategory, Integer>(){{put(roomCategoryRepository.getRoomCategoryById(categoryUUID[2]).get(), 1);
					put(roomCategoryRepository.getRoomCategoryById(categoryUUID[1]).get(), 1);}}));

		bookingRepository.addBooking(Booking.create(
				bookingUUID[2],
				LocalDate.now().plusDays(7),
				LocalDate.now().plusDays(16),
				"5555555555554444",
				customerUUID[2],
				2,
				BookingStatus.PAID,
				new HashMap<RoomCategory, Integer>(){{put(roomCategoryRepository.getRoomCategoryById(categoryUUID[0]).get(), 2);}}
				));
		
		bookingRepository.addBooking(Booking.create(
				bookingUUID[3],
				LocalDate.now().plusDays(1),
				LocalDate.now().plusDays(7),
				"5555555555554444",
				customerUUID[3],
				3,
				BookingStatus.CONFIRMED,
				new HashMap<RoomCategory, Integer>(){{put(roomCategoryRepository.getRoomCategoryById(categoryUUID[0]).get(), 3);}}
				));
		
		bookingRepository.addBooking(Booking.create(
				bookingUUID[4],
				LocalDate.now().plusDays(9),
				LocalDate.now().plusDays(10),
				"5555555555554444",
				customerUUID[4],
				8,
				BookingStatus.PAID,
				new HashMap<RoomCategory, Integer>(){{put(roomCategoryRepository.getRoomCategoryById(categoryUUID[1]).get(), 2);
														put(roomCategoryRepository.getRoomCategoryById(categoryUUID[2]).get(), 2);}}
				));
		
		bookingRepository.addBooking(Booking.create(
				bookingUUID[5],
				LocalDate.now().plusDays(2),
				LocalDate.now().plusDays(4),
				"5555555555554444",
				customerUUID[5],
				1,
				BookingStatus.PENDING,
				new HashMap<RoomCategory, Integer>(){{put(roomCategoryRepository.getRoomCategoryById(categoryUUID[0]).get(), 1);}}
				));
		
		
	}

}
