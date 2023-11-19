package com.tbd.lab1.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import net.postgis.jdbc.PGgeometry;
import net.postgis.jdbc.geometry.Geometry;
import net.postgis.jdbc.geometry.Point;
import net.postgis.jdbc.geometry.Polygon;

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

        if (value != null) {
            Geometry geometry = value.getGeometry();

            if (geometry != null) {
                if (geometry instanceof Point) {
                    // Serializar un punto
                    Point point = (Point) geometry;
                    gen.writeStartObject();
                    gen.writeNumberField("lat", point.y);
                    gen.writeNumberField("lon", point.x);
                    gen.writeEndObject();
                } else if (geometry instanceof Polygon) {
                    // Serializar un polígono
                    Polygon polygon = (Polygon) geometry;
                    gen.writeStartObject();
                    gen.writeFieldName("type");
                    gen.writeString("Polygon");
                    gen.writeFieldName("coordinates");
                    gen.writeStartArray();

                    for (int i = 0; i < polygon.numRings(); i++) {
                        gen.writeStartArray();
                        for (int j = 0; j < polygon.getRing(i).numPoints(); j++) {
                            Point ringPoint = polygon.getRing(i).getPoint(j);
                            gen.writeStartArray();
                            gen.writeNumber(ringPoint.x);
                            gen.writeNumber(ringPoint.y);
                            gen.writeEndArray();
                        }
                        gen.writeEndArray();
                    }

                    gen.writeEndArray();
                    gen.writeEndObject();
                } else {
                    // Puedes manejar otros tipos de geometría aquí
                    gen.writeNull(); // O manejar de otra manera, según tus necesidades
                }
            } else {
                gen.writeNull(); // Manejar el caso en que la geometría es null
            }
        } else {
            gen.writeNull(); // Manejar el caso en que el valor completo es null
        }
    }
}
