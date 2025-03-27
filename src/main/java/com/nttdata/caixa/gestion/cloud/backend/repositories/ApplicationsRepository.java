package com.nttdata.caixa.gestion.cloud.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.caixa.gestion.cloud.backend.entities.Applications;

@Repository
public interface ApplicationsRepository extends JpaRepository<Applications, Long> {

}
