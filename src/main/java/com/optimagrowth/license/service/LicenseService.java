package com.optimagrowth.license.service;

import com.optimagrowth.license.config.ServiceConfig;
import com.optimagrowth.license.model.License;
import com.optimagrowth.license.repository.LicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Random;
import java.util.UUID;

@Service
public class LicenseService {

    @Autowired
    MessageSource messageSource;
    @Autowired
    private LicenseRepository licenseRepository;
    @Autowired
    ServiceConfig config;

    public License getLicense(String licenseId, String organizationId){
        License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);
        if(null == license){
            throw new IllegalArgumentException(String.format(messageSource.getMessage(
                    "license.seatch.error.message", null, null
            )));
        }
        return license;
    }

    public License createLicense(License license, String organizationId){
        license.setLicenseId(UUID.randomUUID().toString());
        licenseRepository.save(license);
        return license.witjComment(config.getProperty());
    }

    public License updateLicense(License license, String organizationId){

        licenseRepository.save(license);
        return license.witjComment(config.getProperty());
//        String responseMessage = null;
//        if(license != null){
//            license.setOrganizationId(organizationId);
//            responseMessage = String.format(messageSource.getMessage("license.update.message", null, locale),
//                    license.toString()); }
//        return responseMessage;
    }

    public String deleteLicense(String licenseId, String organizationId){
        String responseMessage = null;
        License license =  new License();
        license.setLicenseId(licenseId);
        licenseRepository.delete(license);
        responseMessage = String.format(messageSource.getMessage("license.delete.message", null, null), licenseId, organizationId);
        return responseMessage;
    }
}
