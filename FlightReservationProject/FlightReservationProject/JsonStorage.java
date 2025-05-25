import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonStorage {
    private static final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .enable(SerializationFeature.INDENT_OUTPUT)
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    public static <T> void saveList(String filename, List<T> data, Class<T> clazz) throws IOException {
        mapper.writerFor(mapper.getTypeFactory().constructCollectionType(List.class, clazz))
              .writeValue(new File(filename), data);
    }

    public static <T> List<T> loadList(String filename, Class<T> clazz) throws IOException {
        File file = new File(filename);
        if (!file.exists()) return new java.util.ArrayList<>();
        return mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
    }
}
