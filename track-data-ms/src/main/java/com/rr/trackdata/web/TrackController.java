package com.rr.trackdata.web;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.rr.trackdata.dto.TrackDTO;
import com.rr.trackdata.service.TrackService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/track")
public class TrackController {
	
		@Autowired
		private TrackService trackService;
		
		//Basic filtering by car (selecting single car)
	    @GetMapping("/{carId}")
	    public ResponseEntity<List<TrackDTO>> getByCarId(@PathVariable(name = "carId") String carId) {
	        return ResponseEntity.ok(trackService.getByCarId(carId));
	    }

	  //Basic filtering by car (selecting subset)
		@PostMapping("/subsetCarId")
	    public ResponseEntity<?> create(@RequestBody List<String> carIds) {
			List<TrackDTO> allTracks = new LinkedList<TrackDTO>();
			for(String carId: carIds){
				allTracks.addAll(trackService.getByCarId(carId));
			}
			Collections.sort(allTracks);
	        return ResponseEntity.ok(allTracks);
	    }

	    @PostMapping
	    public ResponseEntity<?> create(@RequestBody TrackDTO dto) {
	    	trackService.create(dto);
	        return ResponseEntity.ok().build();
	    }
	    
	    
	    @PostMapping("/upload-csv-file")
	    public ResponseEntity<?> uploadCSVFile(@RequestParam("file") MultipartFile file) {
	        trackService.uploadCsvdata(file);
	        return ResponseEntity.ok().build();
	    }

}
