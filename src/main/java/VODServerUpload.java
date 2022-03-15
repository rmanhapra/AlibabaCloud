/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;

/**
 * @author rakeshramanath.m
 * @version $Id: VODServerUpload.java, v 0.1 2022-02-11 3:20 PM rakeshramanath.m Exp $$
 * Refer "https://www.alibabacloud.com/help/en/doc-detail/53406.htm?spm=a2c63.p38356.0.0.3c9c12fcxbWeOJ#task-1995280"
 * for call back implementations
 */
public class VODServerUpload {
  public static UploadVideoResponse UploadFromServer(String accessId, String accessKey,String title, String fileName){
      System.out.println("Uploading media to server...");

      UploadVideoRequest uploadRequest = new UploadVideoRequest(accessId,accessKey,title,fileName);

      uploadRequest.setPartSize(2 * 1024 * 1024L);
      uploadRequest.setTaskNum(1);
      uploadRequest.setApiRegionId("ap-south-1");
      uploadRequest.setPrintProgress(false);
      UploadVideoImpl uploader = new UploadVideoImpl();
      UploadVideoResponse response = uploader.uploadVideo(uploadRequest);

      System.out.println(JSON.toJSON(response));
      return response;
  }
}