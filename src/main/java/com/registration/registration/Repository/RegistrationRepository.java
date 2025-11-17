package com.registration.registration.Repository;

import com.registration.registration.Entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {


}