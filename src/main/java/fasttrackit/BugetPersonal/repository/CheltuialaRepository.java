package fasttrackit.BugetPersonal.repository;
import fasttrackit.BugetPersonal.entity.CheltuialaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheltuialaRepository extends JpaRepository<CheltuialaEntity, Integer>{
}
