package com.tbd.lab1.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import net.postgis.jdbc.PGgeometry;
import net.postgis.jdbc.geometry.Point;

import java.io.IOException;

public class PGgeometrySerializer extends StdSerializer<PGgeometry> {
    public PGgeometrySerializer() {
        this(null);
    }

    public PGgeometrySerializer(Class<PGgeometry> t) {
        super(t);
    }

    @Override
    public void serialize(
            PGgeometry value, JsonGenerator gen, SerializerProvider arg2)
            throws IOException {

        Point point = (Point) value.getGeometry();
        gen.writeStartObject();
        gen.writeNumberField("lat", point.y);
        gen.writeNumberField("lon", point.x);
        gen.writeEndObject();
    }
}
