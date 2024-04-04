package com.axc.web.security;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityUtils {

    /**
     * Get the email of the current user.
     * @return the username of the current user.
     */
    public static Optional<String> getCurrentUserIdentifier() {
        var securityContext = SecurityContextHolder.getContext();

        return Optional.ofNullable(securityContext.getAuthentication())
                .map(authentication -> {
                    if (authentication.getPrincipal() instanceof final Jwt jwt) {
                        var userMetadataClaims = jwt.getClaimAsMap("user_metadata");
                        return (String) userMetadataClaims.get("email");
                    }

                    if (authentication.getPrincipal() instanceof final UserDetails springSecurityUser) {
                        return springSecurityUser.getUsername();
                    }

                    if (authentication.getPrincipal() instanceof final String authenticationPrincipal) {
                        return authenticationPrincipal;
                    }

                    return null;
                });
    }

    /**
     * Check if a user is authenticated.
     * @return true if the user is authed, false otherwise.
     */
    public static boolean isAuthenticated() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated();
    }


}
