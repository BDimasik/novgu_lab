package me.dimasik.novgu.controller;

import me.dimasik.novgu.entity.FileEntity;
import me.dimasik.novgu.repository.FilesRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
public final class FileController {
    private final FilesRepository filesRepository;
    @Value("${upload.path}")
    private String path;

    public FileController(FilesRepository filesRepository) {
        this.filesRepository = filesRepository;
    }

    @PostMapping("/upload")
    public String load(@RequestParam("file") MultipartFile file,
                       @RequestParam("token") String token,
                       HttpServletRequest request) throws IOException {
        System.out.println(token);
        if (file == null) {
            return "error";
        }
        new File(path).mkdir();
        FileEntity fileEntity = new FileEntity();
        fileEntity.setName(file.getOriginalFilename());
        fileEntity.setVisualName(file.getOriginalFilename() + " origin");

        filesRepository.save(fileEntity);
        file.transferTo(new File(path + "/" + file.getOriginalFilename()));
        return "success";
    }

    @GetMapping("/files")
    public Iterable<FileEntity> get() {
        File file = new File(path);
        if (!file.exists()) file.mkdir();
        return filesRepository.findAll();
    }

    @RequestMapping("/file/{fileName}")
    @ResponseBody
    public void download(@PathVariable("fileName") String fileName, HttpServletResponse response) throws IOException {
        try (BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
             FileInputStream fis = new FileInputStream(path + "/" + fileName)) {
            int len;
            byte[] buf = new byte[1024];
            while ((len = fis.read(buf)) > 0) {
                bos.write(buf, 0, len);
            }
        }
        response.flushBuffer();
    }
}
