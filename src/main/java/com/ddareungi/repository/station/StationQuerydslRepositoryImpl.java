package com.ddareungi.repository.station;

import com.ddareungi.domain.Station;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class StationQuerydslRepositoryImpl implements StationQuerydslRepository {

    @Override
    public Page<Station> search(String address, Pageable pageable) {
        return null;
    }

}
