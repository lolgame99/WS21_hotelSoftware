package at.fhv.se.hotel.managementSoftware.integration.application;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import at.fhv.se.hotel.managementSoftware.application.api.BookingService;
import at.fhv.se.hotel.managementSoftware.application.api.CustomerService;
import at.fhv.se.hotel.managementSoftware.application.api.GuestService;
import at.fhv.se.hotel.managementSoftware.application.api.RoomAssignmentService;
import at.fhv.se.hotel.managementSoftware.application.api.RoomService;
import at.fhv.se.hotel.managementSoftware.application.api.StayService;
import at.fhv.se.hotel.managementSoftware.application.dto.BookingDetailsDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.CustomerDetailsDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.GuestDetailsDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.PriceDetailsDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.RoomAssignmentDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.RoomCategoryDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.RoomDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.StayDetailsDTO;
import at.fhv.se.hotel.managementSoftware.domain.enums.BookingStatus;
import at.fhv.se.hotel.managementSoftware.domain.enums.Gender;
import at.fhv.se.hotel.managementSoftware.domain.enums.RoomStatus;
import at.fhv.se.hotel.managementSoftware.domain.enums.StayStatus;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidBookingException;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidCustomerException;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidStayException;
import at.fhv.se.hotel.managementSoftware.domain.model.Booking;
import at.fhv.se.hotel.managementSoftware.domain.model.BookingId;
import at.fhv.se.hotel.managementSoftware.domain.model.Customer;
import at.fhv.se.hotel.managementSoftware.domain.model.CustomerId;
import at.fhv.se.hotel.managementSoftware.domain.model.Guest;
import at.fhv.se.hotel.managementSoftware.domain.model.GuestId;
import at.fhv.se.hotel.managementSoftware.domain.model.IndividualCustomer;
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
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomAssignmentRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.StayRepository;
import at.fhv.se.hotel.managementSoftware.domain.valueObjects.Address;
import at.fhv.se.hotel.managementSoftware.view.forms.StayData;

@ActiveProfiles("test")
@SpringBootTest
public class StayServiceTest {

	@Autowired
	private StayService stayService;
	
	@MockBean
	private StayRepository stayRepository;
	
	@MockBean
	private CustomerService customerService;
	
	@MockBean
	private BookingService bookingService;
	
	@MockBean
	private GuestService guestService;
	
	@MockBean
	private CustomerRepository customerRepository;
	
	@MockBean
	private BookingRepository bookingRepository;
	
	@MockBean
	private GuestRepository guestRepository;
	
	@MockBean
	private RoomAssignmentRepository roomAssignmentRepository;
		
	@MockBean
	private RoomRepository roomRepository;
	
	@MockBean
	private RoomService roomService;
	
	@MockBean
	private RoomAssignmentService roomAssignmentService;
	
	
	
