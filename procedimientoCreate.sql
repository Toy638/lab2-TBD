-- Tabla de queries de usuario --
CREATE TABLE user_queries (
    id serial PRIMARY KEY,
    user_id integer,
    query text,
    query_type varchar(10),
    timestamp timestamp
);


CREATE OR REPLACE FUNCTION generate_user_query_report()
RETURNS TABLE (user_id integer, username varchar, query_count integer, last_query text) AS $$
BEGIN
    RETURN QUERY
    SELECT
        u.id AS user_id,
        u.email AS username,
        COUNT(uq.id) AS query_count,
        MAX(uq.timestamp) AS last_query
    FROM
        usuario u
    LEFT JOIN
        user_queries uq ON u.id = uq.user_id
    GROUP BY
        u.id
    ORDER BY
        query_count DESC;
END;
$$ LANGUAGE plpgsql;



-- Testeo: SELECT * FROM generate_user_query_report();