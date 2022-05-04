package com.rr.trackdata.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrackCsvDTO {
	@CsvBindByName(column = "Timestamp")
	public String timestamp;
	
	@CsvBindByName(column = "Press")
	public String pressure;
	
	@CsvBindByName(column = "Position")
	public String position;
	
	@CsvBindByName(column = "Temp")
	public String temperature;
	
	@CsvBindByName(column = "Omega")
	public String omega;
	
	@CsvBindByName(column = "Speed")
	public String speed;
	
	@CsvBindByName(column = "Car_id")
	public String carId;

	@Override
	public String toString() {
		return "TrackCsvDTO [timestamp=" + timestamp + ", pressure=" + pressure + ", position=" + position
				+ ", temperature=" + temperature + ", omega=" + omega + ", speed=" + speed + ", carId=" + carId + "]";
	}
	
}
