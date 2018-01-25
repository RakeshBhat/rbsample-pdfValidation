package com.example.demo;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.preflight.PreflightDocument;
import org.apache.pdfbox.preflight.ValidationResult;
import org.apache.pdfbox.preflight.parser.PreflightParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PDFValidatorService {

	public ResponseEntity<AppCustomRespons> validatePDFBytes(byte[] pdf) {
		AppCustomRespons o = null;
		try {
			PDDocument doc = PDDocument.load(pdf);
			int pageCount = doc.getNumberOfPages();

			if (pageCount > 0) {
				System.out.println("YES PDF is Good, with page count, " + pageCount);
			}

		} catch (IOException e) {
			throw new PDFValidationException("Invalid PDF file.");
		}
		o = new AppCustomRespons("validatePDFBytes", "Valid PDF");
		return new ResponseEntity<AppCustomRespons>(o, HttpStatus.OK);
	}

	public ResponseEntity<AppCustomRespons> validatePDF(byte[] pdf) {
		File file = new File("upload.pdf");
		try {
			FileUtils.writeByteArrayToFile(file, pdf);
			PDDocument doc = PDDocument.load(pdf);
			if (doc.isEncrypted()) {
				System.out.println("YES PDF is Encrypted.");
			} else {
				System.out.println("YES PDF is NOT Encrypted.");
			}
		} catch (IOException e) {

		}
		return null;

	}

	public ResponseEntity<AppCustomRespons> validatePDF(MultipartFile file) {
		AppCustomRespons o = null;
		try {
			byte[] filebyt = file.getBytes();

			PDDocument doc = PDDocument.load(filebyt);
			int pageCount = doc.getNumberOfPages();

			if (pageCount > 0) {
				System.out.println("YES PDF is Good, with page count, " + pageCount);
			} else {
				System.out.println("PDF is BAD, with page count, " + pageCount);
			}

		} catch (IOException e) {
			throw new PDFValidationException("Invalid PDF File.");
		}

		o = new AppCustomRespons("validatePDF", "Valid PDF");
		return new ResponseEntity<AppCustomRespons>(o, HttpStatus.OK);

	}

	public ResponseEntity<AppCustomRespons> validatePDF(MultipartFile file, FileProps props) {
		AppCustomRespons o = null;

		try {

			System.out.println("File Props: " + props);

			byte[] filebyt = file.getBytes();
			File ufile = new File("upload.pdf");
			FileUtils.writeByteArrayToFile(ufile, filebyt);
			PreflightParser parser = new PreflightParser(ufile);
			parser.parse();

			PreflightDocument document = parser.getPreflightDocument();
			document.validate();

			// Get validation result
			ValidationResult result = document.getResult();
			result.getErrorsList();

			if (null != result) {
				System.out.println("YES PDF is GOOD, errors" + result);
			}

			document.close();
			FileUtils.deleteQuietly(ufile);

		} catch (IOException e) {
			throw new PDFValidationException("Invalid PDF File.");
		}
		
		o = new AppCustomRespons("validatePDFBytes", "Valid PDF");
		return new ResponseEntity<AppCustomRespons>(o, HttpStatus.OK);
	}

}
