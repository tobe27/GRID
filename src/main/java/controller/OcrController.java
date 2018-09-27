package controller;

import model.ResponseData;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import util.OcrUtil;

import java.io.File;

@RestController
@RequestMapping
public class OcrController {

    /**
     * 调用此接口识别身份证信息
     * @param image
     * @param idCardSide 1-正面（头像面），2-反面
     * @return
     */
    @RequestMapping(value = "/ocr/idcard/{idCardSide}", method = RequestMethod.POST)
    public ResponseData ocrIdCard(@RequestParam MultipartFile image, @PathVariable Integer idCardSide) throws Exception {
        CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) image;
        DiskFileItem diskFileItem = (DiskFileItem) commonsMultipartFile.getFileItem();
        File file = diskFileItem.getStoreLocation();
        String data = OcrUtil.getStringIdentityCard(file, idCardSide);
        return new ResponseData().success().data(OcrUtil.getJsonIdCardInfo(data));

    }
}
