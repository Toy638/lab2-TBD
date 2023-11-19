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

        double lat = node.get("lat").asDouble();
        double lon = node.get("lon").asDouble();

        Point point = new Point(lon, lat);
        return new PGgeometry(point);
    }
}

