package org.miniats.candidateservice.repository;

import java.sql.SQLException;
import java.util.Optional;

import org.miniats.candidateservice.model.Candidate;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

public interface CandidateRepository extends PagingAndSortingRepository<Candidate, Long>{
	@Retryable(value = SQLException.class, maxAttempts = 2, backoff = @Backoff(delay = 200))
	Optional<Candidate> findByUid(String uid);
	
	@Retryable(value = SQLException.class, maxAttempts = 2, backoff = @Backoff(delay = 200))
	Iterable<Candidate> findAll();
}
