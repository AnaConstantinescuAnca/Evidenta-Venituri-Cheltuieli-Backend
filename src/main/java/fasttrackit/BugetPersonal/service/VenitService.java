package fasttrackit.BugetPersonal.service;

import fasttrackit.BugetPersonal.entity.VenitEntity;
import fasttrackit.BugetPersonal.exception.ResourceNotFoundException;
import fasttrackit.BugetPersonal.loader.VenituriReader;
import fasttrackit.BugetPersonal.model.VenituriFilter;
import fasttrackit.BugetPersonal.repository.VenitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenitService {
    private final VenitRepository venitRepository;

    //am incarcat din fisier informatiile
    public VenitService(VenituriReader venitReader, VenitRepository venitRepository) {
        this.venitRepository = venitRepository;
        venitRepository.saveAll(venitReader.getVenituriFromFile());
    }

    // obtinem venit dupa id
    public VenitEntity getByIdVenit(int id) {
        return venitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cheltuiala lipseste! Id = ", id));
    }

    // Obtinem toate veniturile
    public List<VenitEntity> getAllVenituri(VenituriFilter filter) {

        return venitRepository.findAll().stream()
                .filter(tip -> filter.tip() == null || tip.getTip() == filter.tip())
                .toList();
    }

    //adaugam un venit
    public VenitEntity addVenit(VenitEntity venit) {
        return venitRepository.save(venit);
    }

    // Metoda prin care se face update pe Venit dupa id
    public VenitEntity updateVenit(int id, VenitEntity venit) {
        VenitEntity venitToBeUpdated = venitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venitul lipseste", id));
        venitToBeUpdated.setData(venit.getData());
        venitToBeUpdated.setTip(venit.getTip());
        venitToBeUpdated.setValoare(venit.getValoare());
        return venitRepository.save(venitToBeUpdated);
    }

    //metoda care face delete venit dupa id
    public VenitEntity deleteVenitById(int id) {
        VenitEntity venitToBeDeleted = getByIdVenit(id);
        venitRepository.deleteById(id);
        return venitToBeDeleted;
    }

    /*
    * @param patchVenit
    *   parametrul transmis de centroller
    * */
    public VenitEntity partialUpdate(int id, VenitEntity patchVenit) {

        VenitEntity venitToUpdate = getByIdVenit(id);

        venitToUpdate.setValoare(patchVenit.getValoare() != 0 ? patchVenit.getValoare() : venitToUpdate.getValoare());
        venitToUpdate.setData(patchVenit.getData() != null ? patchVenit.getData() : venitToUpdate.getData());
        venitToUpdate.setTip(patchVenit.getTip() != null ? patchVenit.getTip() : venitToUpdate.getTip());

        return  venitRepository.save(venitToUpdate);
    }
}
