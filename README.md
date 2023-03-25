# Zowe Client SDK for Java Code Examples

This project provides code examples to show the usage of Zowe Client Java SDK library. The [Zowe Client Java SDK](https://github.com/zowe/zowe-client-java-sdk) is a subproject of Zowe, focusing on modernizing mainframe experience. Zowe is a project hosted by the Open Mainframe Project, a Linux Foundation project.

Zowe Java SDK lets you leverage the underlying z/OSMF REST APIs on a z/OS system to build client (Windows, Linux, macOS, etc) applications that interface with your z/OS instance.

Class names providing prebuilt API services:

    CancelJobs
    CheckStatus (zosmf info)
    CreateDataset  
    CopyDataset
    DeleteDataset 
    DeleteJobs
    DownloadDataset  
    GetJobs
    GetZosLog (operlog or syslog)
    IssueCommand (mvs commands)  
    IssuesTsoCommand  
    ListDataset  
    ListDefinedSystems (zosmf info)
    MonitorJobs  
    Shell (uss commands)
    SubmitJobs  
    TeamConfig (OS Credential store and Zowe Global Team Configuration info)  
    WriteDataset

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

    src/main/java/zowe/client/sdk/examples/zoslogs     
        ZosGetLog.java
  
    src/main/java/zowe/client/sdk/examples/zosmfinfo 
        CheckStatus.java
        ZosmfDefinedSystems.java
  
    src/main/java/zowe/client/sdk/examples/zostos  
        IssueTsoCommand.java

    src/main/java/zowe/client/sdk/examples/zosuss  
        USSCommand.java
  
    src/main/java/zowe/client/sdk/examples/ZosConnection.java

You need to replace all instances of "xxx" accordingly within the code to meet your command processing, target's z/OSMF credentials and host information.
  
## Requirements  
  
    Compatible with all Java versions 11 and above.  
    z/OSMF installed on your backend z/OS instance.  

## Demo App

[ZosShell](https://github.com/frankgiordano/ZosShell)
  
## Documentation

https://javadoc.io/doc/org.zowe.client.java.sdk/zowe-client-java-sdk/latest/index.html

## Maven Central Publication

https://mvnrepository.com/artifact/org.zowe.client.java.sdk/zowe-client-java-sdk  
  