import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task3 {
    private static final Map<Long, String> valueMap = new HashMap<>();

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java Task3 <tests.json> <values.json>");
            return;
        }
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            TestContainer testContainer = parseJsonObject(args[0], TestContainer.class);
            ValueContainer valuesContainer = parseJsonObject(args[1], ValueContainer.class);
            valuesContainer.values.forEach(value -> valueMap.put(value.id, value.value));
            updateTestValues(testContainer.tests);
            try (FileWriter file = new FileWriter("report.json")) {
                gson.toJson(testContainer, file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static <T> T parseJsonObject(String filePath, Class<T> clazz) throws IOException {
        try (JsonReader reader = new JsonReader(new FileReader(filePath))) {
            return new Gson().fromJson(reader, clazz);
        }
    }

    private static void updateTestValues(List<Test> testsList) {
        testsList.forEach(test -> {
            if (valueMap.containsKey(test.id)) {
                test.value = valueMap.get(test.id);
            }
            if (test.values != null) {
                updateTestValues(test.values);
            }
        });
    }

    static class ValueContainer {
        List<Value> values;
    }

    static class Value {
        long id;
        String value;
    }

    static class TestContainer {
        List<Test> tests;
    }

    static class Test {
        long id;
        String title;
        String value;
        List<Test> values;
    }
}
