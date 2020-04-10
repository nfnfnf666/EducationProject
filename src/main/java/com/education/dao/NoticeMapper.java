package com.education.dao;

import com.education.pojo.Notice;

import java.util.List;
import java.util.Map;

public interface NoticeMapper {
    List<Notice> selectAllNotice(Integer courseListId);

    List<Notice> selectNotices(Map map);

    int deleteByPrimaryKey(Integer noticeId);

    int insert(Notice record);

    int insertSelective(Notice record);

    Notice selectByPrimaryKey(Integer noticeId);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKeyWithBLOBs(Notice record);

    int updateByPrimaryKey(Notice record);
}