package com.rr.trackdata.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrackRepository extends MongoRepository<Track, String> {
  public List<Track> findByCarId(String car_id);
}