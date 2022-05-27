package net.codejava;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class PersonFieldSetMapper implements FieldSetMapper<UserBatch> {

	@Override
	public UserBatch mapFieldSet(FieldSet fieldSet) throws BindException {
		// TODO Auto-generated method stub
		return new UserBatch(fieldSet.readLong("id"),
				fieldSet.readString("name"), fieldSet.readString("dept"), fieldSet.readInt("salary"),fieldSet.readInt("leave"));
	}

}
