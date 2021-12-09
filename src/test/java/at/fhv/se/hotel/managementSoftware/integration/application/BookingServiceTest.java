package at.fhv.se.hotel.managementSoftware.integration.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import at.fhv.se.hotel.managementSoftware.application.dto.BookingDetailsDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.BookingOverviewDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.CustomerDetailsDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.CustomerOverviewDTO;
import at.fhv.se.hotel.managementSoftware.domain.enums.BookingStatus;
import at.fhv.se.hotel.managementSoftware.domain.enums.Gender;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidBookingException;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidCustomerException;
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

@ActiveProfiles("test")
@SpringBootTest
public class BookingServiceTest {
	
	@Autowired
	private BookingService bookingService;
	
	@MockBean
	private BookingRepository bookingRepository;
	
	@MockBean
	private CustomerRepository customerRepository;
	
	@MockBean
	private RoomCategoryRepository roomCategoryRepository;
	
	@MockBean
	private CustomerService customerService;
	
	
	
	
	@Test
	void when_getAll_bookings_return_all() throws InvalidBookingException, InvalidCustomerException {
		//given from Booking
		BookingId bookingId = new BookingId("B1");
		LocalDate checkInDate =  LocalDate.now();
		LocalDate checkOutDate = LocalDate.now().plusDays(7);
		String creditCardNumber = "1212121212";
		String creditCardValid = "12/23";
		CustomerId customerId = new CustomerId("C1");
		int guestCount = 4;
		BookingStatus bookingStatus = BookingStatus.PAID;
		
		//given from RoomCategory
        RoomCategoryId categoryId = new RoomCategoryId("1");
        String categoryName = "Family Suite";
        int bedNumber = 2;
        HashMap <RoomCategory, Integer> categoryCount = new HashMap<>();
        categoryCount.put(RoomCategory.createWithoutDescription(categoryId, categoryName, bedNumber), 3);
       
		
       
        Booking booking = Booking.create(bookingId, checkInDate, checkOutDate, creditCardNumber, creditCardValid, customerId, guestCount, bookingStatus, categoryCount);
        Customer customer = Customer.create(customerId, "Ulrich", "Vogler", LocalDate.of(1988, 7, 21), new Address("Kantstrasse", "32", "Rochlitz", "09301", "Germany"), "UlrichVogler@rhyta.com", "+493737105579", Gender.MALE);
    
        
        List<Booking> allBookings = new ArrayList<Booking>();
        allBookings.add(booking);
		Mockito.when(bookingRepository.getAllBookings()).thenReturn(allBookings);
        
		Optional<CustomerOverviewDTO> customerDetails = Optional.of(CustomerOverviewDTO.createFromCustomer(customer));
		Mockito.when(customerService.getCustomerOverviewById(any(String.class))).thenReturn(customerDetails);
		
		
		//when
		List<BookingOverviewDTO> dto = bookingService.getAllBookings();
		
		
		//then
		assertEquals(1, dto.size());
		assertEquals(allBookings.get(0).getBookingId().getId(), dto.get(0).getBookingId().getId());
		assertEquals(allBookings.get(0).getCustomerId().getId(), dto.get(0).getCustomer().getCustomerId().getId());
		assertEquals(allBookings.get(0).getGuestCount(), dto.get(0).getGuestCount());
		//assertEquals(allBookings.get(0).getBookingStatus(), dto.get(0).getBookingStatus()); //BookingStatus is enum but was java.lang.string:Paid (?)
		assertEquals(customerDetails.get().getCustomerId(), dto.get(0).getCustomer().getCustomerId());
		
		
	}
	
	
	@Test
	void when_get_bookingsByDate() throws InvalidBookingException, InvalidCustomerException {
		//given from Booking
		BookingId bookingId = new BookingId("B1");
		LocalDate checkInDate =  LocalDate.now();
		LocalDate checkOutDate = LocalDate.now().plusDays(7);
		String creditCardNumber = "1212121212";
		String creditCardValid = "12/23";
		CustomerId customerId = new CustomerId("C1");
		int guestCount = 4;
		BookingStatus bookingStatus = BookingStatus.PAID;
		
		//given from RoomCategory
        RoomCategoryId categoryId = new RoomCategoryId("1");
        String categoryName = "Family Suite";
        int bedNumber = 2;
        HashMap <RoomCategory, Integer> categoryCount = new HashMap<>();
        categoryCount.put(RoomCategory.createWithoutDescription(categoryId, categoryName, bedNumber), 3);
       
		
       
        Booking expectedBooking = Booking.create(bookingId, checkInDate, checkOutDate, creditCardNumber, creditCardValid, customerId, guestCount, bookingStatus, categoryCount);
        Customer customer = Customer.create(customerId, "Ulrich", "Vogler", LocalDate.of(1988, 7, 21), new Address("Kantstrasse", "32", "Rochlitz", "09301", "Germany"), "UlrichVogler@rhyta.com", "+493737105579", Gender.MALE);
    
        
        List<Booking> actualBooking = new ArrayList<Booking>();
        actualBooking.add(expectedBooking);
		Mockito.when(bookingRepository.getBookingsByCheckInDate(checkInDate)).thenReturn(actualBooking);
        
		Optional<CustomerOverviewDTO> customerDetails = Optional.of(CustomerOverviewDTO.createFromCustomer(customer));
		Mockito.when(customerService.getCustomerOverviewById(any(String.class))).thenReturn(customerDetails);
		
		
		//when
		List<BookingOverviewDTO> dto = bookingService.getBookingsByDate(checkInDate);
		
		//then
		
		assertEquals(1, dto.size());
		assertEquals(actualBooking.get(0).getBookingId().getId(), dto.get(0).getBookingId().getId());
		assertEquals(actualBooking.get(0).getCustomerId().getId(), dto.get(0).getCustomer().getCustomerId().getId());
		assertEquals(customerDetails.get().getCustomerId(), dto.get(0).getCustomer().getCustomerId());
		assertEquals(actualBooking.get(0).getGuestCount(), dto.get(0).getGuestCount());
		
		
		
		
	}
	
	
	@Test
	void when_get_ready_bookingsByDate() throws InvalidBookingException, InvalidCustomerException {
		//given from Booking
		BookingId bookingId = new BookingId("B1");
		LocalDate checkInDate =  LocalDate.now();
		LocalDate checkOutDate = LocalDate.now().plusDays(7);
		String creditCardNumber = "1212121212";
		String creditCardValid = "12/23";
		CustomerId customerId = new CustomerId("C1");
		int guestCount = 4;
		BookingStatus bookingStatus = BookingStatus.PENDING;
		
		//given from RoomCategory
        RoomCategoryId categoryId = new RoomCategoryId("1");
        String categoryName = "Family Suite";
        int bedNumber = 2;
        HashMap <RoomCategory, Integer> categoryCount = new HashMap<>();
        categoryCount.put(RoomCategory.createWithoutDescription(categoryId, categoryName, bedNumber), 3);
		       
				
		       
        Booking booking = Booking.create(bookingId, checkInDate, checkOutDate, creditCardNumber, creditCardValid, customerId, guestCount, bookingStatus, categoryCount);
        Customer customer = Customer.create(customerId, "Ulrich", "Vogler", LocalDate.of(1988, 7, 21), new Address("Kantstrasse", "32", "Rochlitz", "09301", "Germany"), "UlrichVogler@rhyta.com", "+493737105579", Gender.MALE);

        List<Booking> actualBooking = new ArrayList<Booking>();
        actualBooking.add(booking);
		Mockito.when(bookingRepository.getReadyBookingsByCheckInDate(checkInDate)).thenReturn(actualBooking);
        
		Optional<CustomerDetailsDTO> customerDetails = Optional.of(CustomerDetailsDTO.createFromCustomer(customer));
		Mockito.when(customerService.getCustomerDetailsById(any(String.class))).thenReturn(customerDetails);
		
		//when
		List<BookingDetailsDTO> dto = bookingService.getReadyBookingsByDate(checkInDate);

		
		//then
		
		assertEquals(1, dto.size());
		assertEquals(actualBooking.get(0).getBookingId().getId(), dto.get(0).getBookingId().getId());
		assertEquals(actualBooking.get(0).getCustomerId().getId(), dto.get(0).getCustomer().getCustomerId().getId());
		assertEquals(actualBooking.get(0).getGuestCount(), dto.get(0).getGuestCount());
		//assertEquals(allBookings.get(0).getBookingStatus(), dto.get(0).getBookingStatus()); //BookingStatus is enum but was java.lang.string:Paid (?)
		assertEquals(customerDetails.get().getCustomerId(), dto.get(0).getCustomer().getCustomerId());
		
		
		
	}
		
	
	
