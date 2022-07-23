package top.kaluna.uploadfile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import top.kaluna.uploadfile.service.FileService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Yuery
 * @date 2022/6/8/0008 - 17:54
 */
@Controller
@RequestMapping("/index")
public class UploadController {
    @Resource
    private FileService fileService;

    @GetMapping("index")
    public String index(){
        System.out.println("index....");
        return "index";
    }
    @ResponseBody
    @RequestMapping("/upload")
    public String initOrder(@RequestParam("path") String path) {
        final String s = fileService.setPath(path);
        return s;
    }
}
