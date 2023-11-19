package com.tbd.lab1.serializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import net.postgis.jdbc.PGgeometry;
import net.postgis.jdbc.geometry.Point;

import java.io.IOException;

public class PGgeometryDeserializer extends StdDeserializer<PGgeometry> {

    public PGgeometryDeserializer() {
        this(null);
    }

    public PGgeometryDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public PGgeometry deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {

        JsonNode node = jp.getCodec().readTree(jp);


// Verificar si el JSON tiene la estructura de coordenadas
        if (node.has("coordinates")) {
            // Extraer las coordenadas del JSON
            JsonNode coordinatesNode = node.get("coordinates");
            if (coordinatesNode.isArray() && coordinatesNode.size() > 0) {
                JsonNode firstCoordinateNode = coordinatesNode.get(0);
                if (firstCoordinateNode.isArray() && firstCoordinateNode.size() == 2) {
                    double lat = firstCoordinateNode.get(0).asDouble();
                    double lon = firstCoordinateNode.get(1).asDouble();
                    Point point = new Point(lon, lat);
                    return new PGgeometry(point);
                }
            }
        }

        double lat = node.get("lat").asDouble();
        double lon = node.get("lon").asDouble();

        Point point = new Point(lon, lat);
        return new PGgeometry(point);
    }
}

