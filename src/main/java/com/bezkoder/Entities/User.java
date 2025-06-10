package com.bezkoder.Entities;



import org.springframework.validation.annotation.Validated;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;

@Column(name = "username",updatable = true)
private String username;

@Email(message = "Email no valido")
@Column(name = "email",unique = true)
private String email;
@Column(name = "password")
private String password;


}
