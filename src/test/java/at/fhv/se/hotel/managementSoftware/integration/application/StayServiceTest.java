package at.fhv.se.hotel.managementSoftware.integration.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

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
import at.fhv.se.hotel.managementSoftware.application.dto.StayDetailsDTO;
import at.fhv.se.hotel.managementSoftware.domain.enums.BookingStatus;
import at.fhv.se.hotel.managementSoftware.domain.enums.Gender;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidBookingException;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidCustomerException;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidStayException;
import at.fhv.se.hotel.managementSoftware.domain.model.Booking;
import at.fhv.se.hotel.managementSoftware.domain.model.BookingId;
import at.fhv.se.hotel.managementSoftware.domain.model.Customer;
import at.fhv.se.hotel.managementSoftware.domain.model.CustomerId;
import at.fhv.se.hotel.managementSoftware.domain.model.Guest;
import at.fhv.se.hotel.managementSoftware.domain.model.GuestId;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategory;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategoryId;
import at.fhv.se.hotel.managementSoftware.domain.model.Stay;
import at.fhv.se.hotel.managementSoftware.domain.model.StayId;
import at.fhv.se.hotel.managementSoftware.domain.repositories.BookingRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.CustomerRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.GuestRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomAssignmentRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.StayRepository;
import at.fhv.se.hotel.managementSoftware.domain.valueObjects.Address;

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
	
	
	
//	@Test
//	void when_getAll_stays_return_all() throws InvalidStayException, InvalidBookingException, InvalidCustomerException {
//		//given
//		StayId stayId = new StayId("123");
//		LocalDate checkInDate = LocalDate.now();
//		LocalDate checkOutDate = LocalDate.now().plusDays(7);
//		int guestCount = 3;
//		String creditCardNumber = "0201133211";
//		BookingId bookingId = new BookingId("B12");
//		CustomerId customerId = new CustomerId("122");
//		GuestId guestId = new GuestId("133");
//		
//		//given from RoomCategory
//        RoomCategoryId categoryId = new RoomCategoryId("1");
//        String categoryName = "Family Suite";
//        int bedNumber = 2;
//        HashMap <RoomCategory, Integer> categoryCount = new HashMap<>();
//        categoryCount.put(RoomCategory.createWithoutDescription(categoryId, categoryName, bedNumber), 3);
//		
//		Stay stay = Stay.createForWalkIn(stayId, checkInDate, checkOutDate, guestCount, creditCardNumber, customerId, guestId);
//        Booking booking = Booking.create(bookingId, checkInDate, checkOutDate, creditCardNumber, "11/22" , customerId, guestCount, BookingStatus.PAID, categoryCount);
//        Customer customer = Customer.create(customerId, "Ulrich", "Vogler", LocalDate.of(1988, 7, 21), new Address("Kantstrasse", "32", "Rochlitz", "09301", "Germany"), "UlrichVogler@rhyta.com", "+493737105579", Gender.MALE);
//        Guest guest = Guest.create(guestId, "Johnny" , "Muster", "43546846546");
//		
//		List<Stay> allStays = new ArrayList<Stay>();
//		allStays.add(stay);
//		Mockito.when(stayRepository.getAllStays()).thenReturn(allStays);
//		
//		CustomerDetailsDTO customerDetails = CustomerDetailsDTO.createFromCustomer(customer);
////		Mockito.when(customerService.getCustomerDetailsById(any(String.class))).thenReturn(customerDetails);
//		
//		Optional<BookingDetailsDTO> bookingDetails = Optional.of(BookingDetailsDTO.createFromBooking(booking, customerDetails)); //könnte falsch sein
//		Mockito.when(bookingService.getBookingDetailsById(any(String.class))).thenReturn(bookingDetails);
//		
//		GuestDetailsDTO guestDetails = GuestDetailsDTO.createFromGuest(guest);
////		Mockito.when(guestService.getGuestById(any(String.class))).thenReturn(guestDetails);
//		
//		
//		//when
//		List<StayDetailsDTO> dto = stayService.getAllStays();
//		
//		//then
//	//	assertEquals(1, dto.size());
//	
//	}
	
	
	
	
}
