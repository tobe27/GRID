package controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import model.ResponseData;
import model.TagInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.TagInfoService;
import util.ValidUtil;

import java.util.List;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
@RestController
@RequestMapping
public class TagInfoController {
    @Autowired
    TagInfoService tagInfoService;

    private Logger logger = LoggerFactory.getLogger(TagInfoController.class);

    /**
     * 调用此接口新增标签信息
     * @param info
     * @return
     */
    @RequestMapping(value = "/customer/tag", method = RequestMethod.POST)
    public ResponseData insertTag(TagInfo info) throws Exception {

        tagInfoService.insertSelective(info);
        return new ResponseData().success();

    }

    /**
     * 调用此接口编辑标签信息
     * @param info
     * @return
     */
    @RequestMapping(value = "/customer/tag/{tagId}", method = RequestMethod.PUT)
    public ResponseData updateTag(TagInfo info) throws Exception {

        tagInfoService.updateByPrimaryKeySelective(info);
        return new ResponseData().success();

    }

    /**
     * 调用此接口删除标签信息
     * @param tagId
     * @return
     */
    @RequestMapping(value = "/customer/tag/{tagId}", method = RequestMethod.DELETE)
    public ResponseData deleteTag(@PathVariable Long tagId) throws Exception {

        tagInfoService.deleteByPrimaryKey(tagId);
        return new ResponseData().success();

    }

    /**
     * 分页查询标签信息列表
     * @param info
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/customer/tag/list", method = RequestMethod.GET)
    public ResponseData listTags(TagInfo info, Integer pageNum, Integer pageSize) throws Exception {
        if (ValidUtil.isEmpty(pageNum) || ValidUtil.isEmpty(pageSize)) {
            return new ResponseData().fail("页码与页行数不能为空");
        }

        PageHelper.startPage(pageNum, pageSize);
        List<TagInfo> list = tagInfoService.listTags(info);
        PageInfo<TagInfo> pageInfo = new PageInfo<>(list);
        return new ResponseData().success().result("count", pageInfo.getTotal()).data(pageInfo.getList());

    }

    /**
     * 获取标签信息详情
     * @param tagId
     * @return
     */
    @RequestMapping(value = "/customer/tag/{tagId}", method = RequestMethod.GET)
    public ResponseData listTags(@PathVariable Long tagId) throws Exception {

        TagInfo info = tagInfoService.selectByPrimaryKey(tagId);
        return new ResponseData().success().data(info);

    }
}
