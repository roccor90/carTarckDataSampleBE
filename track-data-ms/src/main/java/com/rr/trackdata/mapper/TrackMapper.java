package com.rr.trackdata.mapper;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.rr.trackdata.dto.TrackDTO;
import com.rr.trackdata.repository.Track;
import com.rr.trackdata.util.Utility;

@Component
public class TrackMapper {

	 public TrackDTO toDto(Track entity) {
		TrackDTO dto = new TrackDTO();
	     dto.setTimestamp(Utility.convertToString(entity.getTimestamp(), "yyyy-MM-dd'T'HH:mm:ss.SSS"));
	     dto.setPressure(entity.getPressure());
	     dto.setPosition(entity.getPosition());
		 dto.setTemperature(entity.getTemperature());
	     dto.setOmega(entity.getOmega());
	     dto.setSpeed(entity.getSpeed());
	     dto.setPosition(entity.getPosition());
	     dto.setCarId(entity.getCarId());
		 dto.setId(entity.getId());
	     return dto;
	 }

	 public Track toEntity(TrackDTO dto) {
		Track entity = new Track();
		if(dto.getTimestamp() != null) {
			entity.setTimestamp(Utility.convertToDate(dto.getTimestamp(), "yyyy-MM-dd'T'HH:mm:ss.SSS"));
		} else {
			entity.setTimestamp(new Date(System.currentTimeMillis()));
		}
	    entity.setPressure(dto.getPressure());
	    entity.setPosition(dto.getPosition());
		entity.setTemperature(dto.getTemperature());
	    entity.setOmega(dto.getOmega());
	    entity.setSpeed(dto.getSpeed());
	    entity.setPosition(dto.getPosition());
	    entity.setCarId(dto.getCarId());
		if(dto.getId() != null) {
			entity.setId(dto.getId());
		}
	    return entity;
	 }


	
}
