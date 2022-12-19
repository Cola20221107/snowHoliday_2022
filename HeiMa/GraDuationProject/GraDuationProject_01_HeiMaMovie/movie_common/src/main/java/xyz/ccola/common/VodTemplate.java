package xyz.ccola.common;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * @ Name: VodTemplate
 * @ Author: Cola
 * @ Time: 2022/12/19 17:46
 * @ Description: VodTemplate
 */
@Component
public class VodTemplate {
    @Autowired
    private VodConfig vodConfig;

    /**
     * 初始化 Vod 客户端对象
     * @return Vod 对象
     */
    public DefaultAcsClient initVodClient() {
        DefaultProfile profile = DefaultProfile.getProfile(
                vodConfig.getRegionId(),
                vodConfig.getAccessKeyId(),
                vodConfig.getAccessKeySecret());
        return new DefaultAcsClient(profile);
    }

    /**
     * 上传视频
     * @param fileName 文件名
     * @param inputStream 输入流
     * @return 存储库中文件的 id
     */
    public String uploadVideo(String fileName, InputStream inputStream) {
        String title = fileName.substring(0, fileName.lastIndexOf("."));
        UploadStreamRequest request = new UploadStreamRequest(
                vodConfig.getAccessKeyId(),
                vodConfig.getAccessKeySecret(),
                title,
                fileName,
                inputStream);
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadStreamResponse response = uploader.uploadStream(request);
        String videoId = response.getVideoId();
        return videoId;
    }

    /**
     * 获取播放凭证 默认有效期为 2h 防止盗链
     * @param videoId 存储库中文件的 id
     * @return 播放凭证
     * @throws Exception 异常
     */
    public GetVideoPlayAuthResponse getVideoPlayAuth(String videoId) throws Exception {
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId(videoId);
        return initVodClient().getAcsResponse(request);
    }
}
