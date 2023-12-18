package com.myshopping.myshopping.utility;

import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("/api/qrcode")
public class QRCodeController {

    private final QRCodeGeneratorService qrCodeGeneratorService;

    @Autowired
    public QRCodeController(QRCodeGeneratorService qrCodeGeneratorService) {
        this.qrCodeGeneratorService = qrCodeGeneratorService;
    }

    @GetMapping("/{text}/{width}/{height}")
    public byte[] generateQRCode(@PathVariable String text,
                                 @PathVariable int width,
                                 @PathVariable int height) throws IOException, WriterException {
        return qrCodeGeneratorService.generateQRCodeImage(text, width, height);
    }
    
    
    @RequestMapping(path = "/qrgen",method = RequestMethod.GET)
    public ResponseEntity<byte[]> generateQRCode1(@PathVariable("text") String text,
    		@RequestParam("width") int width,
    		@RequestParam("height") int height) throws IOException, WriterException {
        byte[] qrCodeBytes = qrCodeGeneratorService.generateQRCodeImage(text, width, height);

        // Set appropriate headers and return the image bytes as ResponseEntity
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(qrCodeBytes);
    }
    //this is working code
    @RequestMapping(path ="/qrgenerate",method = RequestMethod.GET)
    public String generateQRCode2(@RequestParam("text") String text,
    		@RequestParam("width") int width,
    		@RequestParam("height") int height) throws IOException, WriterException {
        byte[] qrCodeBytes = qrCodeGeneratorService.generateQRCodeImage(text, width, height);
        String qrCodeBase64 = Base64.getEncoder().encodeToString(qrCodeBytes);
        return "data:image/png;base64, " + qrCodeBase64;
    }
}
