package by.nikita.services.api;

import java.io.InputStream;

public interface IStorageService {

    String save(InputStream fileStream);

    InputStream read(String fileName);
}
