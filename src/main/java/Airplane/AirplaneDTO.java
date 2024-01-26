package Airplane;

import lombok.Data;

@Data
public class AirplaneDTO 
{
	private String SerialNumber;
	private String AirplaneName;
	private int Capacity;
	private String DepartureCity;
	private String DestinationCity;
	public String getSerialNumber() {
		return SerialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		SerialNumber = serialNumber;
	}
	public String getAirplaneName() {
		return AirplaneName;
	}
	public void setAirplaneName(String airplaneName) {
		AirplaneName = airplaneName;
	}
	public int getCapacity() {
		return Capacity;
	}
	public void setCapacity(int capacity) {
		Capacity = capacity;
	}
	public String getDepartureCity() {
		return DepartureCity;
	}
	public void setDepartureCity(String departureCity) {
		DepartureCity = departureCity;
	}
	public String getDestinationCity() {
		return DestinationCity;
	}
	public void setDestinationCity(String destinationCity) {
		DestinationCity = destinationCity;
	}
	
	
}
