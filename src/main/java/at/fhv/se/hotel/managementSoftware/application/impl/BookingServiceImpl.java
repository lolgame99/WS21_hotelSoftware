package at.fhv.se.hotel.managementSoftware.application.impl;

import java.time.LocalDate;
import java.util.ArrayList;
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
import at.fhv.se.hotel.managementSoftware.domain.model.Address;
import at.fhv.se.hotel.managementSoftware.domain.model.Booking;
import at.fhv.se.hotel.managementSoftware.domain.model.Customer;
import at.fhv.se.hotel.managementSoftware.domain.repositories.BookingRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.CustomerRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomCategoryRepository;
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
		Optional<Customer> customer = customerRepository.getCustomerById(bookingData.getCustomerId());
		if(customer.isEmpty()) {
			customer = Optional.of(new Customer(
					customerRepository.nextIdentity(),
					bookingData.getFirstName(),
					bookingData.getLastName(),
					LocalDate.of(splitDateString(bookingData.getBirthdate())[0],splitDateString(bookingData.getBirthdate())[1],splitDateString(bookingData.getBirthdate())[2]),
					new Address(bookingData.getStreetName(),bookingData.getStreetNumber(),bookingData.getCity(),bookingData.getPostcode(),bookingData.getCountry()),
					bookingData.getEmail(),
					bookingData.getPhoneNumber(),
					bookingData.getGender()
					));
		}
		
		
		Booking booking =new Booking(
				bookingRepository.nextIdentity(),
				LocalDate.of(splitDateString(bookingData.getCheckInDate())[0],splitDateString(bookingData.getCheckInDate())[1],splitDateString(bookingData.getCheckInDate())[2]),
				LocalDate.of(splitDateString(bookingData.getCheckOutDate())[0],splitDateString(bookingData.getCheckOutDate())[1],splitDateString(bookingData.getCheckOutDate())[2]),
				bookingData.getCreditCardNumber(),
				customer.get(),
				bookingData.getGuestCount(),
				BookingStatus.PENDING
				);
		
		for (int i = 0; i < bookingData.getCategoryValues().size(); i++) {
			booking.addCategory(roomCategoryRepository.getRoomCategoryById(bookingData.getCategoryValues().get(i).toString()).get(), 
					bookingData.getCategoryAmounts().get(i));
		}
		customerRepository.addCustomer(customer.get());
		bookingRepository.addBooking(booking);
		
		
		
	}
	
	/* Splits Date String into Array for further processing
	 * splitArray[0] = year
	 * splitArray[1] = month
	 * splitArray[2] = day
	 */
	private int[] splitDateString(String date) {
		String[] splitStringArray = null;
		int[] splitIntArray = new int[3];
		splitStringArray = date.split("-");
		for (int i = 0; i < splitStringArray.length; i++) {
			splitIntArray[i] = Integer.parseInt(splitStringArray[i]);
		}	
		return splitIntArray;
	}
	
}
