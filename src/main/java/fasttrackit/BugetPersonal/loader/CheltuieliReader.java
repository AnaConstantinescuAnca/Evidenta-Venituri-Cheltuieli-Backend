package fasttrackit.BugetPersonal.loader;

import fasttrackit.BugetPersonal.entity.CheltuialaEntity;
import fasttrackit.BugetPersonal.enums.TipCheltuiala;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CheltuieliReader {
    @Value("${file.cheltuieli}")
    private String fileCheltuieliPath;

    // Colectam datele din fisierul cheltuieli.txt
    public List<CheltuialaEntity> getCheltuieliFromFile() {
        try {
            return Files.lines(Path.of(fileCheltuieliPath))
                    .map(this::readLineCheltuiala)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Citim valorile separate de "|" si returnam cheltuiala entity
    private CheltuialaEntity readLineCheltuiala(String line) {

        String[] splitLine = line.split("\\|");
        String dataParts = splitLine[1];
        Date dataCheltuiala = null;

        try {
            dataCheltuiala = new SimpleDateFormat("dd-MM-yyyy").parse(dataParts);
        } catch (ParseException exception) {
            exception.printStackTrace();
        }

        return new CheltuialaEntity(Integer.parseInt(splitLine[0]), Double.parseDouble(splitLine[2]),
                dataCheltuiala, TipCheltuiala.valueOf(splitLine[3]));

    }
}
