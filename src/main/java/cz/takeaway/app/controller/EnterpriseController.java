package cz.takeaway.app.controller;

import java.util.ArrayList;
import java.util.List;

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

import cz.takeaway.app.dto.EnterpriseDto;
import cz.takeaway.app.entity.Enterprise;
import cz.takeaway.app.service.EnterpriseService;
import cz.takeaway.app.transformer.EnterpriseTransformer;


@RestController
@RequestMapping(BasicController.ROOT_V1 + "/enterprise")
@Validated
public class EnterpriseController extends BasicController {

	@Autowired
	private EnterpriseTransformer transformer;
	
	@Autowired
	private EnterpriseService service;

	@GetMapping("/{id}")
	public EnterpriseDto getEnterprise(@PathVariable Long id) {
		EnterpriseDto enterprise = transformer.transform(service.getById(id));
		return enterprise;
	}
	
	@GetMapping("/all")
	public List<EnterpriseDto> getAll() {
		List<EnterpriseDto> enterprises = new ArrayList<>();
		service.getAll().forEach(enterprise -> enterprises.add(transformer.transform(enterprise)));
		return enterprises;
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE )
	@RolesAllowed(value = {"ADMIN", "USER_OWNER"})
	public EnterpriseDto createEnterprise(@RequestBody @Valid EnterpriseDto enterprise) {
		Enterprise entity = service.save(transformer.transform(enterprise));
		return transformer.transform(entity);
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE )
	@RolesAllowed(value = {"ADMIN", "USER_OWNER"})
	public EnterpriseDto updateEnterprise(@RequestBody @Valid EnterpriseDto enterprise) {
		Enterprise entity = service.update(transformer.transform(enterprise));
		return transformer.transform(entity);
	}
	
	
	@DeleteMapping("/{id}")
	@RolesAllowed(value = {"ADMIN", "USER_OWNER"})
	public String deleteEnterprise(@PathVariable Long id) {
		service.delete(id);
		return "Enterprise id: " + id + "was deleted";
	}

}
