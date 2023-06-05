package com.ipn.temperature.service;

import com.ipn.temperature.api.model.Temperature;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TemperatureService {
    public Optional<Temperature> convertTemperature(Double degrees, Character type, Character typeTo) {
        Optional optional = Optional.empty();
        double convertedDegrees;

        // convert from Celsius to Fahrenheit
        if (type == 'C' && typeTo == 'F') {
            convertedDegrees = degrees * 1.8 + 32;
        }
        // convert from Celsius to Kelvin
        else if (type == 'C' && typeTo == 'K') {
            convertedDegrees = degrees + 273.15;
        }
        // convert from Fahrenheit to Celsius
        else if (type == 'F' && typeTo == 'C') {
            convertedDegrees = (degrees - 32) / 1.8;
        }
        // convert from Fahrenheit to Kelvin
        else if (type == 'F' && typeTo == 'K') {
            convertedDegrees = (degrees + 459.67) / 1.8;
        }
        // convert from Kelvin to Celsius
        else if (type == 'K' && typeTo == 'C') {
            convertedDegrees = degrees - 273.15;
        }
        // convert from Kelvin to Fahrenheit
        else if (type == 'K' && typeTo == 'F') {
            convertedDegrees = degrees * 1.8 - 459.67;
        }
        else {
            return optional;
        }
        optional = Optional.of(new Temperature(convertedDegrees, typeTo));
        return optional;
    }
}
