package utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

/**
 * Created by schinnag on 5/12/2021.
 */
public class CustomFeature {

    List<File> featuresFiles  = null;
    public CustomFeature() {
        featuresFiles  = new ArrayList<>();
    }

    public List<File> getFeaturesFiles() {
        File[] files = new File("src/test/resources/SampleCucumber").listFiles((d, name) -> name.endsWith(".feature"));
        featuresFiles = Arrays.asList(files);
        return featuresFiles;
    }

    public void updateAllFiles() {
        for (File file : featuresFiles) {
            String exampledContent = updateFile(file);

            try {
                FileUtils.writeStringToFile(
                        new File(new File(file.getParent()).getParent() + File.separator + "ModifiedFeatures" + File.separator + file.getName()),
                        exampledContent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String updateFile(File file) {
        //for testing purpose
        //actually, we can get from excel
        Map data = new HashMap<String, String>();
        data.putIfAbsent("I_C1", "abcd");
        data.putIfAbsent("I_C2", "efgh");

        int size = data.keySet().size();

        List<String> allLines = null;
        try {
            allLines = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String finalContent = "";
        for (String line : allLines) {
            finalContent +=
                    StringUtils.replaceEach(
                            line,
                            (String[]) data.keySet().toArray(new String[size]),
                            (String[]) data.values().toArray(new String[size])
                    ) + "\n";
        }

        return finalContent;
    }

    public void deleteFiles() {
        Arrays.asList(
                new File("src/test/resources/ModifiedFeatures")
                        .listFiles((d, name) -> name.endsWith(".feature"))
        )
                .forEach(File::delete);
    }
}
