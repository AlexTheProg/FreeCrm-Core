package com.axc.persistence.jpa.service;

import com.axc.persistence.domain.metabase.DashboardType;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;

@Service
public class MetabaseService {
    private final MetabaseDashboardReferenceService metabaseDashboardReferenceService;
    private final String metabaseSiteUrl;

    private final MACSigner macSigner;


    public MetabaseService(MetabaseDashboardReferenceService metabaseDashboardReferenceService,
                           @Value("${metabase.site-url}") String metabaseSiteUrl,
                           @Value("${metabase.secret-key}") String metabaseSecretKey
    ) {
        this.metabaseDashboardReferenceService = metabaseDashboardReferenceService;
        this.metabaseSiteUrl = metabaseSiteUrl;

        try {
            this.macSigner = new MACSigner(metabaseSecretKey);
        } catch (KeyLengthException e) {
            throw new RuntimeException("Env not configured correctly, rip.", e);
        }
    }

    public String getSignedMetabaseUrl(DashboardType dashboardType) {
        var ref = metabaseDashboardReferenceService.findByDashboardType(dashboardType);

        if (ref == null) {
            throw new RuntimeException("No such dashboard type: " + dashboardType);
        }

        var metabaseDashboardId = Objects.requireNonNull(ref.getId()).getMetabaseDashboardId();

        try {
            var resource = new MetabaseResourceConfig();
            resource.setDashboard(metabaseDashboardId);
            var params = new Object();

            var now = Instant.now();
            var payload = new JWTClaimsSet.Builder()
                    .claim("resource", resource)
                    .claim("params", params)
                    .notBeforeTime(Date.from(now))
                    .expirationTime(Date.from(now.plus(Duration.of(10, ChronoUnit.MINUTES))))
                    .build();
            var signedJwt = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), payload);
            signedJwt.sign(macSigner);

            var encoded = signedJwt.serialize();

            return metabaseSiteUrl + "/embed/dashboard/" + encoded + "/#bordered=false&titled=false&theme=white";
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    @Getter
    @Setter
    static class MetabaseResourceConfig {
        private Long dashboard;
        private Long question;
    }
}
