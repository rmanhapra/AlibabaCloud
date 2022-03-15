/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.RegisterMediaRequest;
import com.aliyuncs.vod.model.v20170321.RegisterMediaResponse;
import org.json.JSONObject;

/**
 * @author rakeshramanath.m
 * @version $Id: VODExternalMediaRegistration.java, v 0.1 2022-02-14 12:55 PM rakeshramanath.m Exp $$
 */
public class VODExternalMediaRegistration {
    public static void OSSMediaRegistration(String ossURL, String fileName, DefaultAcsClient client){
        RegisterMediaRequest request = new RegisterMediaRequest();
        String registerMetadata = new JSONObject().put("FileURL",ossURL)
                .put("Title",fileName).toString();
        String array = "["+registerMetadata+"]";
        request.setRegisterMetadatas(array);
        request.setTemplateGroupId("bae6177bd276425f1b8136789e9d9d38");

        try {
            RegisterMediaResponse response = client.getAcsResponse(request);
            System.out.println(JSON.toJSON(response));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}