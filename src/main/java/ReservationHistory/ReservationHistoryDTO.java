package ReservationHistory;

import java.sql.Date;

import lombok.Data;

@Data
public class ReservationHistoryDTO 
{
 private String ReservationNumber;
 private String SerialNumber;
 private String CustomerNumber;
 private String ReservationStartDate;
 private String ReservationEndDate;
 private int NumberOfPeople;
 private String PaymentMethod;
public String getReservationNumber() {
	return ReservationNumber;
}
public void setReservationNumber(String reservationNumber) {
	ReservationNumber = reservationNumber;
}
public String getSerialNumber() {
	return SerialNumber;
}
public void setSerialNumber(String serialNumber) {
	SerialNumber = serialNumber;
}
public String getCustomerNumber() {
	return CustomerNumber;
}
public void setCustomerNumber(String customerNumber) {
	CustomerNumber = customerNumber;
}
public String getReservationStartDate() {
	return ReservationStartDate;
}
public void setReservationStartDate(String reservationStartDate) {
	ReservationStartDate = reservationStartDate;
}
public String getReservationEndDate() {
	return ReservationEndDate;
}
public void setReservationEndDate(String reservationEndDate) {
	ReservationEndDate = reservationEndDate;
}
public int getNumberOfPeople() {
	return NumberOfPeople;
}
public void setNumberOfPeople(int numberOfPeople) {
	NumberOfPeople = numberOfPeople;
}
public String getPaymentMethod() {
	return PaymentMethod;
}
public void setPaymentMethod(String paymentMethod) {
	PaymentMethod = paymentMethod;
}
 
 
}
