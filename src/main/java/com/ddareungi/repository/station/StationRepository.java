package com.ddareungi.repository.station;

import com.ddareungi.domain.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station, Long>, StationQuerydslRepository {
}
