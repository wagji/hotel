package hotels;

import lombok.Data;

@Data
public class HotelsDTO 
{
	private String hotelsNumber;
	private String hotelsName;
	private String hotelsAddress;
	public String getHotelsNumber() {
		return hotelsNumber;
	}
	public void setHotelsNumber(String hotelsNumber) {
		this.hotelsNumber = hotelsNumber;
	}
	public String getHotelsName() {
		return hotelsName;
	}
	public void setHotelsName(String hotelsName) {
		this.hotelsName = hotelsName;
	}
	public String getHotelsAddress() {
		return hotelsAddress;
	}
	public void setHotelsAddress(String hotelsAddress) {
		this.hotelsAddress = hotelsAddress;
	}
	
	
}
