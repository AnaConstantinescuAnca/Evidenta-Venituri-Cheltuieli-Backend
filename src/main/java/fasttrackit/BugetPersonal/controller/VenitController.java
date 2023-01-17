package fasttrackit.BugetPersonal.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fasttrackit.BugetPersonal.controller.dto.VenitOverviewDTO;
import fasttrackit.BugetPersonal.entity.VenitEntity;
import fasttrackit.BugetPersonal.model.VenituriFilter;
import fasttrackit.BugetPersonal.service.VenitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("venituri") //http://localhost:8080/venituri
@RequiredArgsConstructor
public class VenitController {
    private final VenitService venitService;
    /*
     * Obtine toate veniturile; http://localhost:8080/venituri
     * Obtine toate veniturile filtrate dupa tip venit; http://localhost:8080/venituri?tip=DOBANDA
     * */
    @GetMapping
    public List<VenitOverviewDTO> getAllVenituri(VenituriFilter filter) {
        return venitService.getAllVenituri(filter).stream()
                .map(venituri -> new VenitOverviewDTO(venituri.getId(), venituri.getData(), venituri.getTip(), venituri.getValoare()))
                .toList();
    }

    //Adaugare venit
    @PostMapping
    public VenitEntity add(@RequestBody VenitEntity venit) {
        return venitService.addVenit(venit);
    }

    // Actualizeaza venit update
    @PutMapping("{id}")
    VenitEntity updateVenit(@PathVariable int id, @RequestBody VenitEntity venit){
        return venitService.updateVenit(id, venit);
    }

    //  // Sterge venit delete
    @DeleteMapping("{id}")
    public VenitEntity deleteVenitById(@PathVariable int id) {
        return venitService.deleteVenitById(id);
    }

    // Update pe una din proprietatile venit
    @PatchMapping("{id}")
    public VenitEntity patchVenitEntity(@PathVariable int id, @RequestBody VenitEntity patchVenitVal) {
        return venitService.partialUpdate(id, patchVenitVal);
    }
}