	@Test
	void when_getAll_stays_return_all() throws InvalidStayException, InvalidBookingException, InvalidCustomerException {
		//given
		StayId stayId = new StayId("123");
		LocalDate checkInDate = LocalDate.now();
		LocalDate checkOutDate = LocalDate.now().plusDays(7);
		int guestCount = 3;
		String creditCardNumber = "0201133211";
		BookingId bookingId = new BookingId("B12");
		CustomerId customerId = new CustomerId("122");
		GuestId guestId = new GuestId("133");
		
		//given from RoomCategory
        RoomCategoryId categoryId = new RoomCategoryId("1");
        String categoryName = "Family Suite";
        int bedNumber = 2;
        HashMap <RoomCategory, Integer> categoryCount = new HashMap<>();
        categoryCount.put(RoomCategory.createWithoutDescription(categoryId, categoryName, bedNumber), 3);
		
        RoomAssignmentId roomAssignmentId = new RoomAssignmentId("1");
        RoomId roomId = new RoomId("AA");
        
        Booking booking = Booking.create(bookingId, checkInDate, checkOutDate, creditCardNumber, "11/22" , customerId, guestCount, BookingStatus.PAID, categoryCount);
		Stay stay = Stay.createFromBooking(stayId, booking, guestId);
        
        Customer customer = IndividualCustomer.create(customerId, "Ulrich", "Vogler", LocalDate.of(1988, 7, 21), new Address("Kantstrasse", "32", "Rochlitz", "09301", "Germany"), "UlrichVogler@rhyta.com", "+493737105579", Gender.MALE);
        Guest guest = Guest.create(guestId, "Johnny" , "Muster", "43546846546");
		RoomCategory cat = RoomCategory.createWithDescription(categoryId, categoryName, bedNumber, categoryName);
        Room room = Room.create(roomId, RoomStatus.AVAILABLE, cat);
        Price price = Price.create(categoryId, new BigDecimal("250"), checkInDate, checkOutDate);
        RoomAssignment roomAssignment = RoomAssignment.create(roomAssignmentId, roomId, stay);
        
		
		PriceDetailsDTO priceDTO = PriceDetailsDTO.createFromPrice(price);
		RoomCategoryDTO roomCatDTO = RoomCategoryDTO.createFromCategory(cat, priceDTO);
		RoomDTO roomDTO = RoomDTO.createFromRoom(room, roomCatDTO);

		CustomerDetailsDTO customerDetails = CustomerDetailsDTO.createFromCustomer(customer);
		//GuestDetailsDTO guestDetails = GuestDetailsDTO.createFromGuest(guest);
		
		Mockito.when(customerService.getCustomerDetailsById(any(String.class))).thenReturn(Optional.of(customerDetails));
		
		Optional<GuestDetailsDTO> guestDetailsDTO = Optional.of(GuestDetailsDTO.createFromGuest(guest));
		Mockito.when(guestService.getGuestById(any(String.class))).thenReturn(guestDetailsDTO);
		
		List<Stay> allStays = new ArrayList<Stay>();
		allStays.add(stay);
		Mockito.when(stayRepository.getAllStays()).thenReturn(allStays);
		
		Optional<BookingDetailsDTO> bookingDetails = Optional.of(BookingDetailsDTO.createFromBooking(booking, customerDetails));
		Mockito.when(bookingService.getBookingDetailsById(any(String.class))).thenReturn(bookingDetails);

		List<RoomAssignmentDTO> roomAssignmentDTO = new ArrayList<>();
		roomAssignmentDTO.add(RoomAssignmentDTO.createFromRoomAssignment(roomAssignment, roomDTO));
		Mockito.when(roomAssignmentService.getRoomAssignmentsByStayId(any(String.class))).thenReturn(roomAssignmentDTO);
		
		
		//when
		List<StayDetailsDTO> dto = stayService.getAllStays();
		
		//then
		assertEquals(1, dto.size());
		assertEquals(allStays.get(0).getStayId().getId(), dto.get(0).getStayId().getId());
		assertEquals(allStays.get(0).getBookingId().getId(), dto.get(0).getBooking().get().getBookingId().getId());
	}
	
