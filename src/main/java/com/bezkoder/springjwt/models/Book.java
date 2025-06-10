package com.bezkoder.springjwt.models;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book")
public class Book {
/*   imagen
descuento
titulo
valoración
(lista de valoraciones para sacar valoración)
Sinopsis
Categorías que pertenecen
fecha publicación
Autor
Comentarios libro*/  
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Integer id;
@Column(name = "bookimage")
private byte[] image;
@Column(name = "booktitle")
private String title;
@Column(name = "rating")
private int rating;
@Column(name = "listrating")
@JsonProperty("listrating")
private int[] listrating;
@Column(name = "booksinopsis")
private String sinopsis;
@Column(name = "bookcategories")
private String[] categories;
@Column(name = "publicationdate")
@DateTimeFormat
private Date publicationdate;
@Column(name = "bookprice")
private double price;
@Column(name = "discount")
private double disccount;
}
