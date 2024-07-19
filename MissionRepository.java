package com.example.aligntest;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissionRepository extends CrudRepository<Mission,Integer> {

    @Query("SELECT country ,COUNT(*) AS count " +
            "FROM Mission WHERE agent IN " +
            "(SELECT agent FROM Mission " +
            "GROUP BY agent " +
            "HAVING COUNT(*) = 1) group by country")
    List<IsolatedCountries> getIsolatedCountries();

    @Query("SELECT country,COUNT(country) As count " +
            "From Mission " +
            "WHERE agent IN( SELECT agent FROM Mission GROUP BY agent HAVING COUNT(*) = 1) " +
            "GROUP BY country " +
            "HAVING COUNT(country)= " +
            "(SELECT MAX(y.countrycount) " +
            "FROM " +
            "(SELECT country ,COUNT(*) AS countrycount FROM Mission WHERE agent IN ( " +
            "SELECT agent FROM Mission GROUP BY agent HAVING COUNT(*) = 1) group by country) y)")
    List<IsolatedCountries> getMostIsolatedCountries();

    @Query("SELECT DISTINCT m.address FROM Mission m")
    List<String> findDistinctAddresses();

    @Query("SELECT * FROM Mission WHERE address =:address")
    List<Mission> findMissionByAddress(String address);
}