	@Test
	void when_getCurrentStays() throws InvalidStayException, InvalidBookingException, InvalidCustomerException{
		//given
		StayId stayId = new StayId("123");
		LocalDate checkInDate = LocalDate.now();
		LocalDate checkOutDate = LocalDate.now().plusDays(7);
		int guestCount = 3;
		String creditCardNumber = "0201133211";
		BookingId bookingId = new BookingId("B12");
		CustomerId customerId = new CustomerId("122");
		GuestId guestId = new GuestId("133");
		
		//given from RoomCategory
        RoomCategoryId categoryId = new RoomCategoryId("1");
        String categoryName = "Family Suite";
        int bedNumber = 2;
        HashMap <RoomCategory, Integer> categoryCount = new HashMap<>();
        categoryCount.put(RoomCategory.createWithoutDescription(categoryId, categoryName, bedNumber), 3);
		
        RoomAssignmentId roomAssignmentId = new RoomAssignmentId("1");
        RoomId roomId = new RoomId("AA");
        Booking booking = Booking.create(bookingId, checkInDate, checkOutDate, creditCardNumber, "11/22" , customerId, guestCount, BookingStatus.PAID, categoryCount);
		Stay stay = Stay.createFromBooking(stayId, booking, guestId);
        
        Customer customer = IndividualCustomer.create(customerId, "Ulrich", "Vogler", LocalDate.of(1988, 7, 21), new Address("Kantstrasse", "32", "Rochlitz", "09301", "Germany"), "UlrichVogler@rhyta.com", "+493737105579", Gender.MALE);
        Guest guest = Guest.create(guestId, "Johnny" , "Muster", "43546846546");
		RoomCategory cat = RoomCategory.createWithDescription(categoryId, categoryName, bedNumber, categoryName);
        Room room = Room.create(roomId, RoomStatus.AVAILABLE, cat);
        Price price = Price.create(categoryId, new BigDecimal("250"), checkInDate, checkOutDate);
        RoomAssignment roomAssignment = RoomAssignment.create(roomAssignmentId, roomId, stay);
        
        
        PriceDetailsDTO priceDTO = PriceDetailsDTO.createFromPrice(price);
		RoomCategoryDTO roomCatDTO = RoomCategoryDTO.createFromCategory(cat, priceDTO);
		RoomDTO roomDTO = RoomDTO.createFromRoom(room, roomCatDTO);
        
        CustomerDetailsDTO customerDetails = CustomerDetailsDTO.createFromCustomer(customer);
		
        Mockito.when(customerService.getCustomerDetailsById(any(String.class))).thenReturn(Optional.of(customerDetails));
		
		Optional<GuestDetailsDTO> guestDetailsDTO = Optional.of(GuestDetailsDTO.createFromGuest(guest));
		Mockito.when(guestService.getGuestById(any(String.class))).thenReturn(guestDetailsDTO);
        
        List<Stay> currentStays = new ArrayList<>();
        currentStays.add(stay);
        Mockito.when(stayRepository.getCurrentStays(checkInDate)).thenReturn(currentStays);
        
        Optional<BookingDetailsDTO> bookingDetails = Optional.of(BookingDetailsDTO.createFromBooking(booking, customerDetails));
		Mockito.when(bookingService.getBookingDetailsById(any(String.class))).thenReturn(bookingDetails);

		List<RoomAssignmentDTO> roomAssignmentDTO = new ArrayList<>();
		roomAssignmentDTO.add(RoomAssignmentDTO.createFromRoomAssignment(roomAssignment, roomDTO));
		Mockito.when(roomAssignmentService.getRoomAssignmentsByStayId(any(String.class))).thenReturn(roomAssignmentDTO);
		
        
        //when
        List<StayDetailsDTO> dto = stayService.getCurrentStays(checkInDate);
        
        
        assertEquals(1, dto.size());
	}
	
	@Test
	void when_getStay_by_Id() throws InvalidStayException, InvalidBookingException, InvalidCustomerException {
		//given
		StayId stayId = new StayId("123");
		LocalDate checkInDate = LocalDate.now();
		LocalDate checkOutDate = LocalDate.now().plusDays(7);
		int guestCount = 3;
		String creditCardNumber = "0201133211";
		BookingId bookingId = new BookingId("B12");
		CustomerId customerId = new CustomerId("122");
		GuestId guestId = new GuestId("133");
		
		//given from RoomCategory
        RoomCategoryId categoryId = new RoomCategoryId("1");
        String categoryName = "Family Suite";
        int bedNumber = 2;
        HashMap <RoomCategory, Integer> categoryCount = new HashMap<>();
        categoryCount.put(RoomCategory.createWithoutDescription(categoryId, categoryName, bedNumber), 3);
		
        RoomAssignmentId roomAssignmentId = new RoomAssignmentId("1");
        RoomId roomId = new RoomId("AA");
        Booking booking = Booking.create(bookingId, checkInDate, checkOutDate, creditCardNumber, "11/22" , customerId, guestCount, BookingStatus.PAID, categoryCount);
		Stay stay = Stay.createFromBooking(stayId, booking, guestId);
        
        Customer customer = IndividualCustomer.create(customerId, "Ulrich", "Vogler", LocalDate.of(1988, 7, 21), new Address("Kantstrasse", "32", "Rochlitz", "09301", "Germany"), "UlrichVogler@rhyta.com", "+493737105579", Gender.MALE);
        Guest guest = Guest.create(guestId, "Johnny" , "Muster", "43546846546");
		RoomCategory cat = RoomCategory.createWithDescription(categoryId, categoryName, bedNumber, categoryName);
        Room room = Room.create(roomId, RoomStatus.AVAILABLE, cat);
        Price price = Price.create(categoryId, new BigDecimal("250"), checkInDate, checkOutDate);
        RoomAssignment roomAssignment = RoomAssignment.create(roomAssignmentId, roomId, stay);
        
        
        PriceDetailsDTO priceDTO = PriceDetailsDTO.createFromPrice(price);
		RoomCategoryDTO roomCatDTO = RoomCategoryDTO.createFromCategory(cat, priceDTO);
		RoomDTO roomDTO = RoomDTO.createFromRoom(room, roomCatDTO);
        
        CustomerDetailsDTO customerDetails = CustomerDetailsDTO.createFromCustomer(customer);
		
        Mockito.when(customerService.getCustomerDetailsById(any(String.class))).thenReturn(Optional.of(customerDetails));
		
		Optional<GuestDetailsDTO> guestDetailsDTO = Optional.of(GuestDetailsDTO.createFromGuest(guest));
		Mockito.when(guestService.getGuestById(any(String.class))).thenReturn(guestDetailsDTO);
        
        Optional<Stay> currentStays = Optional.of(Stay.createFromBooking(stayId, booking, guestId));
        Mockito.when(stayRepository.getStayById(any(StayId.class))).thenReturn(currentStays);
        
        Optional<BookingDetailsDTO> bookingDetails = Optional.of(BookingDetailsDTO.createFromBooking(booking, customerDetails));
		Mockito.when(bookingService.getBookingDetailsById(any(String.class))).thenReturn(bookingDetails);

		List<RoomAssignmentDTO> roomAssignmentDTO = new ArrayList<>();
		roomAssignmentDTO.add(RoomAssignmentDTO.createFromRoomAssignment(roomAssignment, roomDTO));
		Mockito.when(roomAssignmentService.getRoomAssignmentsByStayId(any(String.class))).thenReturn(roomAssignmentDTO);
		
        
        //when
        Optional<StayDetailsDTO> dto = stayService.getStayById(currentStays.get().getStayId().getId());
		
        //then
        assertTrue(dto.isPresent());
        assertEquals(currentStays.get().getStayId().getId(), dto.get().getStayId().getId());
        assertEquals(currentStays.get().getBookingId().getId(), dto.get().getBooking().get().getBookingId().getId());
		assertEquals(currentStays.get().getCheckInDate(), dto.get().getCheckInDate());
		assertEquals(currentStays.get().getCheckOutDate(), dto.get().getCheckOutDate());
		assertEquals(currentStays.get().getCustomerId().getId(), dto.get().getCustomer().getCustomerId().getId());
		
		
		
	}
	

