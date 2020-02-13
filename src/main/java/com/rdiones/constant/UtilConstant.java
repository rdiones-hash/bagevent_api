package com.rdiones.constant;

import java.io.File;

/**
 * @Author: Rdiones
 * @Dscription:
 * @Date: Created in 9:03 2018/10/31
 * @Modified By:
 */
public class UtilConstant {
    private UtilConstant() {

    }

    // 通用
    public static final String CODE = "code";
    public static final String MSG = "msg";
    public static final String LOCATION = "location";
    public static final String FILE_PATH = "filePath";
    public static final String URL = "url";

    // 阿里oss前缀
    public static final String OSS_URL = "******************";

    // 本地上传参数
    public static final String FULL_DATE = "yyyyMMdd";

    public static final String UPLOAD_PATH = "opt" + File.separator + "upload" + File.separator + "hcwAdmin" + File.separator;

    public static final String CONTENT_TYPE = "application/json;charset=UTF-8";

    public static final String SEPA = File.separator;

    public static final String EXCEL_EXT = ".xls";

    public static final String PDF_EXT = ".pdf";

    public static final String EXCELX_EXT = ".xlsx";

    public static final String EXCEL_FOLDER = File.separator + "excel";

    public static final String OTHER_FOLDER = File.separator + "other";

    public static final String PDF_FOLDER = File.separator + "pdf";

    public static final String FILE_NAME = "fileName";

    public static final String PATH = "path";
}
