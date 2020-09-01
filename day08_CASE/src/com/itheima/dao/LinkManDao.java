package com.itheima.dao;

import com.itheima.bean.LinkMan;
import com.itheima.utils.C3p0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class LinkManDao {

    private QueryRunner queryRunner = new QueryRunner();




    /**
     * 查询当前用户下所有的联系人
     * @param userid
     * @return
     * @throws SQLException
     */
    public List<LinkMan> findAllId(String userid) throws SQLException {

        String sql = "select * from linkman where userid=?";
        List<LinkMan> list = queryRunner.query(C3p0Util.getConn(), sql, new BeanListHandler<>(LinkMan.class), userid);
        return list;
    }

    /**
     * 添加联系人
     * @param linkMan
     * @return
     */
    public boolean add(LinkMan linkMan) throws SQLException {

        String sql = "insert into linkman values(null,?,?,?,?,?,?,?)";
        int result = queryRunner.update(C3p0Util.getConn(), sql,
                linkMan.getUserid(),
                linkMan.getName(),
                linkMan.getSex(),
                linkMan.getAge(),
                linkMan.getAddress(),
                linkMan.getQq(),
                linkMan.getEmail());
        return result != 0;
    }

    /**
     * 删除联系人根据ID
     * @param id
     * @return
     * @throws SQLException
     */
    public boolean deleteById(String id) throws SQLException {

        String sql ="delete from linkman where id=?";
        int result = queryRunner.update(C3p0Util.getConn(),sql,id);

        return result!=0;
    }

    /**
     * 查找联系人根据用户ID
     * @param id
     * @return
     * @throws SQLException
     */
    public LinkMan findById(String id) throws SQLException {
        String sql = "select * from linkman where id=?";
        LinkMan linkMan = queryRunner.query(C3p0Util.getConn(),sql,new BeanHandler<>(LinkMan.class), id);
        return linkMan;
    }

    /**
     * 修改联系人
     * @param linkMan
     * @return
     * @throws SQLException
     */
    public boolean update(LinkMan linkMan) throws SQLException {

        String sql = "update linkman set name=?,sex=?,age=?,address=?,qq=?,email=?  where id=?";
        int result = queryRunner.update(C3p0Util.getConn(), sql,
                linkMan.getName(),
                linkMan.getSex(),
                linkMan.getAge(),
                linkMan.getAddress(),
                linkMan.getQq(),
                linkMan.getEmail(),
                linkMan.getId());
        return result != 0;
    }

    public int sumById(String userid) throws SQLException {

        String sql = "select count(userid) from linkman where userid=?";
        long sum = (long) queryRunner.query(C3p0Util.getConn(),sql,new ScalarHandler(),userid);
        return (int) sum;
    }

    public List<LinkMan> findAllPage(String userid, int currentpage, int pagesize) throws SQLException {

        String sql = "select * from linkman where userid=? limit ?,?";

        List<LinkMan> pageList = queryRunner.query(C3p0Util.getConn(), sql, new BeanListHandler<>(LinkMan.class), userid, (currentpage - 1) * pagesize, pagesize);

        return pageList;
    }
}
