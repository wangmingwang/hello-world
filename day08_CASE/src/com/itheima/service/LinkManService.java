package com.itheima.service;

import com.itheima.bean.LinkMan;
import com.itheima.bean.Page;
import com.itheima.dao.LinkManDao;

import java.sql.SQLException;
import java.util.List;

public class LinkManService {

    private LinkManDao linkManDao = new LinkManDao();

    /**
     * 查询当前用户下的联系人
     * @param userid
     * @return
     */
    public List<LinkMan> findAllId(String userid) throws SQLException {

        List<LinkMan> list = linkManDao.findAllId(userid);

        return list;
    }

    /**
     * 添加联系人
     * @param linkMan
     * @return
     */
    public boolean add(LinkMan linkMan) throws SQLException {

        boolean result = linkManDao.add(linkMan);

        return result;
    }

    /**
     * 删除联系人根据ID
     * @param id
     * @return
     * @throws SQLException
     */
    public boolean deleteById(String id) throws SQLException {

        boolean result= linkManDao.deleteById(id);

        return result;
    }

    /**
     * 查找联系人根据ID
     * @param id
     * @return
     * @throws SQLException
     */
    public LinkMan findById(String id) throws SQLException {
        LinkMan linkMan = linkManDao.findById(id);
        return linkMan;
    }

    /**
     * 修改联系人信息
     * @param linkMan
     * @return
     * @throws SQLException
     */
    public boolean update(LinkMan linkMan) throws SQLException {
        boolean result = linkManDao.update(linkMan);

        return result;
    }

    public Page findAllPage(String userid, int currentpage, int pagesize) throws SQLException {
        Page<LinkMan> page = new Page<>();

        int totalsize =  linkManDao.sumById(userid);
        List<LinkMan> pageList = linkManDao.findAllPage(userid,currentpage,pagesize);

        page.setPagesize(pagesize);
        page.setCurrentpage(currentpage);
        page.setTotalpage((int) Math.ceil((totalsize*1.0)/pagesize));
        page.setPageList(pageList);

        System.out.println("page:"+page);

        return page;
    }
}
