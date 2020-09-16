package by.nikita.services;

import by.nikita.services.api.IStorageService;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.UUID;

@Service
public class StorageService implements IStorageService {

    @Override
    public String save(InputStream fileStream) {
        String string = UUID.randomUUID().toString();
        File file = new File(string);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            IOUtils.copy(fileStream, fileOutputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file.getAbsolutePath();
    }

    @Override
    public InputStream read(String fileName) {
        try {
            return new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
