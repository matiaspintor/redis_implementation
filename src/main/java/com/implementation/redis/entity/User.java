package com.implementation.redis.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.implementation.redis.entity.listener.UserEntityListener;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter()
@Setter()
@Table(name = "user_data")
@EntityListeners({UserEntityListener.class})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user")
	private int idUser;
	
	@Column(name = "name_user", nullable = false)
	private String nameUser;
	
	@Column(name = "email_user", nullable = false)
	private String emailUser;
	
	@Column(name = "birth_date", nullable = false)
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Chile/Continental")
	private Date birthDate;
	
	@Column(name = "status_user", nullable = false)
	private boolean statusUser;
}