	@Test
	void when_add_stay_from_data() throws Exception {
		//given
		StayData data = new StayData();
		StayId stayId = new StayId("123");
		LocalDate checkInDate = LocalDate.now();
		LocalDate checkOutDate = LocalDate.now().plusDays(7);
		int guestCount = 3;
		String creditCardNumber = "0201133211";
		BookingId bookingId = new BookingId("B12");
		CustomerId customerId = new CustomerId("122");
		GuestId guestId = new GuestId("133");
		
		//given from RoomCategory
        RoomCategoryId categoryId = new RoomCategoryId("1");
        String categoryName = "Family Suite";
        int bedNumber = 2;
        HashMap <RoomCategory, Integer> categoryCount = new HashMap<>();
        categoryCount.put(RoomCategory.createWithoutDescription(categoryId, categoryName, bedNumber), 3);
		
        Booking booking = Booking.create(bookingId, checkInDate, checkOutDate, creditCardNumber, "11/22" , customerId, guestCount, BookingStatus.PAID, categoryCount);
		Stay stay = Stay.createFromBooking(stayId, booking, guestId);
        Guest guest = Guest.create(guestId, "Johnny" , "Muster", "43546846546");

        Customer customer = IndividualCustomer.create(customerId, "Ulrich", "Vogler", LocalDate.of(1988, 7, 21), new Address("Kantstrasse", "32", "Rochlitz", "09301", "Germany"), "UlrichVogler@rhyta.com", "+493737105579", Gender.MALE);
        data.addExistingBooking(BookingDetailsDTO.createFromBooking(booking, CustomerDetailsDTO.createFromCustomer(customer)));
        
        data.setGuest("newGuest");
        List<String> list = new ArrayList<>();
        list.add("106");
        data.setRoomNumbers(list);
        
        Mockito.when(stayRepository.getStayById(any(StayId.class))).thenReturn(Optional.of(stay));
        Mockito.when(bookingRepository.getBookingById(any(BookingId.class))).thenReturn(Optional.of(booking));
        Mockito.when(customerService.getCustomerDetailsById(any(String.class))).thenReturn(Optional.of(CustomerDetailsDTO.createFromCustomer(customer)));
        Mockito.when(guestRepository.getGuestById(any(GuestId.class))).thenReturn(Optional.of(guest));
        Mockito.when(guestService.getGuestById(any(String.class))).thenReturn(Optional.of(GuestDetailsDTO.createFromGuest(guest)));
        
        
        
        Optional<Room> room = Optional.of(Room.create(new RoomId("106"), RoomStatus.AVAILABLE, RoomCategory.createWithoutDescription(categoryId, categoryName, bedNumber)));
        Mockito.when(roomRepository.getRoomByNumber(any(RoomId.class))).thenReturn(room);
   
        
        
        //when...then
       
        assertDoesNotThrow(() -> stayService.addStayFromData(data, checkInDate, checkOutDate, LocalDate.of(1988, 7, 21)));
       
        
	}
	

	
	
