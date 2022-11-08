package cz.takeaway.app.controller;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cz.takeaway.app.entity.Calculation;
import cz.takeaway.app.service.CalculationService;

@RestController
@RequestMapping(BasicController.ROOT_V1 + "/calculation")
@Validated
public class CalculationController {

	
	@Autowired
	private CalculationService service;

	@GetMapping("/{id}")
	@RolesAllowed(value = {"ADMIN", "USER", "USER_OWNER"})
	public Calculation getCalculation(@PathVariable Long id) {
		Calculation enterprise = service.getById(id);
		return enterprise;
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE )
	@RolesAllowed(value = {"ADMIN", "USER", "USER_OWNER"})
	public Calculation createCalculation(@RequestBody @Valid Calculation calculation) {
		return service.save(calculation);
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE )
	@RolesAllowed(value = {"ADMIN", "USER", "USER_OWNER"})
	public Calculation updateCalculation(@RequestBody @Valid Calculation calculation) {
		return service.update(calculation);
	}
	
	
	@DeleteMapping("/{id}")
	@RolesAllowed(value = {"ADMIN", "USER", "USER_OWNER"})
	public String deleteCalculation(@PathVariable Long id) {
	    service.delete(id);
		return "Calculation id: " + id + "was deleted";
	}
}
