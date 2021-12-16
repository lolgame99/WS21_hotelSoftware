package at.fhv.se.hotel.managementSoftware.integration.application;

import static org.junit.jupiter.api.Assertions.assertAll;
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

import at.fhv.se.hotel.managementSoftware.application.api.CustomerService;
import at.fhv.se.hotel.managementSoftware.application.api.InvoiceLineService;
import at.fhv.se.hotel.managementSoftware.application.api.InvoiceService;
import at.fhv.se.hotel.managementSoftware.application.dto.BookingDetailsDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.CustomerDetailsDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.GuestDetailsDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.InvoiceDetailsDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.InvoiceLineDetailsDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.PriceDetailsDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.RoomAssignmentDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.RoomCategoryDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.RoomDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.StayDetailsDTO;
import at.fhv.se.hotel.managementSoftware.domain.enums.BookingStatus;
import at.fhv.se.hotel.managementSoftware.domain.enums.Gender;
import at.fhv.se.hotel.managementSoftware.domain.enums.PaymentType;
import at.fhv.se.hotel.managementSoftware.domain.enums.RoomStatus;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidBookingException;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidCustomerException;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidInvoiceException;
import at.fhv.se.hotel.managementSoftware.domain.model.IndividualCustomer;
import at.fhv.se.hotel.managementSoftware.domain.model.Booking;
import at.fhv.se.hotel.managementSoftware.domain.model.BookingId;
import at.fhv.se.hotel.managementSoftware.domain.model.Customer;
import at.fhv.se.hotel.managementSoftware.domain.model.CustomerId;
import at.fhv.se.hotel.managementSoftware.domain.model.Guest;
import at.fhv.se.hotel.managementSoftware.domain.model.GuestId;
import at.fhv.se.hotel.managementSoftware.domain.model.Invoice;
import at.fhv.se.hotel.managementSoftware.domain.model.InvoiceId;
import at.fhv.se.hotel.managementSoftware.domain.model.InvoiceLine;
import at.fhv.se.hotel.managementSoftware.domain.model.Price;
import at.fhv.se.hotel.managementSoftware.domain.model.Room;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomAssignment;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomAssignmentId;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategory;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategoryId;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomId;
import at.fhv.se.hotel.managementSoftware.domain.model.Stay;
import at.fhv.se.hotel.managementSoftware.domain.model.StayId;
import at.fhv.se.hotel.managementSoftware.domain.repositories.CustomerRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.InvoiceRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomAssignmentRepository;
import at.fhv.se.hotel.managementSoftware.domain.valueObjects.Address;
import at.fhv.se.hotel.managementSoftware.domain.valueObjects.InvoiceCustomer;
import at.fhv.se.hotel.managementSoftware.view.forms.InvoiceData;

@ActiveProfiles("test")
@SpringBootTest
public class InvoiceServiceTest {
	
	@Autowired
	private InvoiceService invoiceService;
	
	@MockBean
	private InvoiceRepository invoiceRepository;
	
	@MockBean
	private InvoiceLineService invoiceLineService;
	
	@MockBean
	private RoomAssignmentRepository roomAssignmentRepository;
	
	@MockBean
	private CustomerRepository customerRepository;
	
	@Test
	void when_getAll_invoices_return_all() throws InvalidCustomerException {
		//given
		IndividualCustomer customer = IndividualCustomer.create(new CustomerId("1"), "Test", "Customer", LocalDate.of(1988, 7, 21), new Address("Kantstrasse", "32", "Rochlitz", "09301", "Germany"), "TestCustomer@gmail.com", "+493737105579", Gender.MALE);
		InvoiceCustomer invoiceCustomer = new InvoiceCustomer(customer);
		List<Invoice> allInvoices = new ArrayList<Invoice>();
		allInvoices.add(Invoice.create(new InvoiceId("1"), LocalDate.now(), new BigDecimal(700.00), PaymentType.CASH, invoiceCustomer, new StayId("1")));
		Mockito.when(invoiceRepository.getAllInvoices()).thenReturn(allInvoices);
		
		List<InvoiceLineDetailsDTO> lineDTOs = new ArrayList<InvoiceLineDetailsDTO>();
		lineDTOs.add(InvoiceLineDetailsDTO.createsFromInvoiceLine(InvoiceLine.create(new InvoiceId("1"), 1, "Single Room", "One single room", new BigDecimal (150.00))));
		lineDTOs.add(InvoiceLineDetailsDTO.createsFromInvoiceLine(InvoiceLine.create(new InvoiceId("1"), 1, "Single Room", "One single room", new BigDecimal (150.00))));
		Mockito.when(invoiceLineService.getAllInvoiceLinesByInvoiceId(any(String.class))).thenReturn(lineDTOs);
			
		//when
		List<InvoiceDetailsDTO> dtos = invoiceService.getAllInvoices();
		
		//then
		assertEquals(1, dtos.size());
		assertEquals(2, dtos.get(0).getInvoiceLines().size());
		assertEquals(allInvoices.get(0).getCustomer().getCustomerId().getId(), dtos.get(0).getCustomer().getCustomerId().getId());
		assertEquals(allInvoices.get(0).getDate(), dtos.get(0).getDate());
		assertEquals(allInvoices.get(0).getInvoiceId().getId(), dtos.get(0).getInvoiceId().getId());
		assertEquals(allInvoices.get(0).getStayId().getId(), dtos.get(0).getStayId().getId());
		assertEquals(allInvoices.get(0).getPaymentType(), dtos.get(0).getPaymentType());
		assertEquals(allInvoices.get(0).getSum(), dtos.get(0).getSum());
	}
	
