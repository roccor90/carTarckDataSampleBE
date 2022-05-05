package com.rr.trackdata.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.rr.trackdata.dto.TrackCsvDTO;


public class BayesianPrediction {

	private static Set<TrackCsvDTO> tracks;
	
	 public static void loadData(File file) {
		 tracks = new HashSet();

    	try (Reader reader = new BufferedReader(new FileReader(file))) {

            // create csv bean reader
            CsvToBean<TrackCsvDTO> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(TrackCsvDTO.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            // convert `CsvToBean` object to list of track
            List<TrackCsvDTO> elementList = csvToBean.parse();
            tracks.addAll(elementList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	 }
    
    public static void main(String[] args) throws IOException {    	
        loadData(new File("C:\\Users\\rrana\\Desktop\\Rocco\\Workspace\\PROGETTI-PERSONALI\\workspace_ms_spring_boot\\BE track-data\\track-data-ms\\documentaton\\test.csv"));
        TrackCsvDTO track = new TrackCsvDTO("2017-03-03 00:06:00","2.54999995232","Front Left","9.0","45.4199999641","51.0111111535","Volvo V40");
        double n = tracks.size();  // total number of elements in the training set
        double sumVolvoV40 = 0; 
        double sumAudiA6 = 0; 
        double sumPorsche911 = 0;

        for (TrackCsvDTO t : tracks) {
            sumVolvoV40 += (t.carId.equals("Volvo V40")? 1: 0);
        	sumAudiA6 += (t.carId.equals("Audi A6")? 1: 0);
        	sumPorsche911 += (t.carId.equals("Porsche 911")? 1: 0);

        }
        
        double[][] p = new double[6][4];
        for (TrackCsvDTO t : tracks) {
            if (t.carId.equals("Volvo V40")) {
                p[1][1] += (t.omega.equals(track.omega)? 1: 0)/sumVolvoV40;
                p[2][1] += (t.position.equals(track.position)? 1: 0)/sumVolvoV40;
                p[3][1] += (t.pressure.equals(track.pressure)? 1: 0)/sumVolvoV40;
                p[4][1] += (t.speed.equals(track.speed)? 1: 0)/sumVolvoV40;
                p[5][1] += (t.temperature.equals(track.temperature)? 1: 0)/sumVolvoV40;
            } else if (t.carId.equals("Audi A6")) {
            	p[1][2] += (t.omega.equals(track.omega)? 1: 0)/sumAudiA6;
                p[2][2] += (t.position.equals(track.position)? 1: 0)/sumAudiA6;
                p[3][2] += (t.pressure.equals(track.pressure)? 1: 0)/sumAudiA6;
                p[4][2] += (t.speed.equals(track.speed)? 1: 0)/sumAudiA6;
                p[5][2] += (t.temperature.equals(track.temperature)? 1: 0)/sumAudiA6;
            } else {
            	p[1][3] += (t.omega.equals(track.omega)? 1: 0)/sumPorsche911;
                p[2][3] += (t.position.equals(track.position)? 1: 0)/sumPorsche911;
                p[3][3] += (t.pressure.equals(track.pressure)? 1: 0)/sumPorsche911;
                p[4][3] += (t.speed.equals(track.speed)? 1: 0)/sumPorsche911;
                p[5][3] += (t.temperature.equals(track.temperature)? 1: 0)/sumPorsche911;
            }
        }
        double pc1 = p[1][1]*p[2][1]*p[3][1]*p[4][1]*p[5][1]*sumVolvoV40/n;
        double pc2 = p[1][2]*p[2][2]*p[3][2]*p[4][2]*p[5][2]*sumAudiA6/n;
        double pc3 = p[1][3]*p[2][3]*p[3][3]*p[4][3]*p[5][3]*sumPorsche911/n;
        System.out.printf("pc1 = %.4f, pc2 = %.4f, pc3 = %.4f%n", pc1, pc2, pc3);
        System.out.printf("Predict %s is correct is %s.%n", track.carId, (
        		(track.carId.equals("Volvo V40") && pc1>pc2 && pc1 >pc3) || 
        		(track.carId.equals("Audi A6") 	 && pc2>pc1 && pc2 >pc3) || 
        		(track.carId.equals("Porsche 911") && pc3>pc1 && pc3 >pc2) ? "true": "false")
        );
    }

}
