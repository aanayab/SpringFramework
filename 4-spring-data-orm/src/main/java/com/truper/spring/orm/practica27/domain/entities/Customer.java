package com.truper.spring.orm.practica27.domain.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// Habilitar Entidad JPA
@Entity
// Habilitar nombre de tabla CUSTOMER_TBL
@Table(name = "CUSTOMER_TBL")
@ToString(exclude = { "accounts" })
@EqualsAndHashCode(exclude = { "accounts" })
public class Customer {

	@Id
	@Column(name = "CUSTOMER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "LAST_NAME")
	private String lastName;

	// Anotar mapeo one-to-one Fetch.EAGER y cascade.ALL mapeado por el
	// atributo customer de la Tabla User
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "customer")
	private User user;

	// Anotar mapeo one-to-many Fetch.Lazy mapeado por el
	// atributo customer de la Tabla Account
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.REMOVE)
	// Anotar Cascada solo para DELETE (cascada de hibernate no de JPA)
	@Cascade({ org.hibernate.annotations.CascadeType.DELETE })
	private @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE) List<Account> accounts = new ArrayList<>();
}
