package top.kaluna.uploadfile.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.UploadFileRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.kaluna.uploadfile.config.OSSConfig;
import top.kaluna.uploadfile.domain.LocalFilePath;
import top.kaluna.uploadfile.domain.LocalFilePathExample;
import top.kaluna.uploadfile.mapper.LocalFilePathMapper;
import top.kaluna.uploadfile.service.FileService;
import top.kaluna.uploadfile.util.FileUtil;


import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author Yuery
 * @date 2022/5/14/0014 - 20:12
 */
@Service
@Slf4j
public class FileServiceImpl implements FileService {
    @Resource
    private OSSConfig ossConfig;

    @Resource
    private LocalFilePathMapper localFilePathMapper;


    /**
     * 上传本地某个文件夹所有文件
     *
     */
    @Override
    public void fileUpload() {
        //获取相关配置
        String bucketname = ossConfig.getBucketname();
        String endpoint = ossConfig.getEndpoint();
        String accessKeyId = ossConfig.getAccessKeyId();
        String accessKeySecret = ossConfig.getAccessKeySecret();

        LocalFilePathExample example = new LocalFilePathExample();
        LocalFilePathExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(1) ;
        final List<LocalFilePath> localFilePaths = localFilePathMapper.selectByExample(example);

        String path  = localFilePaths.get(0).getPath();
        //JDK8的日期格式
        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        //拼装路径,oss上存储的路径  data/2022/5/17/log2022-5-9-16-43-55.xlsx
        String folder = dtf.format(ldt);
        //获取原始文件名
        final List<String> fileNames = FileUtil.getFileNames(path);
        //创建OSS对象
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ObjectMetadata meta = new ObjectMetadata();

        // 指定上传的内容类型。
        meta.setContentType("text/plain");
        try {
            fileNames.forEach((originalFileName)->{
            // 在OSS上的bucket下创建 data 这个文件夹
            String newFileName = "data/"+folder+"/"+originalFileName;

                // 文件上传时设置访问权限ACL。
                // meta.setObjectAcl(CannedAccessControlList.Private);
                // 通过UploadFileRequest设置多个参数。
                // 依次填写Bucket名称（例如examplebucket）以及Object完整路径（例如exampledir/exampleobject.txt），Object完整路径中不能包含Bucket名称。
                UploadFileRequest uploadFileRequest = new UploadFileRequest(bucketname,newFileName);

                // 通过UploadFileRequest设置单个参数。
                // 填写本地文件的完整路径，例如 D:\\localpath\\examplefile.txt。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件。
                uploadFileRequest.setUploadFile(path+"\\"+originalFileName);
                // 指定上传并发线程数，默认值为1。
                uploadFileRequest.setTaskNum(5);
                // 指定上传的分片大小，单位为字节，取值范围为100 KB~5 GB。默认值为100 KB。
                uploadFileRequest.setPartSize(1024 * 1024);
                // 开启断点续传，默认关闭。
                uploadFileRequest.setEnableCheckpoint(true);
                // 记录本地分片上传结果的文件。上传过程中的进度信息会保存在该文件中，如果某一分片上传失败，再次上传时会根据文件中记录的点继续上传。上传完成后，该文件会被删除。
                // 如果未设置该值，默认与待上传的本地文件同路径，名称为${uploadFile}.ucp。
                uploadFileRequest.setCheckpointFile("yourCheckpointFile");
                // 文件的元数据。
                uploadFileRequest.setObjectMetadata(meta);
                // 设置上传回调，参数为Callback类型。
                //uploadFileRequest.setCallback("yourCallbackEvent");
                // 断点续传上传。
                try {
                    ossClient.uploadFile(uploadFileRequest);
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }

            } );
        }catch (OSSException oe) {
                System.out.println("Caught an OSSException, which means your request made it to OSS, "
                        + "but was rejected with an error response for some reason.");
                System.out.println("Error Message:" + oe.getErrorMessage());
                System.out.println("Error Code:" + oe.getErrorCode());
                System.out.println("Request ID:" + oe.getRequestId());
                System.out.println("Host ID:" + oe.getHostId());

        } catch (Throwable ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            // 关闭OSSClient。
            if (ossClient != null) {
                ossClient.shutdown();
            }
        };
    }

    @Override
    public void deleteFile() {
        LocalFilePathExample example = new LocalFilePathExample();
        LocalFilePathExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(1) ;
        final List<LocalFilePath> localFilePaths = localFilePathMapper.selectByExample(example);
        String path  = localFilePaths.get(0).getPath();
        FileUtil.deleteFile(path);
    }

    @Override
    public String setPath(String path) {
        LocalFilePathExample example = new LocalFilePathExample();
        LocalFilePathExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(1) ;
        final List<LocalFilePath> localFilePaths = localFilePathMapper.selectByExample(example);
        String youqingtishi = " 每周日23:00将会定时将指定目录的文件上传到阿里云OSS并删除这个文件夹下的所有文件释放存储空间。";
        if(localFilePaths.size() == 1){
            //更新
            String oldPath =localFilePaths.get(0).getPath();
            localFilePaths.get(0).setPath(path);
            localFilePathMapper.updateByExample(localFilePaths.get(0),example);

            return "更新完成，旧的路径："+oldPath+" 更新为："+path + youqingtishi;
        }else if(localFilePaths.size() == 0){
            LocalFilePath localFilePath = new LocalFilePath();
            localFilePath.setPath(path);
            localFilePath.setId(1);
            localFilePathMapper.insert(localFilePath);
            return "提交完成，路径为："+path + youqingtishi;
        }
        return "后端异常，请联系管理员。旧的路径为："+localFilePaths.get(0).getPath();
    }
}
