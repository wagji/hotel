package customer;

import lombok.Data;

@Data
public class CustomerDTO 
{
	private String CustomerNumber;
	private String CustomerName;
	private String PhoneNumber;
	private String ResidentRegistrationNumber;
	private String Email;
	private String Password;
	private String Address;
	public String getCustomerNumber() {
		return CustomerNumber;
	}
	public void setCustomerNumber(String customerNumber) {
		CustomerNumber = customerNumber;
	}
	public String getCustomerName() {
		return CustomerName;
	}
	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	public String getResidentRegistrationNumber() {
		return ResidentRegistrationNumber;
	}
	public void setResidentRegistrationNumber(String residentRegistrationNumber) {
		ResidentRegistrationNumber = residentRegistrationNumber;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	
	
	
}
