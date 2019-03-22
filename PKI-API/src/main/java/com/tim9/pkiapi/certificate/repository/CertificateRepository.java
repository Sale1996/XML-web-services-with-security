package com.tim9.pkiapi.certificate.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim9.pkiapi.certificate.model.Certificate;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {

	Optional<Certificate> findBySerialNumber(String serialNumber);

}
