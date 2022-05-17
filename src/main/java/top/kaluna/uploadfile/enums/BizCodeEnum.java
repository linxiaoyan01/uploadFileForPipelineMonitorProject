package top.kaluna.uploadfile.enums;

import lombok.Getter;

/**
 * @author Yuery
 * @date 2022/5/14/0014 - 18:38
 */
public enum BizCodeEnum {
    /**
     * 文件相关
     */
    FILE_UPLOAD_FAIL(600101,"用户头像文件上传失败");

    @Getter
    private String message;

    @Getter
    private int code;

    private BizCodeEnum(int code, String message){
        this.code = code;
        this.message = message;
    }

}
