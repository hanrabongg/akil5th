package com.ak.il5th.service.pdf;

import org.apache.tika.exception.TikaException;
import org.springframework.stereotype.Service;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.*;

@Service
public class TikaPDFParser {

    public String getTextFromPdfViaTika(String path) {
        String result = "";

        if (path == null || "".equals(path)) {
            return "대상 파일을 찾을 수 없습니다.";
        }

        File file = new File(path);
        BodyContentHandler handler = new BodyContentHandler();
        AutoDetectParser parser = new AutoDetectParser();
        Metadata metadata = new Metadata();

        try (InputStream stream = new FileInputStream(file)) {
            parser.parse(stream, handler, metadata);

            result = handler.toString();

            System.out.println("TIKA Start....\n"+result);

            if ("".equals(result.replaceAll(" ", ""))) {
                return "감지된 Text가 없습니다.";
            } else {
                String skipPrefix = "CONFIRM SHEET";
                String skipSuffix = "AEKYUNG DESIGN CENTER";

                if (result.indexOf(skipPrefix) > -1 && result.indexOf(skipSuffix) > -1) {
                    String skipText = result.substring(result.indexOf(skipPrefix), result.indexOf(skipSuffix)+skipSuffix.length());
                    result = result.replace(skipText, "");
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TikaException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return result;
    }
}
