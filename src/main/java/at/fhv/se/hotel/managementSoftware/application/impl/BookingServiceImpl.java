package at.fhv.se.hotel.managementSoftware.application.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.management.loading.PrivateClassLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.fhv.se.hotel.managementSoftware.application.api.BookingService;
import at.fhv.se.hotel.managementSoftware.application.dto.BookingOverviewDTO;
import at.fhv.se.hotel.managementSoftware.domain.enums.BookingStatus;
import at.fhv.se.hotel.managementSoftware.domain.exceptions.InvalidBookingException;
import at.fhv.se.hotel.managementSoftware.domain.model.Booking;
import at.fhv.se.hotel.managementSoftware.domain.model.Customer;
import at.fhv.se.hotel.managementSoftware.domain.model.CustomerId;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategory;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomCategoryId;
import at.fhv.se.hotel.managementSoftware.domain.repositories.BookingRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.CustomerRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomCategoryRepository;
import at.fhv.se.hotel.managementSoftware.domain.valueObjects.Address;
import at.fhv.se.hotel.managementSoftware.view.forms.BookingData;

@Component
public class BookingServiceImpl implements BookingService{
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private RoomCategoryRepository roomCategoryRepository;

	@Override
	public List<BookingOverviewDTO> getAllBookings() {
		List<Booking> bookings = bookingRepository.getAllBookings();
		List<BookingOverviewDTO> bookingDTOs= new ArrayList<BookingOverviewDTO>();
		
		for (Booking b : bookings) {
			int totalRoomCount = 0;
			for(int categoryCount : b.getCategoryCount().values()) {
				totalRoomCount += categoryCount;
			}
			
			bookingDTOs.add(BookingOverviewDTO.builder()
					.withId(b.getBookingId())
					.withCheckInDate(b.getCheckInDate())
					.withCustomer(b.getCustomer())
					.withGuestCount(b.getGuestCount())
					.withRoomCount(totalRoomCount)
					.build()
					);
		}
		
		return bookingDTOs;
	}

	@Override
	public List<BookingOverviewDTO> getBookingsByDate(LocalDate date) {
		List<Booking> bookings = bookingRepository.getBookingsByCheckInDate(date);
		List<BookingOverviewDTO> bookingDTOs= new ArrayList<BookingOverviewDTO>();
		
		for (Booking b : bookings) {
			int totalRoomCount = 0;
			for(int categoryCount : b.getCategoryCount().values()) {
				totalRoomCount += categoryCount;
			}
			
			bookingDTOs.add(BookingOverviewDTO.builder()
					.withId(b.getBookingId())
					.withCheckInDate(b.getCheckInDate())
					.withCustomer(b.getCustomer())
					.withGuestCount(b.getGuestCount())
					.withRoomCount(totalRoomCount)
					.build()
					);
		}
		
		return bookingDTOs;
	}

	@Override
	public void addBooking(Booking booking) {
		bookingRepository.addBooking(booking);
	}

	@Override
	public void addBookingFromData(BookingData bookingData) throws InvalidBookingException {
		Optional<Customer> customer = customerRepository.getCustomerById(new CustomerId(bookingData.getCustomerId()));
		Boolean customerCreated = customer.isEmpty();
		if(customerCreated) {
			customer = Optional.of(new Customer(
					customerRepository.nextIdentity(),
					bookingData.getFirstName(),
					bookingData.getLastName(),
					dateStringConverter(bookingData.getBirthdate()),
					new Address(bookingData.getStreetName(),bookingData.getStreetNumber(),bookingData.getCity(),bookingData.getPostcode(),bookingData.getCountry()),
					bookingData.getEmail(),
					bookingData.getPhoneNumber(),
					bookingData.getGender()
					));
		}
		
		HashMap<RoomCategory, Integer> categoryCount = new HashMap<RoomCategory, Integer>();
		for (int i = 0; i < bookingData.getCategoryValues().size(); i++) {
			if(categoryCount.containsKey(roomCategoryRepository.getRoomCategoryById(new RoomCategoryId(bookingData.getCategoryValues().get(i))).get())) {
				throw new InvalidBookingException("Booking could not be created <br> The same category can't be selected more than once");
			}
			categoryCount.put(
					roomCategoryRepository.getRoomCategoryById(new RoomCategoryId(bookingData.getCategoryValues().get(i))).get(), 
					bookingData.getCategoryAmounts().get(i));
		}
		Booking booking = new Booking(
				bookingRepository.nextIdentity(),
				dateStringConverter(bookingData.getCheckInDate()),
				dateStringConverter(bookingData.getCheckOutDate()),
				bookingData.getCreditCardNumber(),
				customer.get(),
				bookingData.getGuestCount(),
				BookingStatus.PENDING,
				categoryCount);
		
		
		if (customerCreated) {
			customerRepository.addCustomer(customer.get());
		}
		bookingRepository.addBooking(booking);
		
		
		
	}
	
	/* Splits Date String into Array for further processing
	 * splitArray[0] = year
	 * splitArray[1] = month
	 * splitArray[2] = day
	 */
	public LocalDate dateStringConverter(String date) {
		String[] splitStringArray = null;
		int[] splitIntArray = new int[3];
		splitStringArray = date.split("-");
		for (int i = 0; i < splitStringArray.length; i++) {
			splitIntArray[i] = Integer.parseInt(splitStringArray[i]);
		}	
		return LocalDate.of(splitIntArray[0], splitIntArray[1], splitIntArray[2]);
	}
	
}
