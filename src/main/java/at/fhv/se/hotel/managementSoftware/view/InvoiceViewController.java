package at.fhv.se.hotel.managementSoftware.view;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import at.fhv.se.hotel.managementSoftware.application.api.RoomAssignmentService;
import at.fhv.se.hotel.managementSoftware.application.api.StayService;
import at.fhv.se.hotel.managementSoftware.application.dto.RoomAssignmentDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.StayDetailsDTO;

@Controller
public class InvoiceViewController {
	private static final String INVOICE_OVERVIEW_URL = "/invoice";
	private static final String CREATE_INVOICE_URL = "/invoice/create";
	private static final String ERROR_URL = "/error";
	
	private static final String INVOICE_OVERVIEW_VIEW ="intermediaryInvoice";
	private static final String CREATE_INVOICE_VIEW ="createFinalInvoice";
	
	@Autowired
	private RoomAssignmentService roomAssignmentService;
	
	@Autowired
	private StayService stayService;
	
	@GetMapping(INVOICE_OVERVIEW_URL)
	public String invoiceOverview(@RequestParam(value = "stayId", required = true) String id, Model model) {
		List<RoomAssignmentDTO> assignments = roomAssignmentService.getRoomAssignmentsByStayId(id);
		model.addAttribute("assignments",assignments);
		Optional<StayDetailsDTO> stay = stayService.getStayById(id);
		model.addAttribute("stay",stay);
		
		
		return INVOICE_OVERVIEW_VIEW;
	}
	
	@GetMapping(CREATE_INVOICE_URL)
	public String createInvoiceView(Model model) {			
		//Add finished invoice to model
		return CREATE_INVOICE_VIEW;
	}
}

