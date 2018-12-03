package controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import model.CustomerInfo;
import model.ResponseData;
import model.TagCustomer;
import model.TagInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.TagCustomerService;
import util.ValidUtil;

import java.util.List;
import java.util.Set;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
@RestController
@RequestMapping
public class TagCustomerController {
    @Autowired
    TagCustomerService tagCustomerService;

    private Logger logger = LoggerFactory.getLogger(TagCustomerController.class);

    /**
     * 调用此接口为客户添加标签
     * @param idNumber
     * @param tagIds 标签ID，多个标签以，隔开
     * @return
     */
    @RequestMapping(value = "/customer/{idNumber}/tag", method = RequestMethod.POST)
    public ResponseData insertTagForCustomer(@PathVariable String idNumber, String tagIds) throws Exception {
        if (tagCustomerService.insertSelective(idNumber, tagIds)) {
            return new ResponseData().success();
        }
        return new ResponseData().fail("添加标签异常");

    }

    /**
     * 调用此接口删除客户所有标签
     * @param idNumber
     * @return
     */
    @RequestMapping(value = "/customer/{idNumber}/tag", method = RequestMethod.DELETE)
    public ResponseData deleteTagsForCustomer(@PathVariable String idNumber) throws Exception {

        tagCustomerService.deleteTagByIdNumber(idNumber);
        return new ResponseData().success();

    }

    /**
     * 调用此接口删除客户一个标签
     * @param idNumber
     * @return
     */
    @RequestMapping(value = "/customer/{idNumber}/tag/{tagId}", method = RequestMethod.DELETE)
    public ResponseData deleteTagForCustomer(@PathVariable String idNumber, @PathVariable Long tagId) throws Exception {
        TagCustomer tagCustomer = new TagCustomer();
        tagCustomer.setTagId(tagId);
        tagCustomer.setIdNumber(idNumber);
        tagCustomerService.deleteTagByIdNumberAndTagId(tagCustomer);
        return new ResponseData().success();

    }

    /**
     * 调用此接口获取客户所有标签
     * @param idNumber
     * @return
     */
    @RequestMapping(value = "/customer/{idNumber}/tag/list", method = RequestMethod.GET)
    public ResponseData listTagsForCustomer(@PathVariable String idNumber) throws Exception {

        List<TagCustomer> list = tagCustomerService.listTagsByIdNumber(idNumber);
        return new ResponseData().success().data(list);

    }

    /**
     * 调用此接口获取该标签下的客户数
     * @param tagId
     * @return
     */
    @RequestMapping(value = "/customer/count/tag/{tagId}", method = RequestMethod.GET)
    public ResponseData countTagForCustomer(@PathVariable Long tagId) throws Exception {

        int count = tagCustomerService.countCustomersByTagId(tagId);
        return new ResponseData().success().data(count);

    }

    /**
     * 调用此接口获取该标签下的客户信息
     * @param tagId
     * @return
     */
    @RequestMapping(value = "/customer/list/tag/{tagId}", method = RequestMethod.GET)
    public ResponseData listCustomersByTag(@PathVariable Long tagId, Integer pageNum, Integer pageSize) throws Exception {
        if (ValidUtil.isEmpty(pageNum) || ValidUtil.isEmpty(pageSize)) {
            return new ResponseData().fail("页码与页行数不能为空!");
        }

        PageHelper.startPage(pageNum, pageSize);
        List<CustomerInfo> list = tagCustomerService.listCustomersByTagId(tagId);
        PageInfo<CustomerInfo> pageInfo = new PageInfo<>(list);
        return new ResponseData().success().result("count", pageInfo.getTotal()).data(pageInfo.getList());

    }


}
