package com.ak.il5th.service.pdf;

import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.cloud.vision.v1.Image;
import com.google.protobuf.ByteString;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;



@Service
public class ConnectVisionAPI {

    public String getTextFromImgViaVisionAPI(String path) {

        String result = "";

        try {

            if (path == null || "".equals(path)) {
                return "대상 파일을 찾을 수 없습니다.";
            }

            List<AnnotateImageRequest> requests = new ArrayList<>();

            ByteString imgBytes = ByteString.readFrom(new FileInputStream(path));

            Image img = Image.newBuilder().setContent(imgBytes).build();
            Feature feat = Feature.newBuilder().setType(Type.TEXT_DETECTION).build();
            AnnotateImageRequest request = AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
            requests.add(request);

            try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
                BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
                List<AnnotateImageResponse> responses = response.getResponsesList();

                for (AnnotateImageResponse res : responses) {
                    if (res.hasError()) {
                        System.out.printf("Error: %s\n", res.getError().getMessage());
                    }

                    result = res.getTextAnnotationsList().get(0).getDescription();

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

                    break;

                    // For full list of available annotations, see http://g.co/cloud/vision/docs
			    	/*for (EntityAnnotation annotation : res.getTextAnnotationsList()) {

						//System.out.printf("Text: %s\n", annotation.getDescription());
						//System.out.printf("Position : %s\n", annotation.getBoundingPoly());
					}*/
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        return result;
    }


}