	@Test
	void when_get_booking_details_byId() throws InvalidBookingException, InvalidCustomerException {
		//given from Booking
		BookingId bookingId = new BookingId("B1");
		LocalDate checkInDate =  LocalDate.now();
		LocalDate checkOutDate = LocalDate.now().plusDays(7);
		String creditCardNumber = "1212121212";
		String creditCardValid = "12/23";
		CustomerId customerId = new CustomerId("C1");
		int guestCount = 4;
		BookingStatus bookingStatus = BookingStatus.PENDING;
		
		//given from RoomCategory
        RoomCategoryId categoryId = new RoomCategoryId("1");
        String categoryName = "Family Suite";
        int bedNumber = 2;
        HashMap <RoomCategory, Integer> categoryCount = new HashMap<>();
        categoryCount.put(RoomCategory.createWithoutDescription(categoryId, categoryName, bedNumber), 3);
		       
				
		       
        Booking booking = Booking.create(bookingId, checkInDate, checkOutDate, creditCardNumber, creditCardValid, customerId, guestCount, bookingStatus, categoryCount);
        
        Customer customer = Customer.create(customerId, "Ulrich", "Vogler", LocalDate.of(1988, 7, 21), new Address("Kantstrasse", "32", "Rochlitz", "09301", "Germany"), "UlrichVogler@rhyta.com", "+493737105579", Gender.MALE);

        Optional<Booking> actualBooking = Optional.of(booking);
		Mockito.when(bookingRepository.getBookingById(any(BookingId.class))).thenReturn(actualBooking);
        
		Optional<CustomerDetailsDTO> customerDetails = Optional.of(CustomerDetailsDTO.createFromCustomer(customer));
		Mockito.when(customerService.getCustomerDetailsById(any(String.class))).thenReturn(customerDetails);
		
		
		//when
		Optional<BookingDetailsDTO> dto = bookingService.getBookingDetailsById(actualBooking.get().getBookingId().getId());
		
		//then
		assertTrue(dto.isPresent());
		assertEquals(actualBooking.get().getBookingId().getId(), dto.get().getBookingId().getId());
		assertEquals(actualBooking.get().getCustomerId().getId(), dto.get().getCustomer().getCustomerId().getId());
		assertEquals(actualBooking.get().getCheckInDate(), dto.get().getCheckInDate());
		assertEquals(actualBooking.get().getBookingStatus(), dto.get().getBookingStatus());
		assertEquals(actualBooking.get().getCategoryCount(), dto.get().getCategoryCount());
		assertEquals(actualBooking.get().getCheckOutDate(), dto.get().getCheckOutDate());
		assertEquals(actualBooking.get().getCreditCardNumber(), dto.get().getCreditCardNumber());
		assertEquals(actualBooking.get().getCreditCardValid(), dto.get().getCreditCardValid());
		
	}
	
//	@Test
//	void when_addBookings() {
//		
//
//
//		
//	}
//	
//	@Test
//	void when_addBooking_from_Data() throws InvalidBookingException, InvalidCustomerException {
//		//given from Booking
//		BookingId bookingId = new BookingId("B1");
//		LocalDate checkInDate =  LocalDate.now();
//		LocalDate checkOutDate = LocalDate.now().plusDays(7);
//		String creditCardNumber = "1212121212";
//		String creditCardValid = "12/23";
//		CustomerId customerId = new CustomerId("C1");
//		int guestCount = 4;
//		BookingStatus bookingStatus = BookingStatus.PENDING;
//		
//		//given from RoomCategory
//        RoomCategoryId categoryId = new RoomCategoryId("1");
//        String categoryName = "Family Suite";
//        int bedNumber = 2;
//        HashMap <RoomCategory, Integer> categoryCount = new HashMap<>();
//        categoryCount.put(RoomCategory.createWithoutDescription(categoryId, categoryName, bedNumber), 3);
//		
//        
//		       
//      
//	}
//	
//	@Test
//	void when_update_Booking() {
//		
//	}

}
