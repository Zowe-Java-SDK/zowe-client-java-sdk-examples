# Zowe Client SDK for Java Code Examples

This project provides code examples to show the usage of Zowe Client Java SDK library. The [Zowe Client Java SDK](https://github.com/zowe/zowe-client-java-sdk) is a subproject of Zowe, focusing on modernizing mainframe experience. Zowe is a project hosted by the Open Mainframe Project, a Linux Foundation project.

Zowe Java SDK lets you leverage the underlying z/OSMF REST APIs on a z/OS system to build client (Windows, Linux, macOS, etc) applications that interface with your z/OS instance.

See the following example programs:

    src/main/java/zowe/client/sdk/examples/zosconsole   
        IssueConsoleExp.java  
  
    src/main/java/zowe/client/sdk/examples/zosfiles/dsn    
        DsnCopyExp.java
        DsnCreateExp.java  
        DsnDeleteExp.java  
        DsnGetExp.java  
        DsnGetInfoExp.java
        DsnListExp.java
        DsnWriteExp.java

    src/main/java/zowe/client/sdk/examples/zosfiles/uss    
        UssCreateExp.java
        UssDeleteExp.java  
        UssGetExp.java
        UssListExo.java 
  
    src/main/java/zowe/client/sdk/examples/zosjobs    
        JobCancelExp.java
        JobDeleteExp.java
        JobGetExp.java
        JobMonitorExp.java
        JobSubmitExp.java  

    src/main/java/zowe/client/sdk/examples/zoslogs     
        ZosLogExp.java
  
    src/main/java/zowe/client/sdk/examples/zosmfinfo 
        ZosmfStatusExp.java
        ZosmfSystemsExp.java
  
    src/main/java/zowe/client/sdk/examples/zostos  
        IssueTsoExp.java

    src/main/java/zowe/client/sdk/examples/zosuss  
        IssuUssExp.java
  
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
  
