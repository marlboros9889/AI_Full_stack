package com.marinboy.marinboy.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

// 관리자가 업로드한 시술 대표 이미지를 로컬 업로드 폴더에 저장합니다.
@Service
public class ImageStorageService {

    private static final Set<String> ALLOWED_EXTENSIONS = Set.of("jpg", "jpeg", "png", "gif", "webp");

    private final Path serviceImageDirectory;

    public ImageStorageService(@Value("${app.upload.service-image-dir:uploads/service-images}") String directory) {
        this.serviceImageDirectory = Path.of(directory).toAbsolutePath().normalize();
    }

    public String storeServiceImage(MultipartFile file) {
        // URL 대신 이미지 파일을 선택한 경우 서버에 저장하고 화면에서 접근할 URL을 반환합니다.
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("업로드할 이미지 파일을 선택해야 합니다.");
        }

        String extension = extensionOf(file.getOriginalFilename());
        if (!ALLOWED_EXTENSIONS.contains(extension)) {
            throw new IllegalArgumentException("이미지 파일은 jpg, jpeg, png, gif, webp 형식만 사용할 수 있습니다.");
        }

        try {
            Files.createDirectories(serviceImageDirectory);
            String savedFileName = UUID.randomUUID() + "." + extension;
            Path targetPath = serviceImageDirectory.resolve(savedFileName).normalize();
            file.transferTo(targetPath);
            return "/uploads/service-images/" + savedFileName;
        } catch (IOException exception) {
            throw new IllegalStateException("이미지 파일 저장 중 문제가 발생했습니다.");
        }
    }

    public Path getServiceImageDirectory() {
        return serviceImageDirectory;
    }

    private String extensionOf(String originalFilename) {
        // 파일 확장자를 소문자로 통일해서 허용된 이미지 형식인지 확인합니다.
        if (originalFilename == null || !originalFilename.contains(".")) {
            throw new IllegalArgumentException("이미지 파일 확장자를 확인할 수 없습니다.");
        }
        return originalFilename.substring(originalFilename.lastIndexOf('.') + 1).toLowerCase(Locale.ROOT);
    }
}
