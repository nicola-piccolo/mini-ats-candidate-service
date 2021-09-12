package org.miniats.candidateservice.api;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.miniats.candidateservice.model.Candidate;
import org.miniats.candidateservice.repository.CandidateRepository;
import org.miniats.candidateservice.util.UidGenerator;
import org.miniats.candidateservice.util.ValidationErrorUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path="/candidate", produces="application/json")
@CrossOrigin(origins="*")
public class CandidateCrudController {

	@Autowired
	private CandidateRepository candidateRepository;
	
	@GetMapping("/")
	public Iterable<Candidate> getCandidates(){
		return this.candidateRepository.findAll();
	}
	
	@GetMapping("/{uid}")
	public Candidate getCandidateByUid(@PathVariable("uid") String candidateUid) {
		Optional<Candidate> candidateToCheck = this.candidateRepository.findByUid(candidateUid);
		if(candidateToCheck.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidate not found for uid = " + candidateUid);
		}
		return candidateToCheck.get();
	}
	
	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Candidate createCandidate(@Valid @RequestBody Candidate candidate) {
		String candidateUid = UidGenerator.generateUid();
		candidate.setUid(candidateUid);
		return this.candidateRepository.save(candidate);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException exception) {
	    return ValidationErrorUtility.convertIntoErrorMessagesFrom(exception);
	}
}
