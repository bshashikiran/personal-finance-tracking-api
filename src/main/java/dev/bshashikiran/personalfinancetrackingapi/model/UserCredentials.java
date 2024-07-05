package dev.bshashikiran.personalfinancetrackingapi.model;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_credentials")
public class UserCredentials implements Serializable {
    
    public static final long serialVersionUID = -1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UCRFNUM")
    private Long ucRfnum;

    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    @Column(name = "USER_PASSWORD", nullable = false)
    private String userPassword;

    @Column(name = "CREATEDATE", updatable = false)
    @CreationTimestamp
    private Date createDate;

    @Column(name = "MODIDATE")
    @UpdateTimestamp
    private Date modiDate;

    @Column(name = "DELETED", nullable = false)
    private String deleted = "N";

    @Column(name = "ISACTIVE", nullable = false)
    private String isActive = "Y";

}
