package fasttrackit.BugetPersonal.repository;

import fasttrackit.BugetPersonal.entity.VenitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenitRepository extends JpaRepository<VenitEntity, Integer> {

}
