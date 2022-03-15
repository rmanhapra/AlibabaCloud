/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.CreateUploadVideoRequest;
import com.aliyuncs.vod.model.v20170321.CreateUploadVideoResponse;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetTranscodeSummaryRequest;
import com.aliyuncs.vod.model.v20170321.GetTranscodeSummaryResponse;
import com.aliyuncs.vod.model.v20170321.ListTranscodeTaskRequest;
import com.aliyuncs.vod.model.v20170321.ListTranscodeTaskResponse;
import com.google.gson.JsonParser;

import java.util.Base64;
import java.util.List;

/**
 * @author rakeshramanath.m
 * @version $Id: CommonOperations.java, v 0.1 2022-02-11 5:06 PM rakeshramanath.m Exp $$
 */
public class CommonOperations {

  /*

   */
  public static String IsTranscodingCompleted(String videoId, DefaultAcsClient client){
    GetTranscodeSummaryRequest  request = new GetTranscodeSummaryRequest();
    request.setVideoIds(videoId);
    try {
      GetTranscodeSummaryResponse response = client.getAcsResponse(request);
      System.out.println(JSON.toJSON(response));
      List<GetTranscodeSummaryResponse.TranscodeSummary> summaryList =  response.getTranscodeSummaryList();
      GetTranscodeSummaryResponse.TranscodeSummary summary = summaryList.get(0);
      return summary.getTranscodeStatus();
    }catch (Exception e) {
      System.out.println("ErrCode:" + e.getMessage());
      return null;
    }
  }

  /*

   */
  public static String QueryTranscodingTask(String videoId, DefaultAcsClient client){
    ListTranscodeTaskRequest req = new ListTranscodeTaskRequest();
    req.setVideoId(videoId);
    try {
      ListTranscodeTaskResponse response = client.getAcsResponse(req);
      System.out.println(JSON.toJSON(response));
       return response.getTranscodeTaskList().get(0).getTaskStatus();
    } catch (Exception e) {
      System.out.println("ErrCode:" + e.getMessage());
      return null;
    }
  }

  public static void GetPlayURL(String videoId, DefaultAcsClient client){
    GetPlayInfoRequest request = new GetPlayInfoRequest();
    //Put your videoid for which you want to get play urls
    request.setVideoId("<videoid>");
    request.setResultType("Multiple");
    try {
      GetPlayInfoResponse response = client.getAcsResponse(request);
      System.out.println(JSON.toJSON(response));
    }catch (Exception e) {
      System.out.println("ErrCode:" + e.getMessage());
    }
  }

  public static CreateUploadVideoResponse GetUplodURLForLCient(DefaultAcsClient client, String title, String fileName) {
    //Get Upload URL for client upload
    CreateUploadVideoResponse uploadVideoDetails = GetUploadUrlForMedia(client, title, fileName);
    System.out.println("Video url successfully generated");
    System.out.println("video id: " + uploadVideoDetails.getVideoId());
    System.out.println("upload url: " + uploadVideoDetails.getUploadAddress());
    System.out.println("upload url decode: " + Base64.getUrlDecoder().decode(uploadVideoDetails.getUploadAddress()));
    System.out.println("upload auth: " + uploadVideoDetails.getUploadAuth());
    return uploadVideoDetails;
  }
  /*
   Get Upload url for a media file
    */
  private static CreateUploadVideoResponse GetUploadUrlForMedia(DefaultAcsClient client, String title, String filename){
    CreateUploadVideoRequest request = new CreateUploadVideoRequest();
    request.setTitle(title);
    request.setFileName(filename);
    try{
      return client.getAcsResponse(request);
    }
    catch (Exception e){
      System.out.println("Error Occured");
      System.out.println(e);
    }
    return null;
  }
}