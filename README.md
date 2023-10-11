# Zowe Client SDK for Java Code Examples

This project provides code examples to show the usage of Zowe Client Java SDK library. The [Zowe Client Java SDK](https://github.com/zowe/zowe-client-java-sdk) is a subproject of Zowe, focusing on modernizing mainframe experience. Zowe is a project hosted by the Open Mainframe Project, a Linux Foundation project.

Zowe Java SDK lets you leverage the underlying z/OSMF REST APIs on a z/OS system to build client (Windows, Linux, macOS, etc) applications that interface with your z/OS instance.

See the following example programs:

    src/main/java/zowe/client/sdk/examples/zosconsole   
        IssueCommandTst.java  
  
    src/main/java/zowe/client/sdk/examples/zosfiles/dsn    
        CopyDatasetTst.java
        CreateDatasetTst.java  
        DataSetInfoTst.java  
        DeleteDatasetTst.java  
        DownloadDatasetTst.java
        ListDatasetsTst.java
        WriteDatasetTst.java  

    src/main/java/zowe/client/sdk/examples/zosfiles/uss    
        CreateUssTst.java
        DeleteUssTst.java  
        GetUssTst.java
        ListUssTst.java 
  
    src/main/java/zowe/client/sdk/examples/zosjobs    
        CancelJobsTst.java
        DeleteJobsTst.java
        GetJobsTst.java
        MonitorJobsTst.java
        SubmitJobsTst.java  

    src/main/java/zowe/client/sdk/examples/zoslogs     
        ZosGetLogTst.java
  
    src/main/java/zowe/client/sdk/examples/zosmfinfo 
        CheckStatusTst.java
        ZosmfDefinedSystemsTst.java
  
    src/main/java/zowe/client/sdk/examples/zostos  
        IssueTsoCommandTst.java

    src/main/java/zowe/client/sdk/examples/zosuss  
        USSCommandTst.java
  
    src/main/java/zowe/client/sdk/examples/TstZosConnection.java

You need to replace all instances of "xxx" accordingly within the code to meet your command processing, target's z/OSMF credentials and host information.  

TstZosConnection.java contains the z/OSMF connection information and needs to be filled for all and any code example to work.   
    
## Requirements  
  
    Compatible with all Java versions 11 and above.  
    z/OSMF installed on your backend z/OS instance.  

## Demo App

[ZosShell](https://github.com/frankgiordano/ZosShell)
  
## Documentation

https://javadoc.io/doc/org.zowe.client.java.sdk/zowe-client-java-sdk/latest/index.html

## Maven Central Publication

https://mvnrepository.com/artifact/org.zowe.client.java.sdk/zowe-client-java-sdk  
  
