package service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import dao.CreditReportAccountMapper;
import exception.MyException;
import model.CreditReportAccount;
import service.CreditReportAccountService;
@Service
public class CreditReportAccountServiceImpl implements CreditReportAccountService {
	@Autowired
	private CreditReportAccountMapper creditReportAccountMapper;
	 /**
     * 删除账号信息
     *
     * @param id
     * @return
     * @throws Exception
     */
	@Override
	public boolean deleteByPrimaryKey(Long id) throws Exception {
		if(id==null) {
			 throw new MyException("参数异常");
		}
		CreditReportAccount creditReportAccount=null;
		try {
			creditReportAccount=creditReportAccountMapper.selectByPrimaryKey(id);
		}catch (Exception e) {
			 throw new MyException("查询异常");
		}
		
		if(creditReportAccount==null||"".equals(creditReportAccount.getUserName())) {
			throw new MyException("该账号不存在");
		}
		
		creditReportAccount.setStatus("2");
		creditReportAccount.setUpdatedAt(System.currentTimeMillis());
		try {
		return creditReportAccountMapper.updateByPrimaryKeySelective(creditReportAccount)==1;
		}catch (Exception e) {
			 throw new MyException("查询异常");
		}
	}

	 /**
     * 添加账号信息
     *
     * @param id
     * @return
     * @throws Exception
     */
	public boolean insertSelective(CreditReportAccount record) throws Exception {
		if("".equals(record.getUserName())||"".equals(record.getPassword())) {
			 throw new MyException("用户名或密码不能为空");
		}
		List<CreditReportAccount> list=new ArrayList<>();
		try {
			record.setId(null);
		list=creditReportAccountMapper.getListByUserNameOrId(record);
		}catch (Exception e) {
			 throw new MyException("操作异常");
		}
		if(list!=null &&list.size()>0) {
			throw new MyException("系统中已存在相同的账号记录");
		}
		long now=System.currentTimeMillis();
		record.setCreatedAt(now);
		record.setUpdatedAt(now);
		try {
		return creditReportAccountMapper.insertSelective(record)==1;
		}catch (Exception e) {
			 throw new MyException("保存操作异常");
		}
	}
	/**
     * 查询账号信息
     *
     * @param id
     * @return
     * @throws Exception
     */
	@Override
	public CreditReportAccount selectByPrimaryKey(Long id) throws Exception {
		if(id==null) {
			 throw new MyException("参数异常");
		}
	try {
		return creditReportAccountMapper.selectByPrimaryKey(id);
		}catch (Exception e) {
			 throw new MyException("查询操作异常");
		}
	}
	/**
     * 修改账号信息
     *
     * @param id
     * @return
     * @throws Exception
     */
	@Override
	public boolean updateByPrimaryKeySelective(CreditReportAccount record) throws Exception {
		
		List<CreditReportAccount> list=new ArrayList<>();
		try {
			
		list=creditReportAccountMapper.getListByUserNameOrId(record);
		}catch (Exception e) {
			 throw new MyException("操作异常");
		}
		if(list!=null &&list.size()>0) {
			throw new MyException("系统中已存在相同的账号记录");
		}
		record.setUpdatedAt(System.currentTimeMillis());
		try {
		return creditReportAccountMapper.updateByPrimaryKeySelective(record)==1;
		}catch (Exception e) {
			 throw new MyException("操作异常");
		}
	}
	/**
     * 分页和条件查询账户信息
     *
     * @param id
     * @return
     * @throws Exception
     */
	@Override
	public List<CreditReportAccount> getList(Map<String,Object> map) throws Exception {
		if(!map.containsKey("pageNum")) {
			throw new MyException("查询参数异常");
		}
		if(!map.containsKey("pageSize")) {
			throw new MyException("查询参数异常");
		}
		try {
			PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()), Integer.parseInt(map.get("pageSize").toString()));
			return	creditReportAccountMapper.getList(map);
		}catch(Exception e) {
			throw new MyException("查询参数异常");
		}
		
	}

}
