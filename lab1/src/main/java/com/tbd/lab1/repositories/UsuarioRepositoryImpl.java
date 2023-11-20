package com.tbd.lab1.repositories;
import com.tbd.lab1.entities.UsuarioEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.util.List;


@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository{

    private final Sql2o sql2o;

    public UsuarioRepositoryImpl(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public List<UsuarioEntity> findAll() {
        return null;
    }

    @Override
    public UsuarioEntity findById(Long id_usuario) {
        try (Connection connection = sql2o.open()) {
            String query = "SELECT * FROM usuario WHERE id = :id_usuario";
            return connection.createQuery(query).addParameter("id", id_usuario).executeAndFetchFirst(UsuarioEntity.class);
        }
    }

    @Override
    public UsuarioEntity findByEmail(String email) {
        try (Connection connection = sql2o.open()) {
            String query = "SELECT * FROM usuario WHERE email = :email";
            return connection.createQuery(query).addParameter("email", email).executeAndFetchFirst(UsuarioEntity.class);
        }
    }

    @Override
    public void register(UsuarioEntity usuario) {
        try (Connection connection = sql2o.beginTransaction()) {
            String query = "INSERT INTO usuario (email, password, rol) VALUES (:email, :password, :rol)";
            connection.createQuery(query, true)
                    .addParameter("email", usuario.getEmail())
                    .addParameter("password", usuario.getPassword())
                    .addParameter("rol", usuario.getRol())
                    .executeUpdate();
            connection.commit();
        }

    }

    @Override
    public void update(UsuarioEntity usuario) {
        try{
            Connection connection = sql2o.open();
            String query = "UPDATE usuario SET email = :email, password = :password WHERE id = :id";
            connection.createQuery(query)
                    .addParameter("id", usuario.getId())
                    .addParameter("email", usuario.getEmail())
                    .addParameter("password", usuario.getPassword())
                    .executeUpdate();
            connection.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = sql2o.open()) {
            String query = "DELETE FROM usuario WHERE id = :id_usuario";
            connection.createQuery(query).addParameter("id", id).executeUpdate();
        }
    }


/*
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UsuarioEntity user = UsuarioRepository.findByEmail(email);
        return null;
    }*/
}
