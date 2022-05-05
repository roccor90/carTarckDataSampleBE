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
	
	public TrackCsvDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TrackCsvDTO(String timestamp, String pressure, String position, String temperature, String omega,
			String speed, String carId) {
		super();
		this.timestamp = timestamp;
		this.pressure = pressure;
		this.position = position;
		this.temperature = temperature;
		this.omega = omega;
		this.speed = speed;
		this.carId = carId;
	}
	

	@Override
	public String toString() {
		return "TrackCsvDTO [timestamp=" + timestamp + ", pressure=" + pressure + ", position=" + position
				+ ", temperature=" + temperature + ", omega=" + omega + ", speed=" + speed + ", carId=" + carId + "]";
	}
}
