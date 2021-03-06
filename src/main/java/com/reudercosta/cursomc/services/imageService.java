package com.reudercosta.cursomc.services;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.reudercosta.cursomc.services.excpetions.FileException;

@Service
public class imageService {

	public BufferedImage getJpgImageFromFile(MultipartFile uploadedFile) {

		String ext = FilenameUtils.getExtension(uploadedFile.getOriginalFilename());

		if (!"png".equals(ext) && !"jpg".equals(ext)) {
			throw new FileException("Somente imagens JPG e PNG são permitidas");

		}
		
		try {
			BufferedImage img = ImageIO.read(uploadedFile.getInputStream());
			if("png".equals(ext)) {
			img = pngToJpg(img);
			}
			return img;
		} catch (IOException e) {
			throw new FileException("Erro ao ler o arquivo");
		}
		

	}

	public BufferedImage pngToJpg(BufferedImage img) {
		BufferedImage jpgImage = new BufferedImage(img.getWidth(), img.getHeight(), 
				java.awt.image.BufferedImage.TYPE_INT_RGB);
		jpgImage.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);
		return jpgImage;
	}
	
	public InputStream getInputStream(BufferedImage img, String extension) {
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(img, extension, os);
			return new ByteArrayInputStream(os.toByteArray());
			
		}catch(IOException e) {
			throw new FileException("Erro ao ler o arquivo!!");
		}
	}
	
	public BufferedImage cropSquare(BufferedImage sourceImage) {
		int min = (sourceImage.getHeight()<= sourceImage.getWidth() ? sourceImage.getHeight() : sourceImage.getWidth());
		return Scalr.crop(sourceImage, 
				(sourceImage.getWidth()/2)-(min/2), 
				(sourceImage.getHeight()/2)-(min/2), 
				min,
				min);
	}
	
	public BufferedImage rezise(BufferedImage sourceImg, int size) {
		return Scalr.resize(sourceImg, Scalr.Method.ULTRA_QUALITY, size);
	}
}
