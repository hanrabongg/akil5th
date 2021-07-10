package com.ak.il5th.service.pdf;

import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FilenameFilter;

@RequiredArgsConstructor
@Service
public class PDFBoxService {



    public void imagesToPDF(String sourceDir, String saveTargetDir, String savePDFFileName) throws Exception {
        File dir = new File(sourceDir);

        File[] sourceFiles = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return dir.getName().contains(".jpg");
            }
        });

        File pdfFile = createPDF(saveTargetDir, savePDFFileName);
        PDDocument doc = PDDocument.load(pdfFile);

        int totalCnt = 1;
        for (File curImgFile : sourceFiles) {
            Image curImg = ImageIO.read(curImgFile);

            float imgWidth = curImg.getWidth(null);
            float imgHeight = curImg.getHeight(null);

            PDImageXObject pdImage = PDImageXObject.createFromFileByContent(curImgFile, doc);
            PDRectangle newRect = new PDRectangle(0, 0, imgWidth, imgHeight);
            PDPage newPage = new PDPage(newRect);
            doc.addPage(newPage);

            PDPageContentStream contents = new PDPageContentStream(doc, newPage);
            contents.drawImage(pdImage, 0, 0, imgWidth, imgHeight);
            contents.close();
        }

        doc.save(pdfFile);

        doc.close();

    }

    public File createPDF(String dirPath, String fileName) throws Exception {

        // Directory check
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdir();
        }

        if (!dir.isDirectory()) {
            throw new Exception("dirPath is not exist");
        }

        // File check
        File pdfFile = new File(dirPath + File.separator + fileName + ".pdf");
        if (pdfFile.exists()) {
            pdfFile.delete();
        }

        PDDocument document = new PDDocument();
        document.save(pdfFile);
        document.close();

        System.out.println("PDF created");

        return pdfFile;

    }





}
