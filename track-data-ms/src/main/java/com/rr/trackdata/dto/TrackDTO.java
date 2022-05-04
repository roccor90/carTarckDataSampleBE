package com.rr.trackdata.dto;

import com.rr.trackdata.util.Utility;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrackDTO implements Comparable<TrackDTO>{
	public String timestamp;
	
	public Double pressure;
	
	public String position;
	
	public Double temperature;
	
	public Double omega;
	
	public Double speed;
	
	public String carId;

	public String id;

	@Override
	public String toString() {
		return "TrackDTO [timestamp=" + timestamp + ", pressure=" + pressure + ", position=" + position
				+ ", temperature=" + temperature + ", omega=" + omega + ", speed=" + speed + ", carId=" + carId
				+ ", id=" + id + "]";
	}

	@Override
	public int compareTo(TrackDTO o) {
		return Utility.convertToDate(getTimestamp(), "yyyy-MM-dd'T'HH:mm:ss.SSS").compareTo(Utility.convertToDate(o.getTimestamp(), "yyyy-MM-dd'T'HH:mm:ss.SSS"));

	}
	
}
