package com.education.service;

import com.education.dao.NoticeMapper;
import com.education.pojo.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NoticeService {
    @Autowired
    NoticeMapper noticeMapper;

    public List<Notice> selectAllNotice(Integer courseListId){
        return noticeMapper.selectAllNotice(courseListId);
    }

    public List<Notice> selectNotices(String courselistId, String noticeTitle, String noticeContent){
        Map<String, String> map = new HashMap<>();
        map.put("courselistId", courselistId);
        map.put("noticeTitle", noticeTitle);
        map.put("noticeContent", noticeContent);
        return noticeMapper.selectNotices(map);
    }

    public int deleteNoticeById(Integer noticeId){
        return noticeMapper.deleteByPrimaryKey(noticeId);
    }

    public Notice queryNoticeById(Integer noticeId){
        return noticeMapper.selectByPrimaryKey(noticeId);
    }

    public int update(Notice notice){
        return noticeMapper.updateByPrimaryKeyWithBLOBs(notice);
    }

    public int insert(Notice notice){
        return noticeMapper.insert(notice);
    }

}
