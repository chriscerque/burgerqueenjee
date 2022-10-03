package net.ent.etrs.burgerqueenjee.model.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;


@SuppressWarnings("serial")

@MappedSuperclass
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@FieldDefaults(level=AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public abstract class AbstractEntity implements Serializable{
	// annotation lombok
	@Getter
	// annotations JPA
	@Id
//	@Column(name="ID", columnDefinition="VARCHAR(36)")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

}
