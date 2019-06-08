package com.tim9.pkiapi.role.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.tim9.pkiapi.user.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name", nullable=false)
	private String name;
	
	@ManyToMany(fetch = FetchType.EAGER,
    cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
	@JoinTable(name = "user_role",
    joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id", nullable=false),
	inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id", nullable=false))
    private Set<User> users;

}
