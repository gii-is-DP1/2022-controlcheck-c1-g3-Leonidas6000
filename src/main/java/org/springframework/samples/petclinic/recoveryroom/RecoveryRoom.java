package org.springframework.samples.petclinic.recoveryroom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="Recovery_Room")
public class RecoveryRoom {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="id")
    Integer id;
	
	@Column(name="name")
	@Length(max = 50,min = 3)
	@NotEmpty
    String name;
	
	@PositiveOrZero
	@Column(name="size")
	@NotNull
    double size;
    
	@Column(name="secure")
	@NotNull
    boolean secure;
	
    @ManyToOne(optional = false)
    @JoinColumn(name="room_type")
    RecoveryRoomType roomType;
}
