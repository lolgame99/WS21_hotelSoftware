package at.fhv.se.hotel.managementSoftware;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import at.fhv.se.hotel.managementSoftware.domain.enums.BookingStatus;
import at.fhv.se.hotel.managementSoftware.domain.enums.Gender;
import at.fhv.se.hotel.managementSoftware.domain.enums.RoomStatus;
import at.fhv.se.hotel.managementSoftware.domain.model.Booking;
import at.fhv.se.hotel.managementSoftware.domain.model.BookingId;
import at.fhv.se.hotel.managementSoftware.domain.model.Customer;
import at.fhv.se.hotel.managementSoftware.domain.model.CustomerId;
import at.fhv.se.hotel.managementSoftware.domain.model.Guest;
import at.fhv.se.hotel.managementSoftware.domain.model.GuestId;
import at.fhv.se.hotel.managementSoftware.domain.model.Price;
import at.fhv.se.hotel.managementSoftware.domain.model.Room;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomAssignment;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomAssignmentId;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategory;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategoryId;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomId;
import at.fhv.se.hotel.managementSoftware.domain.model.Stay;
import at.fhv.se.hotel.managementSoftware.domain.model.StayId;
import at.fhv.se.hotel.managementSoftware.domain.repositories.BookingRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.CustomerRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.GuestRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.PriceRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomAssignmentRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomCategoryRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.StayRepository;
import at.fhv.se.hotel.managementSoftware.domain.valueObjects.Address;

@Profile("!test")
@Component
public class TestData implements ApplicationRunner {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private RoomCategoryRepository roomCategoryRepository;
	
	@Autowired
	private StayRepository stayRepository;
	
	@Autowired
	private GuestRepository guestRepository;
	
	@Autowired
	private RoomAssignmentRepository roomAssignmentRepository;
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private PriceRepository priceRepository;

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
		GuestId[] guestUUID = new GuestId[2];
		for (int i = 0; i < guestUUID.length; i++) {
			guestUUID[i] = new GuestId(UUID.randomUUID().toString().toUpperCase());
		}
		
		StayId[] stayUUID = new StayId[4];
		for (int i = 0; i < stayUUID.length; i++) {
			stayUUID[i] = new StayId(UUID.randomUUID().toString().toUpperCase());
		}
		
		RoomAssignmentId[] roomAssignmentUUID = new RoomAssignmentId[3];
		for (int i = 0; i < roomAssignmentUUID.length; i++) {
			roomAssignmentUUID[i] = new RoomAssignmentId(UUID.randomUUID().toString().toUpperCase());
		}
		
		customerRepository.addCustomer(Customer.create(customerUUID[0], "Ulrich", "Vogler", LocalDate.of(1988, 7, 21), new Address("Kantstrasse", "32", "Rochlitz", "09301", "Germany"), "UlrichVogler@rhyta.com", "+493737105579", Gender.MALE));
		customerRepository.addCustomer(Customer.create(customerUUID[1], "Michelle", "Eichelberger", LocalDate.of(1991, 2, 15), new Address("Luebecker Strasse", "62", "Seubersdorf", "92358 ", "Germany"), "MichelleEichelberger@rhyta.com", "+499497826628", Gender.FEMALE));
		customerRepository.addCustomer(Customer.create(customerUUID[2], "Ursula", "Eichelberger", LocalDate.of(1991, 2, 15), new Address("Luebecker Strasse", "62", "Seubersdorf", "92358 ", "Germany"), "MichelleEichelberger@rhyta.com", "+499497826628", Gender.FEMALE));
		customerRepository.addCustomer(Customer.create(customerUUID[3], "Erling", "Haaland", LocalDate.of(2000, 5, 28), new Address("Marktplatz", "21", "Dortmund", "44135 ", "Germany"), "Haaland.goat@rhyta.com", "+495863126628", Gender.MALE).addMiddleName("Klaus"));
		customerRepository.addCustomer(Customer.create(customerUUID[4], "Cristiano", "Ronaldo", LocalDate.of(1985, 2, 5), new Address("Oxford Street", "12", "Manchester", "M1", "United Kingdom"), "CristianoRonaldo@rhyta.com", "+449497823332", Gender.MALE));
		customerRepository.addCustomer(Customer.create(customerUUID[5], "Conchita", "Wurst", LocalDate.of(1994, 10, 11), new Address("Wurst Strasse", "3", "Wien", "1010", "Austria"), "Conchita@wurst.com", "+436642135879", Gender.DIVERSE));

