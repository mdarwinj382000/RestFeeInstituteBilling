package spring.restproject.restfeeinstitutebilling.config;

import java.io.Serializable;
import java.util.stream.Stream;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import spring.restproject.restfeeinstitutebilling.entities.Accountant;
import spring.restproject.restfeeinstitutebilling.entities.Admin;
import spring.restproject.restfeeinstitutebilling.entities.Student;

public class UserIDGenerator implements IdentifierGenerator {

	String prefix;

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {

		String query = String.format("select %s from %s",
				session.getEntityPersister(object.getClass().getName(), object).getIdentifierPropertyName(),
				object.getClass().getSimpleName());
		if (object instanceof Admin)
			prefix = "AD";
		else if (object instanceof Accountant)
			prefix = "AC";
		else if (object instanceof Student)
			prefix = "ST";
		else
			prefix = "USER";
		Stream<String> ids = session.createQuery(query, String.class).stream();

		Long max = ids.map(o -> o.replace(prefix, "")).mapToLong(Long::parseLong).max().orElse(0L);

		return prefix + String.format("%03d", (max + 1));
	}

}
