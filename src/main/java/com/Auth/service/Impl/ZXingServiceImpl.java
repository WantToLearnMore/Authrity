package com.Auth.service.Impl;

import com.Auth.service.ZXingService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jiangtenglong on 2016/8/15.
 */
@Service
public class ZXingServiceImpl implements ZXingService {
    public  void create(String content) {
        String path="ZXing";
        String fileName="ZX.png";
        int wildth=300;
        int height=300;

        File file=new File(path);
        if(!file.exists()){
            file.mkdir();
        }else{
            System.out.println("文件夹已经存在");
        }

        MultiFormatWriter multiFormatWriter=new MultiFormatWriter();

        Map hints= new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET,"UTF-8");
        try {
            BitMatrix bitMatrix=multiFormatWriter.encode(content, BarcodeFormat.QR_CODE,wildth,height,hints);

                Path  filePath= FileSystems.getDefault().getPath(path, fileName);
                MatrixToImageWriter.writeToPath(bitMatrix,"png", filePath);
            } catch (IOException e) {
                e.printStackTrace();
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
