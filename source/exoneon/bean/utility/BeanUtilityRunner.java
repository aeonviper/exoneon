package exoneon.bean.utility;

import java.lang.reflect.Type;

import org.apache.commons.beanutils.BeanUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class BeanUtilityRunner {

	public static void main(String[] array) throws Exception {
		new BeanUtilityRunner().run();
	}

	void run() throws Exception {
		Person person = new Person();
		person.setName("Bob");
		System.out.println(checkNotNull(person, "id", "name"));
		person.setId(0L);
		System.out.println(checkNotNull(person, "id", "name"));
		person.getAddressList().add(new Address("Alice", "Sydney"));
		person.getAddressList().add(new Address("Taylor", "Perth"));
		System.out.println(BeanUtils.getProperty(person, "addressList[1].name"));
		// person.getAddressList().get(1).getName();
		person.mainAddress.name = "Bandung";
		System.out.println(BeanUtils.getProperty(person, "mainAddress.name"));
		// person.getMainAddress().getName();
		person.map.put("Key","Some value");
		System.out.println(BeanUtils.getProperty(person, "map(Key)"));
		System.out.println(BeanUtils.getProperty(person, "map.Key"));
		// person.getMap().get("Key");

		Person user = new Person();
		BeanUtils.copyProperties(user, person);
		System.out.println("User's name from BeanUtility copyProperties: " + user.getName());

		Gson gson = new Gson();
		Type typePerson = new TypeToken<Person>() {
		}.getType();

		user = gson.fromJson(gson.toJson(person), typePerson);
		// gson.toJson(person) -> {id: 1, name: "Bob"}

		System.out.println("User's name from Gson: " + user.getName());
	}

	boolean checkNotNull(Object object, String... array) throws Exception {
		for (String fieldName : array) {
			Object field = BeanUtils.getProperty(object, fieldName);
			if (field == null) {
				return false;
			}
		}
		return true;
	}

}
