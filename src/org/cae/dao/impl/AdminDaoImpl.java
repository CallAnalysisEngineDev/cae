package org.cae.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.cae.common.DaoResult;
import org.cae.common.Generator;

import static org.cae.common.Util.*;

import org.cae.dao.IAdminDao;
import org.cae.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("adminDao")
public class AdminDaoImpl implements IAdminDao {

	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private JdbcTemplate template;

	@Override
	public Logger getLogger() {
		return logger;
	}

	@Override
	public DaoResult getAdminInfoDao(Admin admin) {
		String sql = "SELECT 1 " + "FROM admin "
				+ "WHERE admin_useraccount = ?";
		List<String> list = template.queryForList(sql,
				new Object[] { admin.getAdminUseraccount() }, String.class);
		if (list.size() == 0) {
			return new DaoResult(false, "管理员账号不存在");
		}
		sql = "SELECT admin_id " + "FROM admin "
				+ "WHERE admin_useraccount = ? " + "AND admin_password = ?";
		// 密码要进行md5处理后才和数据库的对比
		List<Admin> theResult = template.query(
				sql,
				new Object[] { admin.getAdminUseraccount(),
						md5(admin.getAdminPassword()) },
				new RowMapper<Admin>() {

					@Override
					public Admin mapRow(ResultSet rs, int row)
							throws SQLException {
						return Generator.admin(rs.getInt("admin_id"));
					}

				});
		if (isNotNull(theResult)) {
			return new DaoResult(true, theResult.get(0));
		} else {
			return new DaoResult(false, "密码错误");
		}
	}

}
