package com.shu.cashbook.common;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * @version 1.0
 * @author: yang
 * @date: 2019/2/22 15:52
 */
public class ProjectPath {

    public static File getFile() {
        File path = null;//根目录
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        File uploadPath = new File(path.getAbsolutePath(), "static/images/upload/");//图片目录
        return uploadPath;
    }
}
