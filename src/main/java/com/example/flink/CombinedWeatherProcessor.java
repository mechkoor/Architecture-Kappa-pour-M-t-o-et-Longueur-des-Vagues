package com.example.flink;


import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.streaming.api.functions.co.KeyedCoProcessFunction;
import org.apache.flink.util.Collector;

public class CombinedWeatherProcessor extends KeyedCoProcessFunction<String, WeatherGeneral, WeatherMarine, CombinedWeather> {

    // États pour stocker temporairement les objets WeatherGeneral et WeatherMarine
    private ValueState<WeatherGeneral> weatherGeneralState;
    private ValueState<WeatherMarine> weatherMarineState;

    @Override
    public void open(org.apache.flink.configuration.Configuration parameters) {
        weatherGeneralState = getRuntimeContext().getState(
                new ValueStateDescriptor<>("weatherGeneralState", WeatherGeneral.class));
        weatherMarineState = getRuntimeContext().getState(
                new ValueStateDescriptor<>("weatherMarineState", WeatherMarine.class));
    }

    @Override
    public void processElement1(WeatherGeneral general, Context ctx, Collector<CombinedWeather> out) throws Exception {
        // Mettre à jour l'état avec WeatherGeneral
        weatherGeneralState.update(general);

        // Vérifier si WeatherMarine est déjà présent
        WeatherMarine marine = weatherMarineState.value();
        if (marine != null) {
            CombinedWeather combined = new CombinedWeather();
            combined.setWeatherGeneral(general);
            combined.setWeatherMarine(marine);
            out.collect(combined);

            // Nettoyer les états
            weatherGeneralState.clear();
            weatherMarineState.clear();
        }
    }

    @Override
    public void processElement2(WeatherMarine marine, Context ctx, Collector<CombinedWeather> out) throws Exception {
        // Mettre à jour l'état avec WeatherMarine
        weatherMarineState.update(marine);

        // Vérifier si WeatherGeneral est déjà présent
        WeatherGeneral general = weatherGeneralState.value();
        if (general != null) {
            CombinedWeather combined = new CombinedWeather();
            combined.setWeatherGeneral(general);
            combined.setWeatherMarine(marine);
            out.collect(combined);

            // Nettoyer les états
            weatherGeneralState.clear();
            weatherMarineState.clear();
        }
    }
}
