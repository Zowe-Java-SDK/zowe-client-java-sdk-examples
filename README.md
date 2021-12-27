# Zowe Client SDK for Java Code Examples

This project provides code examples to show the usage of Zowe Client SDK for Java library. The Zowe Java SDK is a sub-project of Zowe, focusing on modernizing mainframe experience. Zowe is a project hosted by the Open Mainframe Project, a Linux Foundation project.

The SDK lets you leverage the underlying z/OSMF REST APIs on a z/OS system to build applications that interface with the mainframe.

Functionality provided:

    GetJobs   
    IssueCommand (mvs commands)  
    IssuesTsoCommand  
    SubmitJobs  
    DownloadDataset  
    CreateDataset  
    DeleteDataset  
    WriteDataset  
    ListDataset  
    MonitorJobs  
    CopyDataset
    CancelJobs
    DeleteJobs

See the following example programs:

    src/main/java/zowe/client/sdk/examples/zosconsole   
        IssueCommand.java  
  
    src/main/java/zowe/client/sdk/examples/zosfiles    
        CopyDataset.java
        CreateDataset.java  
        DataSetInfo.java  
        DeleteDataset.java  
        DownloadDataset.java
        ListDatasets.java
        WriteDataset.java  
  
    src/main/java/zowe/client/sdk/examples/zosjobs    
        CancelJobs.java
        DeleteJobs.java
        GetJobs.java
        MonitorJobs.java
        SubmitJobs

    src/main/java/zowe/client/sdk/examples/zostos  
        IssueTsoCommand.java
  
    src/main/java/zowe/client/sdk/examples/ZosConnection.java

You need to replace all instances of "XXX" accordingly to meet your target's credentials and host information.

## Demo App

[ZosShell](https://github.com/frankgiordano/ZosShell)

## Logger

Logger packaged used for the project is log4j2.

Under 'resources' directory you will find the logger configuration file log4j2.xml.

Change <Root level="debug"> assignment accordingly for your needs.

## Documentation

https://javadoc.io/doc/org.zowe.client.java.sdk/zowe-client-java-sdk/latest/index.html

## Maven Central Publication

https://mvnrepository.com/artifact/org.zowe.client.java.sdk/zowe-client-java-sdk  
  

