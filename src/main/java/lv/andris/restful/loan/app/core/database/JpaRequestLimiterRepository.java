package lv.andris.restful.loan.app.core.database;

import lv.andris.restful.loan.app.core.domain.RequestLimiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;


public interface JpaRequestLimiterRepository extends JpaRepository<RequestLimiter, String> {

    @Query("Select r From RequestLimiter r where country = :country")
    List<RequestLimiter> getRequestLimiterByCountry(@Param("country") String country);

    @Query("Select r.expiry From RequestLimiter r Where country = :country")
    Timestamp getExpiry(@Param("country") String country);

    @Query("Select r.requests_made From RequestLimiter r Where country = :country")
    int getRequestsMade(@Param("country") String country);

    @Modifying
    @Query("UPDATE RequestLimiter AS r SET r.requests_made = requests_made + 1 WHERE r.country = :country")
    int incrementRequestCount(@Param("country") String country);

    @Modifying
    @Query("DELETE RequestLimiter WHERE country = :country")
    int deleteRequestLimiterForCountry(@Param("country") String country);
}
