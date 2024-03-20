package kr.co.sboard.service;


import kr.co.sboard.dto.ArticleDTO;
import kr.co.sboard.dto.FileDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileService {

    @Value("${file.upload.path}")
    private String fileUploadPath;

    public List<FileDTO> fileUpload(ArticleDTO articleDTO) {

        //파일 시스템 경로 구하기
        String path = new File(fileUploadPath).getAbsolutePath();

        List<FileDTO> files = new ArrayList<>();

        log.info("for문 동작전에 뜨는 fileUpload메서드");


        ///첨부한 파일 갯수만큼 반복 처리
        for (MultipartFile mf : articleDTO.getFiles()) {


            if (!mf.isEmpty()) {

                String oName = mf.getOriginalFilename();

                log.info("여기는 파일이 있어야 들어올 수 있는 곳: 오리지날 이름" + oName);

                String ext = oName.substring(oName.lastIndexOf("."));
                String sName = UUID.randomUUID().toString() + ext;

                log.info("oName : " + oName);
                log.info("sName : " + sName);


                try {
                    // 저장
                    mf.transferTo(new File(path, sName));

                    //파일정보 생성
                    FileDTO fileDTO = FileDTO.builder()
                            .oName(oName)
                            .sName(sName)
                            .build();

                    //리스트 저장
                    files.add(fileDTO);

                } catch (IOException e) {
                    log.error("fileUpload : " + e.getMessage());
                }
            }
        }
        //저장한 파일 정보 리스트 반환
        return files;
    }


    public void insertFile(FileDTO fileDTO) {

    }

    public void selectFile() {
    }

    public void selectFiles() {
    }

    public void updateFile() {
    }

    public void deleteFile() {
    }
}
