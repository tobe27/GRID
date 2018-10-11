package controller;

import model.ResponseData;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import util.OcrUtil;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
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
        return new ResponseData().success().data(OcrUtil.getIdCardInfo(image, idCardSide));
    }
}
