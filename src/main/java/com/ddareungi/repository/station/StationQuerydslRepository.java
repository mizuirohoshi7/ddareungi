package com.ddareungi.repository.station;

import com.ddareungi.domain.Station;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StationQuerydslRepository {

    Page<Station> search(String address, Pageable pageable);

}
