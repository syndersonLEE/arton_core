package team.arton.coreserver.service;


import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class S3FileUploadService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${cloud.aws.s3.bucket.name}")
    private String bucket;

    @Value("${cloud.aws.s3.bucket.url}")
    private String defaultUrl;

    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;


    public String upload(MultipartFile uploadFile) throws IOException {
        String originalFileName = uploadFile.getOriginalFilename();
        logger.info("{} - Original File Name", originalFileName);
        String url = null;

        try{
            //확장자
            final String ext = originalFileName.substring(originalFileName.lastIndexOf('.'));
            //파일 이름 암호화
            final String saveFileName = getUuid() + ext;
            //파일 객체 생성
            File file = new File(System.getProperty("user.dir") + saveFileName);

            uploadFile.transferTo(file);
            uploadOnS3(saveFileName, file);

            url = defaultUrl + saveFileName;
            file.delete();

        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
            logger.info("no file");
            url = null;
        }
        return url;

    }

    private static String getUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    private void uploadOnS3(final String fileName, final File file) {
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 amazonS3Client = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.AP_NORTHEAST_2)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();

        final TransferManager transferManager = TransferManagerBuilder.standard()
                .withS3Client(amazonS3Client)
                .build();
        final PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, fileName, file).withCannedAcl(CannedAccessControlList.PublicRead);
        Upload upload = transferManager.upload(putObjectRequest);
        try {
            upload.waitForCompletion();
        } catch (AmazonClientException e) {
            logger.error(e.getMessage());
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
    }

}
