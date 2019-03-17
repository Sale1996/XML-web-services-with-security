package com.tim9.pkiapi.revoked.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim9.pkiapi.revoked.model.RevokedCertificate;

public interface RevokedCertificateRepository extends JpaRepository<RevokedCertificate, Long> {

}
