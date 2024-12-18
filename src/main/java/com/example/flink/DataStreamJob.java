package com.example.flink;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.connector.elasticsearch.sink.Elasticsearch7SinkBuilder;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.elasticsearch7.shaded.org.apache.http.HttpHost;
import org.apache.flink.elasticsearch7.shaded.org.elasticsearch.action.index.IndexRequest;
import org.apache.flink.elasticsearch7.shaded.org.elasticsearch.client.Requests;
import org.apache.flink.elasticsearch7.shaded.org.elasticsearch.common.xcontent.XContentType;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class DataStreamJob {

    public static void main(String[] args) throws Exception {
        // Initialiser l'environnement d'exécution Flink
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // Configurer la source Kafka pour WeatherGeneral
        KafkaSource<WeatherGeneral> source1 = KafkaSource.<WeatherGeneral>builder()
                .setBootstrapServers("kafka:9092")
                .setTopics("weather")
                .setGroupId("flink-group")
                .setStartingOffsets(OffsetsInitializer.earliest())
                .setValueOnlyDeserializer(new JSONWeatherDeserializationSchema())
                .build();

        // Configurer la source Kafka pour WeatherMarine
        KafkaSource<WeatherMarine> source2 = KafkaSource.<WeatherMarine>builder()
                .setBootstrapServers("kafka:9092")
                .setTopics("wave")
                .setGroupId("flink-group-marine")
                .setStartingOffsets(OffsetsInitializer.earliest())
                .setValueOnlyDeserializer(new JSONWeatherMarineDeserializationSchema())
                .build();

        // Lire les flux Kafka
        DataStream<WeatherGeneral> stream1 = env.fromSource(source1, WatermarkStrategy.noWatermarks(), "Kafka WeatherGeneral Source");
        DataStream<WeatherMarine> stream2 = env.fromSource(source2, WatermarkStrategy.noWatermarks(), "Kafka WeatherMarine Source");

        // Joindre les flux en utilisant cityName comme clé
        DataStream<CombinedWeather> combinedStream = stream1
                .keyBy(weatherGeneral -> (int) weatherGeneral.getLatitude()) // Conversion de la latitude en int dans WeatherGeneral
                .connect(stream2.keyBy(weatherMarine -> (int) weatherMarine.getLatitude())) // Conversion de la latitude en int dans WeatherMarine
                .process(new CombinedWeatherProcessor());

        // Envoyer les données combinées vers Elasticsearch
        combinedStream.sinkTo(
                new Elasticsearch7SinkBuilder<CombinedWeather>()
                        .setHosts(new HttpHost("elasticsearch", 9200, "http"))
                        .setEmitter((combinedWeather, runtimeContext, requestIndexer) -> {
                            // Convertir l'objet CombinedWeather en JSON
                            String json = JsonUtil.convertWeatherToJson(combinedWeather);

                            // Créer une requête d'indexation pour Elasticsearch
                            IndexRequest indexRequest = Requests.indexRequest()
                                    .index("weather")
                                    .source(json, XContentType.JSON);

                            // Ajouter la requête à l'indexeur
                            requestIndexer.add(indexRequest);
                        })
                        .build()
        );

        // Démarrer le job Flink
        env.execute("Kafka to Elasticsearch with Combined Streams");
    }
}
