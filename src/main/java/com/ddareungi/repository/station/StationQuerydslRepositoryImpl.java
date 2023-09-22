package com.ddareungi.repository.station;

import com.ddareungi.domain.Station;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.ddareungi.domain.QStation.station;

public class StationQuerydslRepositoryImpl implements StationQuerydslRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;

    public StationQuerydslRepositoryImpl(EntityManager em) {
        this.em = em;
        query = new JPAQueryFactory(em);
    }

    @Override
    public Page<Station> search(String address, Pageable pageable) {
        List<Station> content = query
                .selectFrom(station)
                .where(station.address.contains(address))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total =  query
                .selectFrom(station)
                .where(station.address.contains(address))
                .fetch()
                .size();

        return new PageImpl<>(content, pageable, total);
    }

}
