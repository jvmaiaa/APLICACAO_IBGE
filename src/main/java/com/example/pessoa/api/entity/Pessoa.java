package com.example.pessoa.api.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "tb_pessoa")
@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa implements Serializable{
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	private String name;

	private Integer age;

	private String email;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "endereco_id")
	private Address endereco;
}