		guestRepository.addGuest(Guest.createFromCustomer(guestUUID[0], customerRepository.getCustomerById(customerUUID[0]).get()));
		guestRepository.addGuest(Guest.create(guestUUID[1], "Franziska", "Nachbauer", "+438465184868").addMiddleName("Leonie"));
		
		roomCategoryRepository.addRoomCategory(RoomCategory.createWithoutDescription(categoryUUID[0], "Single Room", 1));
		roomCategoryRepository.addRoomCategory(RoomCategory.createWithoutDescription(categoryUUID[1], "Double Room", 2));
		roomCategoryRepository.addRoomCategory(RoomCategory.createWithoutDescription(categoryUUID[2], "Family Suite", 4));
		
		priceRepository.addPrice(Price.create(categoryUUID[0], new BigDecimal(100.00), LocalDate.now(), LocalDate.now().plusMonths(6)));
		priceRepository.addPrice(Price.create(categoryUUID[1], new BigDecimal(300.00), LocalDate.now(), LocalDate.now().plusMonths(6)));
		priceRepository.addPrice(Price.create(categoryUUID[2], new BigDecimal(800.00), LocalDate.now(), LocalDate.now().plusMonths(6)));
		
		
		bookingRepository.addBooking(Booking.create(
				bookingUUID[0],
				LocalDate.now(),
				LocalDate.now().plusDays(7),
				"5555555555554444",
				"11/10",
				customerUUID[0],
				2,
				BookingStatus.ARRIVED,
				new HashMap<RoomCategory, Integer>(){{put(roomCategoryRepository.getRoomCategoryById(categoryUUID[1]).get(),1);}})); 

		bookingRepository.addBooking(Booking.create(
				bookingUUID[1],
				LocalDate.now(),
				LocalDate.now().plusDays(10),
				"5555555555554444",
				"11/10",
				customerUUID[1],
				6,
				BookingStatus.PAID,
				new HashMap<RoomCategory, Integer>(){{put(roomCategoryRepository.getRoomCategoryById(categoryUUID[2]).get(), 2);
					put(roomCategoryRepository.getRoomCategoryById(categoryUUID[1]).get(), 1);}}));

		bookingRepository.addBooking(Booking.create(
				bookingUUID[2],
				LocalDate.now().plusDays(7),
				LocalDate.now().plusDays(16),
				"5555555555554444",
				"11/10",
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
				"11/21",
				customerUUID[3],
				3,
				BookingStatus.PAID,
				new HashMap<RoomCategory, Integer>(){{put(roomCategoryRepository.getRoomCategoryById(categoryUUID[0]).get(), 3);}}
				));
		
		bookingRepository.addBooking(Booking.create(
				bookingUUID[4],
				LocalDate.now().plusDays(9),
				LocalDate.now().plusDays(10),
				"5555555555554444",
				"11/10",
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
				"11/10",
				customerUUID[5],
				1,
				BookingStatus.PAID,
				new HashMap<RoomCategory, Integer>(){{put(roomCategoryRepository.getRoomCategoryById(categoryUUID[0]).get(), 1);}}
				));
		
		
		stayRepository.addStay(Stay.createForWalkIn(
				stayUUID[0],
				LocalDate.now(),
				LocalDate.now().plusDays(14),
				7,
				"5555555555554444",
				customerUUID[4],
				guestUUID[1])
				);
	
		
		
