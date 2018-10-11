package util;

import exception.MyException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * File与MultipartFile相互转化
 * @author Created by L.C.Y on 2018-9-28
 */
public class FileConvertUtil {

    /**
     * MultipartFile转化为File
     * @param multipartFile
     * @return
     */
    public static File convertMulti2File(MultipartFile multipartFile) {
        CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) multipartFile;
        DiskFileItem diskFileItem = (DiskFileItem) commonsMultipartFile.getFileItem();
        return diskFileItem.getStoreLocation();
    }

    /**
     * File转化为MultipartFile
     * @param file
     * @return
     */
    public static MultipartFile convertFile2Multi(File file) {
        FileInputStream inputStream;
        MultipartFile multipartFile;
        try {
            inputStream = new FileInputStream(file);
            multipartFile = new MockMultipartFile("file",file.getName(),"text/plain", IOUtils.toByteArray(inputStream));
        } catch (IOException e) {
            throw new MyException("File转换MultipartFile出现异常");
        }
        return multipartFile;
    }


}
