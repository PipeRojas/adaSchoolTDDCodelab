package org.adaschool.tdd.service;

import org.adaschool.tdd.controller.weather.dto.WeatherReportDto;
import org.adaschool.tdd.exception.WeatherReportNotFoundException;
import org.adaschool.tdd.repository.WeatherReportRepository;
import org.adaschool.tdd.repository.document.GeoLocation;
import org.adaschool.tdd.repository.document.WeatherReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MongoWeatherService
    implements WeatherService
{

    private final WeatherReportRepository repository;

    public MongoWeatherService( @Autowired WeatherReportRepository repository )
    {
        this.repository = repository;
    }

    @Override
    public WeatherReport report( WeatherReportDto weatherReportDto )
    {
        return repository.save(new WeatherReport(weatherReportDto));
    }

    @Override
    public WeatherReport findById( String id )
    {
        Optional<WeatherReport> ans = repository.findById(id);
        if(!ans.isPresent()){
            throw new WeatherReportNotFoundException();
        }else{
            return ans.get();
        }
    }

    @Override
    public List<WeatherReport> findNearLocation( GeoLocation geoLocation, float distanceRangeInMeters )
    {
        List<WeatherReport> ans= new ArrayList<>();
        for (WeatherReport wr: repository.findAll()) {
            if(wr.getGeoLocation().distance(geoLocation)<=distanceRangeInMeters){
                ans.add(wr);
            }
        }
        return ans;
    }

    @Override
    public List<WeatherReport> findWeatherReportsByName( String reporter )
    {
        return repository.findByReporter(reporter);
    }
}
