package at.fhv.se.hotel.managementSoftware.view;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import at.fhv.se.hotel.managementSoftware.application.api.InvoiceService;
import at.fhv.se.hotel.managementSoftware.application.api.RoomAssignmentService;
import at.fhv.se.hotel.managementSoftware.application.api.StayService;
import at.fhv.se.hotel.managementSoftware.application.dto.InvoiceDetailsDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.RoomAssignmentDTO;
import at.fhv.se.hotel.managementSoftware.application.dto.StayDetailsDTO;
import at.fhv.se.hotel.managementSoftware.domain.model.InvoiceId;
import at.fhv.se.hotel.managementSoftware.view.forms.InvoiceData;
import at.fhv.se.hotel.managementSoftware.view.forms.StayData;

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
	
	@Autowired
	private InvoiceService invoiceService;
	
	@GetMapping(INVOICE_OVERVIEW_URL)
	public String invoiceOverview(@RequestParam(value = "stayId", required = true) String id, Model model) {
		List<RoomAssignmentDTO> assignments = roomAssignmentService.getRoomAssignmentsByStayId(id);
		model.addAttribute("assignments",assignments);
		StayDetailsDTO stay = stayService.getStayById(id).get();
		model.addAttribute("stay",stay);
		InvoiceData form = new InvoiceData();
		form.addInfo(stay);
		model.addAttribute("form", form);
		
		return INVOICE_OVERVIEW_VIEW;
	}
	
	@GetMapping(CREATE_INVOICE_URL)
	public String createInvoiceGet(@RequestParam(value = "invoiceId", required = true) String id, Model model, HttpServletRequest request) {
		InvoiceDetailsDTO invoice = invoiceService.getInvoiceByInvoiceId(id).get();
		model.addAttribute("invoice",invoice);
		return CREATE_INVOICE_VIEW;
	}

	
	@PostMapping(CREATE_INVOICE_URL)
	public ModelAndView createInvoicePost(@ModelAttribute InvoiceData form, Model model, HttpServletRequest request) {
		InvoiceId id = null;
		try {
			form.validate();
			 id = invoiceService.addInvoiceFromData(form);
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			System.out.println(e.getMessage());
			return new ModelAndView("forward:"+ERROR_URL);
		}
		return new ModelAndView("redirect:" + CREATE_INVOICE_URL+"?invoiceId="+id.getId());
	}
}

