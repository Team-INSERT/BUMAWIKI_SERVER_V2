package com.project.bumawiki.domain.contribute.domain.repository;

import com.project.bumawiki.domain.contribute.domain.Contribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContributeRepository extends JpaRepository<Contribute, Long> {
}
