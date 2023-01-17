package fasttrackit.BugetPersonal.controller;

import fasttrackit.BugetPersonal.controller.dto.CheltuialaOverviewDTO;
import fasttrackit.BugetPersonal.entity.CheltuialaEntity;
import fasttrackit.BugetPersonal.entity.VenitEntity;
import fasttrackit.BugetPersonal.model.CheltuieliFilter;
import fasttrackit.BugetPersonal.service.CheltuialaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cheltuieli") //http://localhost:8080/cheltuieli
@RequiredArgsConstructor
public class CheltuialaController {
    private final CheltuialaService cheltuialaService;

    /*
     * Obtine toate cheltuielile; http://localhost:8080/cheltuielile
     * Obtine toate cheltuielile filtrate dupa tip cheltuiala; http://localhost:8080/cheltuielile?tip=CASA
     * */


    @GetMapping  //http://localhost:8080/cheltuieli
    public List<CheltuialaOverviewDTO> getAllCheltuieli(CheltuieliFilter filter) {
        return cheltuialaService.getAllCheltuieli(filter).stream()
                .map(cheltuieli -> new CheltuialaOverviewDTO(cheltuieli.getId(), cheltuieli.getData(),
                        cheltuieli.getTip(), cheltuieli.getValoare()))
                .toList();
    }

    //Adaugare cheltuiala
    @PostMapping
    public CheltuialaEntity add(@RequestBody CheltuialaEntity cheltuiala) {
        return cheltuialaService.addCheltuiala(cheltuiala);
    }

    // Actualizare cheltuiala update
    @PutMapping("{id}")
    CheltuialaEntity updateCheltuiala(@PathVariable int id, @RequestBody CheltuialaEntity cheltuiala){
        return cheltuialaService.updateCheltuiala(id, cheltuiala);
    }

    // Stergere cheltuiala delete
    @DeleteMapping("{id}")
    public CheltuialaEntity deleteCheltuialaById(@PathVariable int id) {
        return cheltuialaService.deleteCheltuialaById(id);
    }

    // Update pe una din proprietatile cheltuiala
    @PatchMapping("{id}")
    public CheltuialaEntity patchCheltuialaEntity(@PathVariable int id, @RequestBody CheltuialaEntity patchCheltuialaVal) {
        return cheltuialaService.partialUpdate(id, patchCheltuialaVal);
    }

}
