package com.reudercosta.cursomc.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class S3Service {

	private Logger LOG = LoggerFactory.getLogger(S3Service.class);

	@Autowired
	private AmazonS3 s3Client;

	@Value("${s3.bucket}")
	private String bucketName;

	public URI uploadFile(MultipartFile multiPart) {
		try {
			String fileName = multiPart.getOriginalFilename();
			InputStream is;
			is = multiPart.getInputStream();
			String contentType = multiPart.getContentType();
			return uploadFile(is, fileName, contentType);
		} catch (IOException e) {
		throw new RuntimeException("Erro de IO:"+e.getMessage() );
		}

	}

	public URI uploadFile(InputStream is, String fileName, String contentType) {
		try {
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentType(contentType);
			LOG.info("Starting upload");
			s3Client.putObject(new PutObjectRequest(bucketName, fileName, is, meta));
			LOG.info("Finalize upload");
			return s3Client.getUrl(bucketName, fileName).toURI();
		} catch (URISyntaxException e) {
			throw new RuntimeException("Error ao converter URL para URI");
		}
	}
}