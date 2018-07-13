package de.rieckpil.learning.javaocr;

import lombok.Data;

@Data
public class JwtApiKey {

    private String issuer;
    private String issuedAt;
    private String expiresAt;
    private String notBefore;
    private String jsonWebToken;

}
