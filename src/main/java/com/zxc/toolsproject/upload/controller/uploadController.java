package com.zxc.toolsproject.upload.controller;

import com.zxc.toolsproject.commons.utils.FileUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class uploadController {
    @RequestMapping(value = "/uploadFolder", method = RequestMethod.POST)
    public String uploadFolder(MultipartFile[] folder) {
        FileUtil.saveMultiFile("D:/upload", folder);
        return "ok";
    }

}
