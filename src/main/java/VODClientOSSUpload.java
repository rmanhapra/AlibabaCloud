/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.google.gson.JsonObject;
import org.bouncycastle.util.encoders.Base64;

import java.io.File;

/**
 * @author rakeshramanath.m
 * @version $Id: VODClientOSSUpload.java, v 0.1 2022-02-11 6:03 PM rakeshramanath.m Exp $$
 *
 * Use Upload credentials recieved from VOD SDK response and use OSS sdk to upload media files
 */
public class VODClientOSSUpload {

    public static void UploadFromOSS(String videoId, String uploadAddress, String uploadAuth){

        JSONObject uAuth = JSONObject.parseObject(new String(Base64.decode(uploadAuth)));
        JSONObject uAddr = JSONObject.parseObject(new String(Base64.decode(uploadAddress)));
        // Use UploadAuth and UploadAddress to initialize the OSS client.
        OSSClient ossClient = initOssClient(uAuth, uAddr);
        uploadLocalFile(ossClient,uAddr,"<local media file path>");
    }

    private static OSSClient initOssClient(JSONObject uploadAuth, JSONObject uploadAddress) {
        String endpoint = uploadAddress.getString("Endpoint");
        String accessKeyId = uploadAuth.getString("AccessKeyId");
        String accessKeySecret = uploadAuth.getString("AccessKeySecret");
        String securityToken = uploadAuth.getString("SecurityToken");
        return new OSSClient(endpoint, accessKeyId, accessKeySecret, securityToken);

    }

    private static void uploadLocalFile(OSSClient ossClient, JSONObject uploadAddress, String localFile){
        String bucketName = uploadAddress.getString("Bucket");
        String objectName = uploadAddress.getString("FileName");
        File file = new File(localFile);
        ossClient.putObject(bucketName, objectName, file);
    }
}