欢迎使用 Jcex Easy SDK for Java 。

Jcex Esay SDK for Java让您不用复杂编程即可访支付宝开放平台开放的各项常用能力，SDK可以自动帮您满足能力调用过程中所需的证书校验、加签、验签、发送HTTP请求等非功能性要求。


环境要求
Jcex Easy SDK for Java 需要配合JDK 1.8或其以上版本。

使用 Jcex Easy SDK for Java 之前 ，您需要先前往佳成国际-开发者中心完成开发者接入的一些准备工作，包括创建应用、为应用添加功能包、设置应用的接口加签方式等。

准备工作完成后，注意保存如下信息，后续将作为使用SDK的输入。

加签模式为公钥证书模式时（推荐）
AppId、应用的私钥、证书文件、公钥证书文件、根证书文件

加签模式为公钥模式时
AppId、应用的私钥、公钥

安装依赖
通过Maven来管理项目依赖
推荐通过Maven来管理项目依赖，您只需在项目的pom.xml文件中声明如下依赖

<dependency>
   <groupId>com.jcex.open</groupId>
   <artifactId>jcex-easysdk</artifactId>
   <version>1.0.0</version>
</dependency>

快速开始
