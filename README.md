**About:**
-----------
This source code gives different code snippets on different ways to 
upload media files to Alibaba Cloud - ApsaraVideo VOD product.

Users are expected to have a registered Alibaba Cloud account and 
ApsaraVideo VOD activated. For quick start with usage of VOD product
you can refere here.

https://www.alibabacloud.com/help/en/doc-detail/295417.html


**SDK**
-------
ApsaraVideo provides SDK to upload vidoes to VOD. The choice of using
SDK is based on your programing language preference and 
flexibility you need.

**ApsaraVideo VOD upload SDK:**
Use this SDK to upload videos directly to VOD from application server.
This SDK is available only in a few programing languages.
https://www.alibabacloud.com/help/en/doc-detail/55399.html

**ApsaraVideo VOD SDK**
This SDK encapsulates the different operations that you can do
on Apsara VOD. ex: Getupload url for uploading media files, trigger transcoding, 
Get playback urls after transcoding, use wbhooks for getting notifications etc
This SDK is available in many programing languages
https://www.alibabacloud.com/help/en/doc-detail/101789.html

**ApsaraVideo Player SDK**
Apsara Video upload web SDK helps to play the medias uploaded in VOD console.
Its available in Web, Android, ios, flutter and Windows
https://www.alibabacloud.com/help/en/doc-detail/125579.html

**Code Snippets**
----------------
VODAccess.java is the main class. Snippets for following use case are available.
1) Directly upload videos to VOD server using Upload sdk
2) Use VOD SDK and OSS client SDK to get upload credentials and 
then upload to OSS folder created by VOD in OSS respectively
3) Use VOD SDK to get the upload credentials and use js sdk to upload
medias from web page
4) Use VOD sdk to upload medias from source url to destination url in VOD
5) Use VOD sdk to register existing medias available in OSS (whihch were uploaded
directly to OSS before independently)












