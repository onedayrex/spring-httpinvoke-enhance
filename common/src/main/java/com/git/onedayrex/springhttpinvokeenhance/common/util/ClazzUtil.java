package com.git.onedayrex.springhttpinvokeenhance.common.util;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ClazzUtil {

    public static final String CLASS_ENDFIX = ".class";

    /**
     * 通过包名获取包下所有class
     * @param packageName
     * @return
     */
    public static List<Class> getPackageClazz(String packageName) throws FileNotFoundException {
        //获取classpath路径名称
        String clazzPath = ResourceUtils.getURL(ResourceUtils.CLASSPATH_URL_PREFIX).getPath();
        //把包名转化成路径
        String path = packageName.replace(".", File.separator);
        String packagePath = clazzPath + path;
        File file = new File(packagePath);
        List<Class> classList = new ArrayList<>();
        return searchPath(clazzPath, file, classList);
    }

    private static List<Class> searchPath(String clazzPath,File file,List<Class> classList) {
        if (file == null || !file.exists()) {
            return classList;
        }
        if (file.isDirectory()) {
            //如果是目录继续向下遍历
            File[] files = file.listFiles();
            for (File childFile : files) {
                searchPath(clazzPath, childFile, classList);
            }
            return classList;
        }else {
            if (file.getName().endsWith(CLASS_ENDFIX)) {
                String clazzPackageName = file.getPath().replace(clazzPath.replace("/", "\\").replaceFirst("\\\\", ""), "").replace("\\", ".").replace(".class", "");
                try {
                    Class<?> scanClazz = Class.forName(clazzPackageName);
                    classList.add(scanClazz);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            return classList;
        }
    }


}
