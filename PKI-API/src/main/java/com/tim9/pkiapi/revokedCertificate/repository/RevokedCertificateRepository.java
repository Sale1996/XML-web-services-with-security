package com.tim9.pkiapi.revokedCertificate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim9.pkiapi.revokedCertificate.model.RevokedCertificate;

public interface RevokedCertificateRepository extends JpaRepository<RevokedCertificate, Long> {

}