		stayRepository.addStay(Stay.createFromBooking(stayUUID[1], bookingRepository.getBookingById(bookingUUID[0]).get(), guestUUID[0]));
		roomRepository.addRoom(Room.create(new RoomId("101"), RoomStatus.AVAILABLE, roomCategoryRepository.getRoomCategoryById(categoryUUID[0]).get()));
		roomRepository.addRoom(Room.create(new RoomId("102"), RoomStatus.AVAILABLE, roomCategoryRepository.getRoomCategoryById(categoryUUID[0]).get()));
		roomRepository.addRoom(Room.create(new RoomId("103"), RoomStatus.AVAILABLE, roomCategoryRepository.getRoomCategoryById(categoryUUID[0]).get()));
		roomRepository.addRoom(Room.create(new RoomId("104"), RoomStatus.AVAILABLE, roomCategoryRepository.getRoomCategoryById(categoryUUID[0]).get()));
		roomRepository.addRoom(Room.create(new RoomId("105"), RoomStatus.AVAILABLE, roomCategoryRepository.getRoomCategoryById(categoryUUID[0]).get()));
		roomRepository.addRoom(Room.create(new RoomId("106"), RoomStatus.AVAILABLE, roomCategoryRepository.getRoomCategoryById(categoryUUID[0]).get()));
		roomRepository.addRoom(Room.create(new RoomId("201"), RoomStatus.OCCUPIED, roomCategoryRepository.getRoomCategoryById(categoryUUID[1]).get()));
		roomRepository.addRoom(Room.create(new RoomId("202"), RoomStatus.AVAILABLE, roomCategoryRepository.getRoomCategoryById(categoryUUID[1]).get()));
		roomRepository.addRoom(Room.create(new RoomId("203"), RoomStatus.AVAILABLE, roomCategoryRepository.getRoomCategoryById(categoryUUID[1]).get()));
		roomRepository.addRoom(Room.create(new RoomId("204"), RoomStatus.AVAILABLE, roomCategoryRepository.getRoomCategoryById(categoryUUID[1]).get()));
		roomRepository.addRoom(Room.create(new RoomId("205"), RoomStatus.AVAILABLE, roomCategoryRepository.getRoomCategoryById(categoryUUID[1]).get()));
		roomRepository.addRoom(Room.create(new RoomId("206"), RoomStatus.AVAILABLE, roomCategoryRepository.getRoomCategoryById(categoryUUID[1]).get()));
		roomRepository.addRoom(Room.create(new RoomId("301"), RoomStatus.OCCUPIED, roomCategoryRepository.getRoomCategoryById(categoryUUID[2]).get()));
		roomRepository.addRoom(Room.create(new RoomId("302"), RoomStatus.OCCUPIED, roomCategoryRepository.getRoomCategoryById(categoryUUID[2]).get()));
		roomRepository.addRoom(Room.create(new RoomId("303"), RoomStatus.AVAILABLE, roomCategoryRepository.getRoomCategoryById(categoryUUID[2]).get()));
		roomRepository.addRoom(Room.create(new RoomId("304"), RoomStatus.AVAILABLE, roomCategoryRepository.getRoomCategoryById(categoryUUID[2]).get()));
		roomRepository.addRoom(Room.create(new RoomId("305"), RoomStatus.AVAILABLE, roomCategoryRepository.getRoomCategoryById(categoryUUID[2]).get()));
		roomRepository.addRoom(Room.create(new RoomId("306"), RoomStatus.AVAILABLE, roomCategoryRepository.getRoomCategoryById(categoryUUID[2]).get()));
		
		roomAssignmentRepository.addRoomAssignment(RoomAssignment.create(roomAssignmentUUID[0], new RoomId("301"), stayRepository.getStayById(stayUUID[0]).get()));
		roomAssignmentRepository.addRoomAssignment(RoomAssignment.create(roomAssignmentUUID[1], new RoomId("302"), stayRepository.getStayById(stayUUID[0]).get())); 
		roomAssignmentRepository.addRoomAssignment(RoomAssignment.create(roomAssignmentUUID[2], new RoomId("201"), stayRepository.getStayById(stayUUID[1]).get()));
		
	}

}
