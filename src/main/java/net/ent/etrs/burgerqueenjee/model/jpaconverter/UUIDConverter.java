package net.ent.etrs.burgerqueenjee.model.jpaconverter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.UUID;

@Converter(autoApply=true)
public class UUIDConverter implements AttributeConverter<UUID, String> {

	@Override
	public String convertToDatabaseColumn(UUID arg0) {
		
		
		String uuidChaine = null;
		if(arg0 != null) {
			uuidChaine = arg0.toString();
		}
		return uuidChaine;

//		return (arg0 != null) ? arg0.toString() : null;

	}

	
	@Override
	public UUID convertToEntityAttribute(String arg0) {
		UUID retour = null;
		try {
			retour = UUID.fromString(arg0);
		} catch(IllegalArgumentException e) {
			retour = null;
		}
		
		return retour;
		
	}

}
