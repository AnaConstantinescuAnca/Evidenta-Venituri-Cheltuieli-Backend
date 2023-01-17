package fasttrackit.BugetPersonal.loader;

import fasttrackit.BugetPersonal.entity.VenitEntity;
import fasttrackit.BugetPersonal.enums.TipVenit;
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
public class VenituriReader {
    @Value("${file.venituri}")
    private String fileVenituriPath;

    // Colectam datele din fisierul venituri.txt
    public List<VenitEntity> getVenituriFromFile() {
        try {
            return Files.lines(Path.of(fileVenituriPath))
                    .map(this::readLineVenit)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Citim valorile separate de "|" si returnam venit entity
    private VenitEntity readLineVenit(String line) {

        String[] splitLine = line.split("\\|");
        String dataParts = splitLine[1];
        Date dataVenit = null;

        try {
            dataVenit = new SimpleDateFormat("dd-MM-yyyy").parse(dataParts);
        } catch (ParseException exception) {
            exception.printStackTrace();
        }

        return new VenitEntity(Integer.parseInt(splitLine[0]), Integer.parseInt(splitLine[2]),
                dataVenit, TipVenit.valueOf(splitLine[3]));

    }
}
