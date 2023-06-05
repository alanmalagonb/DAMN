package com.ipn.temperature.api.controller;

import com.ipn.temperature.api.model.Temperature;
import com.ipn.temperature.service.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class TemperatureController {

    private TemperatureService temperatureService;

    @Autowired
    public TemperatureController(TemperatureService temperatureService){
        this.temperatureService = temperatureService;
    }

    @GetMapping("/temperature")
    public Temperature convertTemperature(@RequestParam Double degrees, @RequestParam Character type,@RequestParam Character typeTo){
        Optional<Temperature> temperature = temperatureService.convertTemperature(degrees,type,typeTo);
        if(temperature.isPresent()){
            return (Temperature)  temperature.get();
        }
        return null;
    }
}
