package com.devsupra.hrworker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsupra.hrworker.entities.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Long>  {

}
