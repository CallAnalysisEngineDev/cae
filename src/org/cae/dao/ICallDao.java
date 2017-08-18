package org.cae.dao;

import java.util.List;

import org.cae.common.Condition;
import org.cae.common.DaoResult;
import org.cae.entity.CallRecord;

public interface ICallDao extends IBaseDao {

	DaoResult getAllCallDao(Condition condition, CallRecord callRecord);

	DaoResult getCallCountDao(Condition condition, CallRecord callRecord);

	DaoResult getCallDao(CallRecord callRecord);

	DaoResult saveCallDao(CallRecord callRecord);

	DaoResult deleteCallDao(CallRecord callRecord);

	DaoResult deleteCallDao(List<CallRecord> callRecords);

}
