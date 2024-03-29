package kr.co.sboard.service;


import jakarta.transaction.Transactional;
import kr.co.sboard.dto.ArticleDTO;
import kr.co.sboard.dto.FileDTO;
import kr.co.sboard.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;

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

    @Transactional
    public ResponseEntity<?> fileDownload(int fno) {

        //파일 조회
        kr.co.sboard.entity.File file= fileRepository.findById(fno).get();

        try {

            Path path = Paths.get(fileUploadPath + file.getSName());

            String contentType = Files.probeContentType(path);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentDisposition(ContentDisposition.builder("attachment")
                    .filename(file.getOName(), StandardCharsets.UTF_8).build());

            headers.add(HttpHeaders.CONTENT_TYPE, contentType);
            Resource resource = new InputStreamResource(Files.newInputStream(path));


            //파일 다운로드 카운트 업데이트
            file.setDownload(file.getDownload()+1);
            fileRepository.save(file);


            return new ResponseEntity<>(resource, headers, HttpStatus.OK);
        }catch (Exception e){

            log.error("fileDownload : "+e.getMessage());

            return new ResponseEntity<>(null,null,HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<?> fileDownloadCount(int fno)  {

        // 파일 조회
        kr.co.sboard.entity.File file = fileRepository.findById(fno).get();

        // 다운로드 카운트 Json 생성
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("count", file.getDownload());//이게 File엔티티에서 파일의 다운로드 횟수임

        return ResponseEntity.ok().body(resultMap);
    }
/*
    public List<FileDTO> fileById(int ano){

       kr.co.sboard.entity.File filed = fileRepository.findById(ano).get();

    }
 */

}
