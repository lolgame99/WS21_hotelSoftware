package at.fhv.se.hotel.managementSoftware.integration.application;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import at.fhv.se.hotel.managementSoftware.domain.model.CompanyCustomer;
import at.fhv.se.hotel.managementSoftware.domain.model.Customer;
import at.fhv.se.hotel.managementSoftware.domain.model.CustomerId;
import at.fhv.se.hotel.managementSoftware.domain.model.IndividualCustomer;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategory;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategoryId;
import at.fhv.se.hotel.managementSoftware.domain.repositories.BookingRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.CustomerRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomCategoryRepository;
import at.fhv.se.hotel.managementSoftware.domain.valueObjects.Address;
import at.fhv.se.hotel.managementSoftware.view.forms.BookingData;

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
	void when_addNewBooking() throws Exception{
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
 
		
		//when...then
		bookingService.addBooking(booking);
		
	
	}
	
	
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
        Customer customer = IndividualCustomer.create(customerId, "Ulrich", "Vogler", LocalDate.of(1988, 7, 21), new Address("Kantstrasse", "32", "Rochlitz", "09301", "Germany"), "UlrichVogler@rhyta.com", "+493737105579", Gender.MALE);
    
        
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
		assertEquals(allBookings.get(0).getBookingStatus(), dto.get(0).getBookingStatus());
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
        Customer customer = IndividualCustomer.create(customerId, "Ulrich", "Vogler", LocalDate.of(1988, 7, 21), new Address("Kantstrasse", "32", "Rochlitz", "09301", "Germany"), "UlrichVogler@rhyta.com", "+493737105579", Gender.MALE);
    
        
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
        Customer customer = IndividualCustomer.create(customerId, "Ulrich", "Vogler", LocalDate.of(1988, 7, 21), new Address("Kantstrasse", "32", "Rochlitz", "09301", "Germany"), "UlrichVogler@rhyta.com", "+493737105579", Gender.MALE);

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
		assertEquals(actualBooking.get(0).getBookingStatus(), dto.get(0).getBookingStatus());
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
        
        Customer customer = IndividualCustomer.create(customerId, "Ulrich", "Vogler", LocalDate.of(1988, 7, 21), new Address("Kantstrasse", "32", "Rochlitz", "09301", "Germany"), "UlrichVogler@rhyta.com", "+493737105579", Gender.MALE);

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
	
	@Test
	void when_get_booking_details_byId_return_empty() {
		//given
		String bookingId = "1123";
		
		//when
		Optional<BookingDetailsDTO> dto = bookingService.getBookingDetailsById(bookingId);
		
		//then
		assertTrue(dto.isEmpty());
	}

	@Test
	void when_add_booking_from_data_IndividualCustomer() throws Exception {
		//given Booking IndividualCustomer
		BookingData data = new BookingData();
		BookingId bookingId = new BookingId("B1");
		LocalDate checkInDate =  LocalDate.now();
		LocalDate checkOutDate = LocalDate.now().plusDays(7);
		String creditCardNumber = "1212121212";
		String creditCardValid = "12/23";
		CustomerId customerId = new CustomerId("C1");
		int guestCount = 4;
		BookingStatus bookingStatus = BookingStatus.PENDING;
		
		//given RoomCategory IndividualCustomer
        RoomCategoryId categoryId = new RoomCategoryId("1");
        String categoryName = "Family Suite";
        int bedNumber = 2;
        RoomCategory category = RoomCategory.createWithoutDescription(categoryId, categoryName, bedNumber);
        HashMap <RoomCategory, Integer> categoryCount = new HashMap<>();
        categoryCount.put(category, 3);
        
        
		       
        Booking booking = Booking.create(bookingId, checkInDate, checkOutDate, creditCardNumber, creditCardValid, customerId, guestCount, bookingStatus, categoryCount);
        Customer customer = IndividualCustomer.create(customerId, "Ulrich", "Vogler", LocalDate.of(1988, 7, 21), new Address("Kantstrasse", "32", "Rochlitz", "09301", "Germany"), "UlrichVogler@rhyta.com", "+493737105579", Gender.MALE);
        
       
        data.addExistingBooking(BookingDetailsDTO.createFromBooking(booking, CustomerDetailsDTO.createFromCustomer(customer)));
        
        Mockito.when(roomCategoryRepository.getRoomCategoryById(any(RoomCategoryId.class))).thenReturn(Optional.of(category));
        Mockito.when(bookingRepository.getBookingById(any(BookingId.class))).thenReturn(Optional.of(booking));
        Mockito.when(customerService.getCustomerDetailsById(any(String.class))).thenReturn(Optional.of(CustomerDetailsDTO.createFromCustomer(customer)));

        //when...IndividualCustomer
        bookingService.addBookingFromData(data, checkInDate, checkOutDate, LocalDate.of(1988, 7, 21));
        Optional<BookingDetailsDTO> dto = bookingService.getBookingDetailsById(bookingId.getId());
       
     
        //then...IndividualCustomer
  		assertTrue(dto.isPresent());
  		assertEquals(booking.getBookingId().getId(), dto.get().getBookingId().getId());
  		assertEquals(booking.getCustomerId().getId(), dto.get().getCustomer().getCustomerId().getId());
  		assertEquals(booking.getCheckInDate(), dto.get().getCheckInDate());
  		assertEquals(booking.getBookingStatus(), dto.get().getBookingStatus());
  		assertEquals(booking.getCategoryCount(), dto.get().getCategoryCount());
  		assertEquals(booking.getCheckOutDate(), dto.get().getCheckOutDate());
  		assertEquals(booking.getCreditCardNumber(), dto.get().getCreditCardNumber());
  		assertEquals(booking.getCreditCardValid(), dto.get().getCreditCardValid());
 		assertDoesNotThrow(() -> bookingService.addBookingFromData(data, checkInDate, checkOutDate, LocalDate.of(1988, 7, 21)));
 		
	}
	
	@Test
	void when_addBookingFromData_CompanyCustomer() throws Exception {
		//given Booking CompanyCustomer
        BookingData companyData = new BookingData();
        BookingId companyBookingId = new BookingId("B2");
        LocalDate companyCheckInDate = LocalDate.now();
        LocalDate companyCheckOutDate = LocalDate.now().plusDays(10);
        String companyCreditCardNumber = "1000102020";
        String companyCreditCardValid = "11/24";
		int companyGuestCount = 3;
		BookingStatus companyBookingStatus = BookingStatus.PENDING;

        //given CompanyCustomer
        CustomerId companyCustomerId = new CustomerId("C2");
        String companyName = "Firma Muster AG";
        Address companyAddress = new Address("Musterstrasse", "21", "Dornbirn", "6850", "Austria");
        String companyEmail = "MusterAG@muster.at";
        String companyPhoneNumber = "+4366054862056";
        BigDecimal discountRate = new BigDecimal(10);
        
        //given RoomCategory CompanyCustomer
        RoomCategoryId companyCategoryId = new RoomCategoryId("1");
        String companyCategoryName = "Family Suite";
        int companyBedNumber = 2;
        RoomCategory companyCategory = RoomCategory.createWithoutDescription(companyCategoryId, companyCategoryName, companyBedNumber);
        HashMap <RoomCategory, Integer> companyCategoryCount = new HashMap<>();
        companyCategoryCount.put(companyCategory, 3);
  		
        Booking companyBooking = Booking.create(companyBookingId, companyCheckInDate, companyCheckOutDate, companyCreditCardNumber, companyCreditCardValid, companyCustomerId, companyGuestCount, companyBookingStatus, companyCategoryCount);
        Customer companyCustomer = CompanyCustomer.create(companyCustomerId, companyName, companyAddress, companyEmail, companyPhoneNumber, discountRate);
        companyData.addExistingBooking(BookingDetailsDTO.createFromBooking(companyBooking, CustomerDetailsDTO.createFromCustomer(companyCustomer)));
       
        
        
        Mockito.when(roomCategoryRepository.getRoomCategoryById(any(RoomCategoryId.class))).thenReturn(Optional.of(companyCategory));
        Mockito.when(bookingRepository.getBookingById(any(BookingId.class))).thenReturn(Optional.of(companyBooking));
        Mockito.when(customerService.getCustomerDetailsById(any(String.class))).thenReturn(Optional.of(CustomerDetailsDTO.createFromCustomer(companyCustomer)));
        //Mockito.doThrow(InvalidBookingException.class).when(roomCategoryRepository).getRoomCategoryById(any(RoomCategoryId.class));
        //when...CompanyCustomer
        bookingService.addBookingFromData(companyData, companyCheckInDate, companyCheckOutDate, LocalDate.of(1989, 7, 21));
        Optional<BookingDetailsDTO> dto2 = bookingService.getBookingDetailsById(companyBookingId.getId());
  		
        
        //then...CompanyCustomer
  		assertTrue(dto2.isPresent());
 		assertEquals(companyBooking.getBookingId().getId(), dto2.get().getBookingId().getId());
 		assertEquals(companyBooking.getCustomerId().getId(), dto2.get().getCustomer().getCustomerId().getId());
 		assertDoesNotThrow(() -> bookingService.addBookingFromData(companyData, companyCheckInDate, companyCheckOutDate, LocalDate.of(1989, 7, 21)));
 		
        
 		
	}
	
	@Test
	void when_roomCategory_is_not_selected() throws Exception {
		//given Booking IndividualCustomer
		BookingData data = new BookingData();
		BookingId bookingId = new BookingId("B1");
		LocalDate checkInDate =  LocalDate.now();
		LocalDate checkOutDate = LocalDate.now().plusDays(7);
		String creditCardNumber = "1212121212";
		String creditCardValid = "12/23";
		CustomerId customerId = new CustomerId("C1");
		int guestCount = 1;
		BookingStatus bookingStatus = BookingStatus.PENDING;
		
		//given RoomCategory IndividualCustomer
        RoomCategoryId categoryId = new RoomCategoryId("1");
        String categoryName = "Family Suite";
        int bedNumber = 1;
        RoomCategory category = RoomCategory.createWithoutDescription(categoryId, categoryName, bedNumber);
        HashMap <RoomCategory, Integer> categoryCount = new HashMap<>();
        categoryCount.put(category, 1);
        
       
        Mockito.when(roomCategoryRepository.getRoomCategoryById(any(RoomCategoryId.class))).thenReturn(Optional.empty());

        Booking booking = Booking.create(bookingId, checkInDate, checkOutDate, creditCardNumber, creditCardValid, customerId, guestCount, bookingStatus, categoryCount);
        Customer customer = IndividualCustomer.create(customerId, "Ulrich", "Vogler", LocalDate.of(1988, 7, 21), new Address("Kantstrasse", "32", "Rochlitz", "09301", "Germany"), "UlrichVogler@rhyta.com", "+493737105579", Gender.MALE);
        
        categoryCount.clear();
        data.addExistingBooking(BookingDetailsDTO.createFromBooking(booking, CustomerDetailsDTO.createFromCustomer(customer)));
       
        //Mockito.when(roomCategoryRepository.getRoomCategoryById(any(RoomCategoryId.class))).thenReturn(Optional.of(category));
        Mockito.when(roomCategoryRepository.getRoomCategoryById(any(RoomCategoryId.class))).thenReturn(Optional.empty());
        Mockito.when(bookingRepository.getBookingById(any(BookingId.class))).thenReturn(Optional.of(booking));
        Mockito.when(customerService.getCustomerDetailsById(any(String.class))).thenReturn(Optional.of(CustomerDetailsDTO.createFromCustomer(customer)));
        

     
        //then...IndividualCustomer
        
		assertThrows(InvalidBookingException.class, () -> bookingService.addBookingFromData(data, checkInDate, checkOutDate, LocalDate.of(1988, 7, 21)));
	}
	
	@Test
	void when_same_roomCategory_is_selected_more_than_once() throws Exception{
		//given Booking IndividualCustomer
		BookingData data = new BookingData();
		BookingId bookingId = new BookingId("B1");
		LocalDate checkInDate =  LocalDate.now();
		LocalDate checkOutDate = LocalDate.now().plusDays(7);
		String creditCardNumber = "1212121212";
		String creditCardValid = "12/23";
		CustomerId customerId = new CustomerId("C1");
		int guestCount = 1;
		BookingStatus bookingStatus = BookingStatus.PENDING;
		
		//given RoomCategory IndividualCustomer
        RoomCategoryId categoryId = new RoomCategoryId("1");
        String categoryName = "Family Suite";
        int bedNumber = 2;
        RoomCategory category = RoomCategory.createWithoutDescription(categoryId, categoryName, bedNumber);
        RoomCategory category2 = RoomCategory.createWithoutDescription(categoryId, categoryName, bedNumber);
        HashMap <RoomCategory, Integer> categoryCount = new HashMap<>();
        categoryCount.put(category, 1);
        categoryCount.put(category2, 1);
        
          
        Booking booking = Booking.create(bookingId, checkInDate, checkOutDate, creditCardNumber, creditCardValid, customerId, guestCount, bookingStatus, categoryCount);
        Customer customer = IndividualCustomer.create(customerId, "Ulrich", "Vogler", LocalDate.of(1988, 7, 21), new Address("Kantstrasse", "32", "Rochlitz", "09301", "Germany"), "UlrichVogler@rhyta.com", "+493737105579", Gender.MALE);
        
       
        data.addExistingBooking(BookingDetailsDTO.createFromBooking(booking, CustomerDetailsDTO.createFromCustomer(customer)));
        
        Mockito.when(roomCategoryRepository.getRoomCategoryById(any(RoomCategoryId.class))).thenReturn(Optional.of(category));
        Mockito.when(roomCategoryRepository.getRoomCategoryById(any(RoomCategoryId.class))).thenReturn(Optional.of(category2));
        Mockito.when(bookingRepository.getBookingById(any(BookingId.class))).thenReturn(Optional.of(booking));
        Mockito.when(customerService.getCustomerDetailsById(any(String.class))).thenReturn(Optional.of(CustomerDetailsDTO.createFromCustomer(customer)));
        
        
        //when...IndividualCustomer
        
       
       
     
        //then...IndividualCustomer
  		
		assertThrows(InvalidBookingException.class, () -> bookingService.addBookingFromData(data, checkInDate, checkOutDate, LocalDate.of(1988, 7, 21)));
	
	}
	
	@Test
	void when_updateBooking() throws Exception {
		//given Booking IndividualCustomer
		BookingData data = new BookingData();
		BookingId bookingId = new BookingId("B1");
		LocalDate checkInDate =  LocalDate.now();
		LocalDate checkOutDate = LocalDate.now().plusDays(7);
		String creditCardNumber = "1212121212";
		String creditCardValid = "12/23";
		CustomerId customerId = new CustomerId("C1");
		int guestCount = 4;
		BookingStatus bookingStatus = BookingStatus.PENDING;
		
		//given RoomCategory IndividualCustomer
        RoomCategoryId categoryId = new RoomCategoryId("1");
        String categoryName = "Family Suite";
        int bedNumber = 2;
        RoomCategory category = RoomCategory.createWithoutDescription(categoryId, categoryName, bedNumber);
        HashMap <RoomCategory, Integer> categoryCount = new HashMap<>();
        categoryCount.put(category, 3);
        
        
		       
        Booking booking = Booking.create(bookingId, checkInDate, checkOutDate, creditCardNumber, creditCardValid, customerId, guestCount, bookingStatus, categoryCount);
        Customer customer = IndividualCustomer.create(customerId, "Ulrich", "Vogler", LocalDate.of(1988, 7, 21), new Address("Kantstrasse", "32", "Rochlitz", "09301", "Germany"), "UlrichVogler@rhyta.com", "+493737105579", Gender.MALE);
        
       
        data.addExistingBooking(BookingDetailsDTO.createFromBooking(booking, CustomerDetailsDTO.createFromCustomer(customer)));
        
        Mockito.when(roomCategoryRepository.getRoomCategoryById(any(RoomCategoryId.class))).thenReturn(Optional.of(category));
        Mockito.when(bookingRepository.getBookingById(any(BookingId.class))).thenReturn(Optional.of(booking));
        Mockito.when(customerService.getCustomerDetailsById(any(String.class))).thenReturn(Optional.of(CustomerDetailsDTO.createFromCustomer(customer)));

        bookingService.addBookingFromData(data, checkInDate, checkOutDate, LocalDate.of(1988, 7, 21)); 
        
        //when...then
        assertDoesNotThrow(() -> bookingService.updateBooking(data, checkInDate, checkOutDate, LocalDate.of(1988, 7, 21)));
        
	}
	
}