	@Test
	void when_checkOut_Stay() throws Exception {
		//given
		StayId stayId = new StayId("123");
		LocalDate checkInDate = LocalDate.now();
		LocalDate checkOutDate = LocalDate.now().plusDays(7);
		int guestCount = 3;
		String creditCardNumber = "0201133211";
		BookingId bookingId = new BookingId("B12");
		CustomerId customerId = new CustomerId("122");
		GuestId guestId = new GuestId("133");
		
		//given from RoomCategory
        RoomCategoryId categoryId = new RoomCategoryId("1");
        String categoryName = "Family Suite";
        int bedNumber = 2;
        HashMap <RoomCategory, Integer> categoryCount = new HashMap<>();
        categoryCount.put(RoomCategory.createWithoutDescription(categoryId, categoryName, bedNumber), 3);
		
        RoomAssignmentId roomAssignmentId = new RoomAssignmentId("1");
        RoomId roomId = new RoomId("AA");
        Booking booking = Booking.create(bookingId, checkInDate, checkOutDate, creditCardNumber, "11/22" , customerId, guestCount, BookingStatus.PAID, categoryCount);
		Stay stay = Stay.createFromBooking(stayId, booking, guestId);
        
        Customer customer = IndividualCustomer.create(customerId, "Ulrich", "Vogler", LocalDate.of(1988, 7, 21), new Address("Kantstrasse", "32", "Rochlitz", "09301", "Germany"), "UlrichVogler@rhyta.com", "+493737105579", Gender.MALE);
        Guest guest = Guest.create(guestId, "Johnny" , "Muster", "43546846546");
		RoomCategory cat = RoomCategory.createWithDescription(categoryId, categoryName, bedNumber, categoryName);
        Room room = Room.create(roomId, RoomStatus.AVAILABLE, cat);
        Price price = Price.create(categoryId, new BigDecimal("250"), checkInDate, checkOutDate);
        
        
        
        PriceDetailsDTO priceDTO = PriceDetailsDTO.createFromPrice(price);
		RoomCategoryDTO roomCatDTO = RoomCategoryDTO.createFromCategory(cat, priceDTO);
		RoomDTO roomDTO = RoomDTO.createFromRoom(room, roomCatDTO);
        
        CustomerDetailsDTO customerDetails = CustomerDetailsDTO.createFromCustomer(customer);
		
        Mockito.when(customerService.getCustomerDetailsById(any(String.class))).thenReturn(Optional.of(customerDetails));
		
		Optional<GuestDetailsDTO> guestDetailsDTO = Optional.of(GuestDetailsDTO.createFromGuest(guest));
		Mockito.when(guestService.getGuestById(any(String.class))).thenReturn(guestDetailsDTO);
        
        Optional<Stay> currentStays = Optional.of(Stay.createFromBooking(stayId, booking, guestId));
        Mockito.when(stayRepository.getStayById(any(StayId.class))).thenReturn(currentStays);
        
        Optional<BookingDetailsDTO> bookingDetails = Optional.of(BookingDetailsDTO.createFromBooking(booking, customerDetails));
		Mockito.when(bookingService.getBookingDetailsById(any(String.class))).thenReturn(bookingDetails);

		
		List<RoomAssignment> roomAssignment = new ArrayList<>();
		RoomAssignment roomAssignments = RoomAssignment.create(roomAssignmentId, roomId, stay);
		roomAssignments.paid();
		roomAssignment.add(roomAssignments);
		Mockito.when(roomAssignmentRepository.getRoomAssignmentsByStayId(any(StayId.class))).thenReturn(roomAssignment);
		Mockito.when(roomRepository.getRoomByNumber(any(RoomId.class))).thenReturn(Optional.of(room));
		
		//when
		stayService.checkoutStay(stayId.getId());
		stay.checkout();
		
		
		//then
		assertEquals(StayStatus.CHECKEDOUT, stay.getStatus());
	}

}
