package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

@RestController
public class PDFValidatorController {
	
	private PDFValidatorService pDFValidatorService;
	
	@Autowired
	public PDFValidatorController(PDFValidatorService pDFValidatorService) {
		this.pDFValidatorService = pDFValidatorService;
	}

	@PostMapping(value="/upload/pdf", consumes = "application/pdf", produces = "application/json")
	public ResponseEntity<AppCustomRespons> uploadPDF(@RequestBody byte[] pdf){
		return pDFValidatorService.validatePDFBytes(pdf);
	}
	
	@PostMapping(value="/upload/multiPart")
	public ResponseEntity<AppCustomRespons> multiPartUploadPDF(@RequestParam("file") MultipartFile file){
		return pDFValidatorService.validatePDF(file);
	}
	
	@PostMapping(value="/upload/filenPropss", consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE, produces = "application/json", headers="content-type=application/json,application/octet-stream")
	public ResponseEntity<AppCustomRespons> uploadPDF(@RequestPart("file") MultipartFile file, @RequestPart("props") FileProps props){
		return pDFValidatorService.validatePDF(file, props);
	}
	
	@PostMapping(value="/upload/filenProps", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "application/json")
	public ResponseEntity<AppCustomRespons> uploadPDFs(@RequestPart("file") MultipartFile file, @RequestParam("props") String fprops){
		Gson gson = new Gson();
		FileProps props = gson.fromJson(fprops, FileProps.class);
		return pDFValidatorService.validatePDF(file, props);
	}

}
