package at.fhv.se.hotel.managementSoftware.application.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.fhv.se.hotel.managementSoftware.application.api.BookingAssignmentService;
import at.fhv.se.hotel.managementSoftware.application.api.RoomAssignmentService;
import at.fhv.se.hotel.managementSoftware.application.api.RoomService;
import at.fhv.se.hotel.managementSoftware.application.api.StayService;
import at.fhv.se.hotel.managementSoftware.application.dto.RoomAssignmentDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.RoomDTO;
import at.fhv.se.hotel.managementSoftware.domain.enums.RoomStatus;
import at.fhv.se.hotel.managementSoftware.domain.model.BookingAssignment;
import at.fhv.se.hotel.managementSoftware.domain.model.Room;
import at.fhv.se.hotel.managementSoftware.domain.model.RoomAssignment;
import at.fhv.se.hotel.managementSoftware.domain.model.StayId;
import at.fhv.se.hotel.managementSoftware.domain.repositories.BookingAssignmentRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomAssignmentRepository;
import at.fhv.se.hotel.managementSoftware.domain.repositories.RoomRepository;

@Component
public class BookingAssignmentServiceImpl implements BookingAssignmentService {

	@Autowired
	private BookingAssignmentRepository bookingAssignmentRepository;
	
	@Autowired
	private RoomRepository roomRepository;

	@Override
	public Map<String, Integer> getFreeRoomCountBetweenDates(String fromDate, String toDate) {
		List<BookingAssignment> bookingAssignments = bookingAssignmentRepository.getAllBookingAssignmentsBetweenDates(dateStringConverter(fromDate), dateStringConverter(toDate));
		List<Room> allRooms = roomRepository.getAllRooms();
		Map<String, Integer> result = new HashMap<String, Integer>();
		
		for (Room room : allRooms) {
			if (!result.containsKey(room.getCategory().getCategoryId().getId())) {
				result.put(room.getCategory().getCategoryId().getId(), 0);
			}
		}
		for (Room room : allRooms) {
			result.put(room.getCategory().getCategoryId().getId(), result.get(room.getCategory().getCategoryId().getId())+1);
		}
		for (BookingAssignment ba : bookingAssignments) {
			result.put(ba.getCategory().getCategoryId().getId(), result.get(ba.getCategory().getCategoryId().getId())-1);
		}
		
		
		return result;
	}

	@Override
	public void addBookingAssignment(BookingAssignment ba) {
		bookingAssignmentRepository.addBookingAssignment(ba);	
	}
	
	private LocalDate dateStringConverter(String date) {
		String[] splitStringArray = null;
		int[] splitIntArray = new int[3];
		if (date != null && date != "") {
			splitStringArray = date.split("-");
			for (int i = 0; i < splitStringArray.length; i++) {
				splitIntArray[i] = Integer.parseInt(splitStringArray[i]);
			}
			return LocalDate.of(splitIntArray[0], splitIntArray[1], splitIntArray[2]);
		}else {
			return null;
		}
		
		
	}
}
