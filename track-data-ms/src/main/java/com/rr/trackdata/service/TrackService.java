package com.rr.trackdata.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.rr.trackdata.dto.TrackCsvDTO;
import com.rr.trackdata.dto.TrackDTO;
import com.rr.trackdata.mapper.TrackMapper;
import com.rr.trackdata.repository.Track;
import com.rr.trackdata.repository.TrackRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class TrackService {
	
	@Autowired
    private TrackRepository trackRepository;
	
	@Autowired
    private TrackMapper trackMapper;

    public List<TrackDTO> getByCarId(String car_id) {
        List<Track> resultOpt = trackRepository.findByCarId(car_id);
        Collections.sort(resultOpt);
        /**
         * Preprocessing of data making sure that pressure and angular speed
			(omega) contain just positive values.
         */
        
        List<TrackDTO> tracksDto = new LinkedList<TrackDTO>();
        for(Track t: resultOpt) {
        	if(t.getPressure() != null && t.getPressure() >= 0 && t.getOmega() != null && t.getOmega() >= 0) {
            	tracksDto.add(trackMapper.toDto(t));
        	}
        }
        return tracksDto;
    }

    public void create(TrackDTO dto) {
    	trackRepository.save(trackMapper.toEntity(dto));
    }
    
    public void uploadCsvdata(MultipartFile file) {
    	 // validate file
        if (!file.isEmpty()) {
            // parse CSV file to create a list of `Track` objects
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

                // create csv bean reader
                CsvToBean<TrackCsvDTO> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(TrackCsvDTO.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                // convert `CsvToBean` object to list of track
                List<TrackCsvDTO> tracks = csvToBean.parse();
                
                for(TrackCsvDTO t: tracks) {
                	Track trackEntity = new Track(t);
                	trackRepository.save(trackEntity);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