	@Test
	void when_given_customerId_return_customer_invoices() throws InvalidCustomerException {
		//given
		IndividualCustomer customer = IndividualCustomer.create(new CustomerId("1"), "Test", "Customer", LocalDate.of(1988, 7, 21), new Address("Kantstrasse", "32", "Rochlitz", "09301", "Germany"), "TestCustomer@gmail.com", "+493737105579", Gender.MALE);
		InvoiceCustomer invoiceCustomer = new InvoiceCustomer(customer);
		List<Invoice> allInvoices = new ArrayList<Invoice>();
		allInvoices.add(Invoice.create(new InvoiceId("1"), LocalDate.now(), new BigDecimal(700.00), PaymentType.CASH, invoiceCustomer, new StayId("1")));
		Mockito.when(invoiceRepository.getInvoicesByCustomerId(any(CustomerId.class))).thenReturn(allInvoices);
		
		List<InvoiceLineDetailsDTO> lineDTOs = new ArrayList<InvoiceLineDetailsDTO>();
		lineDTOs.add(InvoiceLineDetailsDTO.createsFromInvoiceLine(InvoiceLine.create(new InvoiceId("1"), 1, "Single Room", "One single room", new BigDecimal (150.00))));
		lineDTOs.add(InvoiceLineDetailsDTO.createsFromInvoiceLine(InvoiceLine.create(new InvoiceId("1"), 1, "Single Room", "One single room", new BigDecimal (150.00))));
		Mockito.when(invoiceLineService.getAllInvoiceLinesByInvoiceId(any(String.class))).thenReturn(lineDTOs);
		
		//when
		List<InvoiceDetailsDTO> dtos = invoiceService.getInvoicesByCustomerId(customer.getCustomerId().getId());
		
		//then
		assertEquals(1, dtos.size());
		assertEquals(2, dtos.get(0).getInvoiceLines().size());
		assertEquals(allInvoices.get(0).getCustomer().getCustomerId().getId(), dtos.get(0).getCustomer().getCustomerId().getId());
		assertEquals(allInvoices.get(0).getDate(), dtos.get(0).getDate());
		assertEquals(allInvoices.get(0).getInvoiceId().getId(), dtos.get(0).getInvoiceId().getId());
		assertEquals(allInvoices.get(0).getStayId().getId(), dtos.get(0).getStayId().getId());
		assertEquals(allInvoices.get(0).getPaymentType(), dtos.get(0).getPaymentType());
		assertEquals(allInvoices.get(0).getSum(), dtos.get(0).getSum());
	}
	
	@Test
	void when_given_stayId_return_stay_invoices() throws InvalidCustomerException {
		//given
		IndividualCustomer customer = IndividualCustomer.create(new CustomerId("1"), "Test", "Customer", LocalDate.of(1988, 7, 21), new Address("Kantstrasse", "32", "Rochlitz", "09301", "Germany"), "TestCustomer@gmail.com", "+493737105579", Gender.MALE);
		InvoiceCustomer invoiceCustomer = new InvoiceCustomer(customer);
		List<Invoice> allInvoices = new ArrayList<Invoice>();
		allInvoices.add(Invoice.create(new InvoiceId("1"), LocalDate.now(), new BigDecimal(700.00), PaymentType.CASH, invoiceCustomer, new StayId("1")));
		Mockito.when(invoiceRepository.getInvoicesByStayId(any(StayId.class))).thenReturn(allInvoices);
		
		List<InvoiceLineDetailsDTO> lineDTOs = new ArrayList<InvoiceLineDetailsDTO>();
		lineDTOs.add(InvoiceLineDetailsDTO.createsFromInvoiceLine(InvoiceLine.create(new InvoiceId("1"), 1, "Single Room", "One single room", new BigDecimal (150.00))));
		lineDTOs.add(InvoiceLineDetailsDTO.createsFromInvoiceLine(InvoiceLine.create(new InvoiceId("1"), 1, "Single Room", "One single room", new BigDecimal (150.00))));
		Mockito.when(invoiceLineService.getAllInvoiceLinesByInvoiceId(any(String.class))).thenReturn(lineDTOs);
		
		//when
		List<InvoiceDetailsDTO> dtos = invoiceService.getInvoicesByStayId(allInvoices.get(0).getStayId().getId());
		
		//then
		assertEquals(1, dtos.size());
		assertEquals(2, dtos.get(0).getInvoiceLines().size());
		assertEquals(allInvoices.get(0).getCustomer().getCustomerId().getId(), dtos.get(0).getCustomer().getCustomerId().getId());
		assertEquals(allInvoices.get(0).getDate(), dtos.get(0).getDate());
		assertEquals(allInvoices.get(0).getInvoiceId().getId(), dtos.get(0).getInvoiceId().getId());
		assertEquals(allInvoices.get(0).getStayId().getId(), dtos.get(0).getStayId().getId());
		assertEquals(allInvoices.get(0).getPaymentType(), dtos.get(0).getPaymentType());
		assertEquals(allInvoices.get(0).getSum(), dtos.get(0).getSum());
	}
	
