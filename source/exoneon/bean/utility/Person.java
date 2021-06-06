package exoneon.bean.utility;

import java.util.ArrayList;
import java.util.List;

public class Person {

	protected Long id;
	protected String name;
	protected List<Address> addressList = new ArrayList<>();
	protected Address mainAddress = new Address();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}

	public Address getMainAddress() {
		return mainAddress;
	}

	public void setMainAddress(Address mainAddress) {
		this.mainAddress = mainAddress;
	}

}
