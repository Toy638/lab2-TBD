INSERT INTO region (nombre, geom) VALUES
('Arica y Parinacota', ST_GeomFromText('POLYGON((
    -70.374021 -18.355898,
    -69.978752 -18.269287,
    -69.749717 -17.958024,
    -69.794386 -17.651072,
    -69.473659 -17.509981,
    -68.921318 -18.886304,
    -70.286369 -19.228981,
    -70.374021 -18.355898
))', 4326)),
('Tarapacá', ST_GeomFromText('POLYGON((
    -70.286490 -19.226292,
    -68.950085 -18.946912,
    -68.478510 -20.955103,
    -70.053651 -21.424550,
    -70.286490 -19.226292
))', 4326)),
('Antofagasta', ST_GeomFromText('POLYGON((
    -70.058731 -21.437225,
    -68.441137 -20.935275,
    -67.850730 -22.866857,
    -67.011731 -22.995640,
    -67.314703 -24.042723,
    -69.093759 -25.796361,
    -70.622366 -26.013777,
    -70.058731 -21.437225
))', 4326)),
('Atacama', ST_GeomFromText('POLYGON((
    -70.625938 -26.030389,
    -68.560509 -25.302576,
    -69.959201 -29.480756,
    -71.496148 -29.182322,
    -70.625938 -26.030389
))', 4326)),
('Coquimbo', ST_GeomFromText('POLYGON((
    -71.498533 -29.179561,
    -71.081586 -29.357696,
    -70.904557 -29.134008,
    -70.528017 -29.075151,
    -70.466097 -29.376609,
    -69.968236 -29.498793,
    -70.334118 -32.261145,
    -71.536163 -32.179562,
    -71.618582 -30.239217,
    -71.498533 -29.179561
))', 4326)),
('Valparaíso', ST_GeomFromText('POLYGON((
    -71.544566 -32.176534,
    -70.647978 -32.069991,
    -70.224161 -32.329341,
    -70.000930 -32.948735,
    -71.031007 -32.977802,
    -71.700251 -33.931766,
    -71.529525 -32.187600,
    -71.544566 -32.176534
))', 4326)),
('Región Metropolitana', ST_GeomFromText('POLYGON((
    -71.011019 -32.976862,
    -70.419887 -33.031251,
    -70.097961 -33.067271,
    -69.780045 -33.326766,
    -69.811310 -34.230796,
    -70.440559 -33.854224,
    -70.949396 -34.150472,
    -71.714594 -33.947718,
    -71.011019 -32.976862
))', 4326)),
('Libertador General Bernardo O Higgins', ST_GeomFromText('POLYGON((
    -71.834290 -33.923589,
    -70.933341 -34.137059,
    -70.433463 -33.875221,
    -70.023783 -34.303590,
    -70.333634 -34.994381,
    -71.285362 -34.807358,
    -72.052932 -34.691958,
    -71.997406 -34.136882,
    -71.834290 -33.923589
))', 4326)),
('Maule', ST_GeomFromText('POLYGON((
    -72.056317 -34.683776,
    -71.702908 -34.924844,
    -71.282485 -34.798539,
    -70.365127 -35.008023,
    -70.711702 -36.419537,
    -72.793611 -35.989585,
    -72.056317 -34.683776
))', 4326)),
('Ñuble', ST_GeomFromText('POLYGON((
    -72.756420 -36.000246,
    -71.094951 -36.378097,
    -71.111282 -37.108322,
    -72.261003 -37.191630,
    -72.881592 -36.454321,
    -72.756420 -36.000246
))', 4326)),
('Biobío', ST_GeomFromText('POLYGON((
    -72.892103 -36.442210,
    -72.180749 -37.049224,
    -72.273133 -37.189187,
    -71.122957 -37.122921,
    -70.998239 -38.110798,
    -71.654163 -38.292294,
    -72.504092 -37.647765,
    -73.049156 -37.614841,
    -73.520312 -38.462488,
    -73.598838 -37.152380,
    -73.201805 -37.160884,
    -72.892103 -36.442210
))', 4326)),
('Araucanía', ST_GeomFromText('POLYGON((
    -73.540175 -38.474844,
    -73.061543 -37.617821,
    -72.506095 -37.648582,
    -71.764203 -38.035076,
    -71.651559 -38.303805,
    -71.092227 -38.102351,
    -70.894130 -38.729284,
    -71.437925 -38.913884,
    -71.468999 -39.605603,
    -73.230508 -39.390550,
    -73.540175 -38.474844
))', 4326)),
('Los Ríos', ST_GeomFromText('POLYGON((
    -73.243724 -39.390577,
    -72.856764 -39.284491,
    -71.916878 -39.422073,
    -71.674477 -39.568305,
    -71.671451 -40.086529,
    -71.889591 -40.567025,
    -72.608560 -40.673984,
    -73.709452 -40.248217,
    -73.683923 -39.964410,
    -73.243724 -39.390577
))', 4326)),
('Los Lagos', ST_GeomFromText('POLYGON((
    -73.766612 -40.236241,
    -71.859647 -40.582834,
    -71.739927 -42.128654,
    -72.178997 -42.157284,
    -71.721796 -43.161364,
    -71.718750 -44.062053,
    -73.037318 -43.723858,
    -74.793001 -43.673309,
    -73.766612 -40.236241
))', 4326)),
('Aysén', ST_GeomFromText('POLYGON((
    -74.136110 -43.815507,
    -72.842740 -43.635249,
    -71.780328 -43.955332,
    -71.152119 -44.425643,
    -72.554273 -48.814799,
    -73.173243 -49.190553,
    -74.244893 -48.686885,
    -75.556741 -48.741745,
    -75.605016 -46.581449,
    -75.250903 -44.801533,
    -74.136110 -43.815507
))', 4326)),
('Magallanes', ST_GeomFromText('POLYGON((
    -75.630826 -48.814381,
    -74.993619 -48.604141,
    -73.268766 -49.153250,
    -73.587369 -49.153250,
    -73.114957 -50.736727,
    -72.367887 -50.653215,
    -71.986585 -51.915128,
    -68.448154 -52.352669,
    -68.393996 -54.843755,
    -66.461696 -55.141065,
    -67.228300 -56.043160,
    -71.288184 -55.020236,
    -74.669157 -52.823299,
    -75.386881 -51.563120,
    -75.684382 -49.810590,
    -75.630826 -48.814381
))', 4326));

