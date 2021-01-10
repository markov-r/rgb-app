package com.rgb.application.repository;

import com.rgb.application.model.Rgb;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RgbRepository extends CrudRepository<Rgb, Long> {
}
