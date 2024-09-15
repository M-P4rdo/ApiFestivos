package apifestivos.apifestivos.core.interfaces.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import apifestivos.apifestivos.dominio.Festivo;

@Repository
public interface IFestivoRepositorio extends JpaRepository<Festivo, Integer>{

    @Query("SELECT f FROM Festivo f WHERE f.dia = :dia AND f.mes = :mes")
    Optional<Festivo> findByDiaAndMes(@Param("dia") int dia, @Param("mes") int mes);
}


//http://localhost:8081/api/v1/festivos/validar?dia=12&mes=05