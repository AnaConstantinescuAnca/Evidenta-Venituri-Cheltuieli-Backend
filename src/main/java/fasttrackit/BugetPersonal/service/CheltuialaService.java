package fasttrackit.BugetPersonal.service;

import fasttrackit.BugetPersonal.entity.CheltuialaEntity;
import fasttrackit.BugetPersonal.entity.VenitEntity;
import fasttrackit.BugetPersonal.exception.ResourceNotFoundException;
import fasttrackit.BugetPersonal.loader.CheltuieliReader;
import fasttrackit.BugetPersonal.model.CheltuieliFilter;
import fasttrackit.BugetPersonal.repository.CheltuialaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheltuialaService {
    private final CheltuialaRepository cheltuialaRepository;

    //am incarcat din fisier informatiile
    public CheltuialaService(CheltuieliReader cheltuialaReader, CheltuialaRepository cheltuialaRepository) {
        this.cheltuialaRepository = cheltuialaRepository;
        cheltuialaRepository.saveAll(cheltuialaReader.getCheltuieliFromFile());
    }

    // Obtinem cheltuiala dupa id
    public CheltuialaEntity getByIdCheltuiala(int id) {
        return cheltuialaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cheltuiala lipseste", id));
    }

    // Obtinem toate cheltuielile
    public List<CheltuialaEntity> getAllCheltuieli(CheltuieliFilter filter) {
        return cheltuialaRepository.findAll().stream()
                .filter(tip -> filter.tip() == null || tip.getTip() == filter.tip())
                .toList();
    }

    //adaugam o cheltuiala
    public CheltuialaEntity addCheltuiala(CheltuialaEntity cheltuiala) {
        return cheltuialaRepository.save(cheltuiala);
    }

    // Metoda prin care se face update pe Cheltuiala dupa id
    public CheltuialaEntity updateCheltuiala(int id, CheltuialaEntity cheltuiala) {
        CheltuialaEntity cheltuialaToBeUpdated = cheltuialaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cheltuiala lipseste", id));
        cheltuialaToBeUpdated.setData(cheltuiala.getData());
        cheltuialaToBeUpdated.setTip(cheltuiala.getTip());
        cheltuialaToBeUpdated.setValoare(cheltuiala.getValoare());
        return cheltuialaRepository.save(cheltuialaToBeUpdated);
    }

    // Metoda prin care se face delete pe Cheltuiala dupa id
    public CheltuialaEntity deleteCheltuialaById(int id) {
        CheltuialaEntity cheltuialaToBeDeleted = getByIdCheltuiala(id);
        cheltuialaRepository.deleteById(id);
        return cheltuialaToBeDeleted;
    }

    /*
     * @param patchCheltuiala
     *   parametrul transmis de centroller
     * */ /*
     * @param patchCheltuiala
     *   parametrul transmis de centroller
     * */
    public CheltuialaEntity partialUpdate(int id, CheltuialaEntity patchCheltuiala) {

        CheltuialaEntity cheltuialaToUpdate = getByIdCheltuiala(id);

        cheltuialaToUpdate.setValoare(patchCheltuiala.getValoare() != 0 ? patchCheltuiala.getValoare() : cheltuialaToUpdate.getValoare());
        cheltuialaToUpdate.setData(patchCheltuiala.getData() != null ? patchCheltuiala.getData() : cheltuialaToUpdate.getData());
        cheltuialaToUpdate.setTip(patchCheltuiala.getTip() != null ? patchCheltuiala.getTip() : cheltuialaToUpdate.getTip());

        return  cheltuialaRepository.save(cheltuialaToUpdate);
    }
}
