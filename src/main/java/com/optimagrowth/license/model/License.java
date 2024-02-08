package com.optimagrowth.license.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Builder
@Entity
@Table(name = "licenses")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class License extends RepresentationModel<License> {
    @Id
    @Column(name = "license_id", nullable = false)
    private String licenseId;
    private String description;

    @Column(name = "organization_id", nullable = false)
    private String organizationId;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "license_type", nullable = false)
    private String licenseType;

    @Column(name = "comment")
    private String comment;

    public License witjComment(String comment){
        this.setComment(comment);
        return this;
    }
}
