package com.implementation.redis.dto;

import java.util.Date;

import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Getter()
@Setter()
@JsonPropertyOrder({ "id", "name", "email", "brith", "status" })
public class UserDTO {

	@JsonProperty(value = "id")
	private int idUser;

	@JsonProperty(value = "name")
	@Email(message = "The email entered is not valid")
	private String nameUser;

	@JsonProperty(value = "email")
	private String emailUser;

	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "Chile/Continental")
	@JsonProperty(value = "brith")
	private Date birthDate;

	@JsonProperty(value = "status")
	private boolean statusUser;
}