	@Test
	void when_given_invoiceId_return_invoice() throws InvalidCustomerException {
		//given
		IndividualCustomer customer = IndividualCustomer.create(new CustomerId("1"), "Test", "Customer", LocalDate.of(1988, 7, 21), new Address("Kantstrasse", "32", "Rochlitz", "09301", "Germany"), "TestCustomer@gmail.com", "+493737105579", Gender.MALE);
		InvoiceCustomer invoiceCustomer = new InvoiceCustomer(customer);
		Optional<Invoice> invoice = Optional.of(Invoice.create(new InvoiceId("1"), LocalDate.now(), new BigDecimal(700.00), PaymentType.CASH, invoiceCustomer, new StayId("1")));
		Mockito.when(invoiceRepository.getInvoiceByInvoiceId(any(InvoiceId.class))).thenReturn(invoice);
		
		List<InvoiceLineDetailsDTO> lineDTOs = new ArrayList<InvoiceLineDetailsDTO>();
		lineDTOs.add(InvoiceLineDetailsDTO.createsFromInvoiceLine(InvoiceLine.create(new InvoiceId("1"), 1, "Single Room", "One single room", new BigDecimal (150.00))));
		lineDTOs.add(InvoiceLineDetailsDTO.createsFromInvoiceLine(InvoiceLine.create(new InvoiceId("1"), 1, "Single Room", "One single room", new BigDecimal (150.00))));
		Mockito.when(invoiceLineService.getAllInvoiceLinesByInvoiceId(any(String.class))).thenReturn(lineDTOs);
		
		//when
		Optional<InvoiceDetailsDTO> dto = invoiceService.getInvoiceByInvoiceId(invoice.get().getInvoiceId().getId());
		
		//then
		assertTrue(dto.isPresent());
		assertEquals(2, dto.get().getInvoiceLines().size());
		assertEquals(invoice.get().getCustomer().getCustomerId().getId(), dto.get().getCustomer().getCustomerId().getId());
		assertEquals(invoice.get().getDate(), dto.get().getDate());
		assertEquals(invoice.get().getInvoiceId().getId(), dto.get().getInvoiceId().getId());
		assertEquals(invoice.get().getStayId().getId(), dto.get().getStayId().getId());
		assertEquals(invoice.get().getPaymentType(), dto.get().getPaymentType());
		assertEquals(invoice.get().getSum(), dto.get().getSum());
	}
	
	@Test	void when_given_invoiceData_create_invoice() throws Exception {
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
        List<RoomAssignmentDTO> roomAssignmentDTOs = new ArrayList<RoomAssignmentDTO>();
        roomAssignmentDTOs.add(RoomAssignmentDTO.createFromRoomAssignment(roomAssignment, RoomDTO.createFromRoom(room, RoomCategoryDTO.createFromCategory(cat, PriceDetailsDTO.createFromPrice(price)))));
        
		InvoiceData data = new InvoiceData();
		data.addInfo(StayDetailsDTO.createFromStay(stay, Optional.of(BookingDetailsDTO.createFromBooking(booking, CustomerDetailsDTO.createFromCustomer(customer))), CustomerDetailsDTO.createFromCustomer(customer), GuestDetailsDTO.createFromGuest(guest), roomAssignmentDTOs));
		
		ArrayList<Boolean> selectedPositions = new ArrayList<Boolean>();
		selectedPositions.add(true);
		data.setToPay(selectedPositions);
		
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<String> descriptions = new ArrayList<String>();
		ArrayList<String> assignmentIds = new ArrayList<String>();
		ArrayList<BigDecimal> prices = new ArrayList<BigDecimal>();
		names.add("Testpositions");
		descriptions.add("Testposition Description");
		prices.add(BigDecimal.valueOf(100));
		assignmentIds.add(roomAssignment.getRoomAssignmentId().getId());
		
		data.setDescriptions(descriptions);
		data.setNames(names);
		data.setPrices(prices);
		data.setAssignmentIds(assignmentIds);
		data.setPaymentType(PaymentType.CASH);
		
		Mockito.when(roomAssignmentRepository.getRoomAssignmentsById(any(RoomAssignmentId.class))).thenReturn(Optional.of(roomAssignment));
		Mockito.when(customerRepository.getCustomerById(any(CustomerId.class))).thenReturn(Optional.of(customer));
		
		data.validate();
		
		//when.. then
		assertDoesNotThrow(() -> invoiceService.addInvoiceFromData(data));
		
	}
}
