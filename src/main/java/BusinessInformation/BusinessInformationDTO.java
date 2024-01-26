package BusinessInformation;

import lombok.Data;

@Data
public class BusinessInformationDTO 
{
	private String SerialNumber;
	private String BusinessAddress;
	private String RoomType;
	private int RoomCount;
	private int Fee;
	public String getSerialNumber() {
		return SerialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		SerialNumber = serialNumber;
	}
	public String getBusinessAddress() {
		return BusinessAddress;
	}
	public void setBusinessAddress(String businessAddress) {
		BusinessAddress = businessAddress;
	}
	public String getRoomType() {
		return RoomType;
	}
	public void setRoomType(String roomType) {
		RoomType = roomType;
	}
	public int getRoomCount() {
		return RoomCount;
	}
	public void setRoomCount(int roomCount) {
		RoomCount = roomCount;
	}
	public int getFee() {
		return Fee;
	}
	public void setFee(int fee) {
		Fee = fee;
	}
	
	
}
