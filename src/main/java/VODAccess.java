/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */

/**
 * @author rakeshramanath.m
 * @version $Id: VODAccess.java, v 0.1 2022-02-10 5:59 PM rakeshramanath.m Exp $$
 */

import com.alibaba.fastjson.JSON;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.CreateUploadVideoResponse;
import com.aliyuncs.vod.model.v20170321.UploadMediaByURLRequest;
import com.aliyuncs.vod.model.v20170321.UploadMediaByURLResponse;

public class VODAccess {

    public static void main (String[] args){
        DefaultAcsClient client =  initProfile();

        // Use VODUpload SDK to Upload media files to VOD

        UploadVideoResponse resp = VODServerUpload.UploadFromServer("<accessid>","<accesskey>","ServerUpload","local file path");
        String transcodeStataus = CommonOperations.IsTranscodingCompleted(resp.getVideoId(),client);
        String transcodeStataus1 = CommonOperations.QueryTranscodingTask(resp.getVideoId(),client);
        CommonOperations.GetPlayURL("",client);


        //Use VOD server SDK and OSS SDK to Upload vidoes to VOD

        CreateUploadVideoResponse resp1 = CommonOperations.GetUplodURLForLCient(client,"Client Upload Server","clientvideoupload.mp4");
        VODClientOSSUpload.UploadFromOSS(resp1.getVideoId(), resp1.getUploadAddress(), resp1.getUploadAuth());

        //Use VOD Server SDK and response url and adress in Javascript player SDK
        CreateUploadVideoResponse resp3 = CommonOperations.GetUplodURLForLCient(client,"Web client upload", "webclientbrewing.mp4");




        // Use VOD Server SDK to directly upload video from source  url to destination url
        UploadMediaByURLRequest urlMediaRequest = new UploadMediaByURLRequest();
        String inputVideo = "<source videp url>";
        urlMediaRequest.setUploadURLs(inputVideo);
        UploadMediaByURLResponse response = UploadVideoAssetFromServer(client,urlMediaRequest);


        //Use VOD server SDK to register media files which are already uploaded in OSS directly
        VODExternalMediaRegistration.OSSMediaRegistration("<oss media url>","ossUploadBrewing",client);
    }
    /*
    Initialise VOD sdk
     */
    private static DefaultAcsClient initProfile(){
        String regionId = "";  // The region where you want to call ApsaraVideo VOD operations.
        DefaultProfile profile = DefaultProfile.getProfile(regionId, "", "");
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }





    /*
    Upload Video By URL
     */
    private static UploadMediaByURLResponse UploadVideoAssetFromServer (DefaultAcsClient client, UploadMediaByURLRequest request){
        try {
            UploadMediaByURLResponse response = client.getAcsResponse(request);
            System.out.println(JSON.toJSON(response));
            return response;
        }  catch (Exception e) {
            System.out.println("ErrCode:" + e.getMessage());
            return null;
        }
    }

}