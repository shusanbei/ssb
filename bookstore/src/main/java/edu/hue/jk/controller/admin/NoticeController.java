package edu.hue.jk.controller.admin;

import edu.hue.jk.mapper.NoticeMapper;
import edu.hue.jk.model.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/notices")
public class NoticeController {
    @Autowired
    private NoticeMapper noticeMapper;

    @RequestMapping("list")
    public String list(Model model) {
        List<Notice> noticeList = noticeMapper.findAll();
        model.addAttribute("noticeList", noticeList);
        return "admin/notices/list";
    }

    @RequestMapping("del")
    public String del(Model model, int nId) {
        noticeMapper.delete(nId);

        List<Notice> noticeList = noticeMapper.findAll();
        model.addAttribute("noticeList", noticeList);
        return "admin/notices/list";
    }

    @RequestMapping("add")
    public String add(Model model) {
        Notice notice = new Notice();
        model.addAttribute("notice", notice);
        return "admin/notices/add";
    }

    @RequestMapping("edit")
    public String edit(Model model, int nId) {
        Notice notice = noticeMapper.findById(nId);
        model.addAttribute("notice", notice);
        return "admin/notices/edit";
    }

    @RequestMapping("save")
    public String save(Model model, Notice notice) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String now = df.format(new Date());

        boolean isNew = notice.getNId() == null;
        if (isNew) {
            notice.setNTime(now);
            noticeMapper.insert(notice);
        } else {
            notice.setNTime(now);
            noticeMapper.update(notice);
        }

        List<Notice> noticeList = noticeMapper.findAll();
        model.addAttribute("noticeList", noticeList);
        return "admin/notices/list";
    }
}
