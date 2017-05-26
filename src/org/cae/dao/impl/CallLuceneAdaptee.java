package org.cae.dao.impl;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.cae.common.DaoResult;
import org.cae.entity.CallRecord;
import org.springframework.stereotype.Repository;

@Repository("callLuceneAdaptee")
public class CallLuceneAdaptee {

	private Logger logger=Logger.getLogger(this.getClass());
	
	//日文的unicode集合
	private Set<Character.UnicodeBlock> japaneseUnicodeBlocks = new HashSet<Character.UnicodeBlock>(){{
		add(Character.UnicodeBlock.HIRAGANA);
		add(Character.UnicodeBlock.KATAKANA);
		add(Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS);
	}};
	
	public DaoResult getAllCallLucene(CallRecord callRecord) {
		// TODO Auto-generated method stub
		return null;
	}

	public DaoResult saveCallLucene(CallRecord callRecord) {
		// TODO Auto-generated method stub
		return null;
	}

	public DaoResult deleteCallLucene(CallRecord callRecord) {
		// TODO Auto-generated method stub
		return null;
	}

	//判断一个字符串是英文还是日文
	private boolean enOrJp(String word){
		//先把字符串的特殊字符用正则消去,然后再判断是否纯英文
		boolean result=word.replaceAll("[^a-zA-Z_\u2E80-\uFE4F]", "").matches("^[a-zA-z]*");
		//如果是纯英文则返回true
		if(result){
			return result;
		}
		//如果不是纯英文则判断是否有日文,对每个字符进行轮询,一旦出现有日文则认为该字符串是日文并返回false
		for (char c : word.toCharArray()){
			if (japaneseUnicodeBlocks.contains(Character.UnicodeBlock.of(c))){
				result=false;
				break;
			}
		}
		return result;
	}
	
	public Logger getLogger(){
		return logger;
	}
}
