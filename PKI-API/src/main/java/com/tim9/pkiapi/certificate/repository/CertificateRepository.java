package com.tim9.pkiapi.certificate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim9.pkiapi.certificate.model.Certificate;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {

}
