package com.rr.trackdata.repository;


import java.util.Date;

import org.springframework.data.annotation.Id;

import com.rr.trackdata.dto.TrackCsvDTO;
import com.rr.trackdata.util.Utility;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Track implements Comparable<Track>{
  @Id
  public String id;

  public Date timestamp;
  public Double pressure;
  public String position;
  public Double temperature;
  public Double omega;
  public Double speed;
  public String carId;

  public Track() {}

  public Track(Double pressure, String position, Double temperature, Double omega, Double speed, String carId) {
    this.timestamp = new Date(System.currentTimeMillis());
    this.pressure = pressure;
    this.position = position;
    this.temperature = temperature;
    this.omega = omega;
    this.speed = speed;
    this.carId = carId;
  }
  
  public Track(TrackCsvDTO trackCsv) {
	    this.timestamp = Utility.convertToDate(trackCsv.getTimestamp(), "yyyy-MM-dd HH:mm:ss");
	    this.pressure = !trackCsv.getPressure().equals("") ? Double.parseDouble(trackCsv.getPressure()) : null;
	    this.position = trackCsv.getPosition();
	    this.temperature = !trackCsv.getTemperature().equals("") ? Double.parseDouble(trackCsv.getTemperature())  : null;
	    this.omega = !trackCsv.getOmega().equals("") ? Double.parseDouble(trackCsv.getOmega()) : null;
	    this.speed = !trackCsv.getSpeed().equals("") ? Double.parseDouble(trackCsv.getSpeed()) : null;
	    this.carId = trackCsv.getCarId();
	  }

  @Override
  public int compareTo(Track o) {
	return getTimestamp().compareTo(o.getTimestamp());
  }

  @Override
  public String toString() {
		return "Track [id=" + id + ", timestamp=" + timestamp + ", pressure=" + pressure + ", position=" + position
				+ ", temperature=" + temperature + ", omega=" + omega + ", speed=" + speed + ", carId=" + carId + "]";
  }

}

