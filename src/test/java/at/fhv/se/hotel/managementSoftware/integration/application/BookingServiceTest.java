package at.fhv.se.hotel.managementSoftware.integration.application;

//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import at.fhv.se.hotel.managementSoftware.application.api.BookingService;
//import at.fhv.se.hotel.managementSoftware.application.api.CustomerService;
//import at.fhv.se.hotel.managementSoftware.application.dto.BookingOverviewDTO;
//import at.fhv.se.hotel.managementSoftware.domain.enums.BookingStatus;
//import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidBookingException;
//import at.fhv.se.hotel.managementSoftware.domain.model.Booking;
//import at.fhv.se.hotel.managementSoftware.domain.model.BookingId;
//import at.fhv.se.hotel.managementSoftware.domain.model.CustomerId;
//import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategory;
//import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategoryId;
//import at.fhv.se.hotel.managementSoftware.domain.repositories.BookingRepository;
//import at.fhv.se.hotel.managementSoftware.domain.repositories.CustomerRepository;
//import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomCategoryRepository;
//
//
//@SpringBootTest
//public class BookingServiceTest {
//	
//	@Autowired
//	private BookingService bookingService;
//	
//	@MockBean
//	private BookingRepository bookingRepository;
//	
//	@MockBean
//	private CustomerRepository customerRepository;
//	
//	@MockBean
//	private RoomCategoryRepository roomCategoryRepository;
//	
//	@MockBean
//	private CustomerService customerService;
//	
//	
//	
//	
//	@Test
//	void when_getAll_bookings_return_all() throws InvalidBookingException {
//		//given from Booking
//		BookingId bookingId = new BookingId("B1");
//		LocalDate checkInDate =  LocalDate.now();
//		LocalDate checkOutDate = LocalDate.now().plusDays(7);
//		String creditCardNumber = "1212121212";
//		String creditCardValid = "12/23";
//		CustomerId customerId = new CustomerId("C1");
//		int guestCount = 4;
//		BookingStatus bookingStatus = BookingStatus.PAID;
//		
//		//given from RoomCategory
//        RoomCategoryId categoryId = new RoomCategoryId("1");
//        String categoryName = "Family Suite";
//        int bedNumber = 2;
//        HashMap <RoomCategory, Integer> categoryCount = new HashMap<>();
//        categoryCount.put(RoomCategory.createWithoutDescription(categoryId, categoryName, bedNumber), 3);
//        HashMap <RoomCategory, Integer> categoryCount2 = new HashMap<>();
//        categoryCount2.put(RoomCategory.createWithoutDescription(categoryId, categoryName, bedNumber), 3);
//		
//        
//        //when
//        Booking booking = Booking.create(bookingId, checkInDate, checkOutDate, creditCardNumber, creditCardValid, customerId, guestCount, bookingStatus, categoryCount);
//        Booking booking2 = Booking.create(new BookingId("B2"), LocalDate.now().plusDays(2), LocalDate.now().plusDays(3), "1213213131", "10/23", new CustomerId("C2"), 2, BookingStatus.PAID, categoryCount2);
//        List<Booking> allBookings = new ArrayList<Booking>();
//		allBookings.add(booking);
//		allBookings.add(booking2);
//		Mockito.when(bookingRepository.getAllBookings()).thenReturn(allBookings);
//		
//		List<BookingOverviewDTO> dto = bookingService.getAllBookings();
//		
//		
//		//then
//		assertEquals(2, dto.size());
//		assertEquals(allBookings.get(0).getBookingId().getId(), dto.get(0).getBookingId().getId());
//		assertEquals(allBookings.get(0).getBookingStatus(), dto.get(0).getBookingStatus());
//		assertEquals(allBookings.get(0).getCustomerId().getId(), dto.get(0).getCustomer().getCustomerId().getId());
//		assertEquals(allBookings.get(0).getGuestCount(), dto.get(0).getGuestCount());

		
		
//	}
	
	
//	@Test
//	void when_get_bookingsByDate() throws InvalidBookingException {
//
//       
//	}
//	
//	@Test
//	void when_get_ready_bookingsByDate() {
//		
//	}
//	
//	@Test
//	void when_get_booking_details_byId() {
//		
//	}
//	
//	@Test
//	void when_addBookings() {
//		
//	}
//	
//	@Test
//	void when_addBooking_from_Data() {
//		
//	}
//	
//	@Test
//	void when_update_Booking() {
//		
//	}

//}
