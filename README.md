# Mshare
[![PyPi Version](https://jitpack.io/v/crazyniche1/Mshare.svg)](https://jitpack.io/#crazyniche1/Mshare)
[![License](https://img.shields.io/badge/license-Apache-blue)](LICENSE)

一键分享SDK：<br>  
   *支持文字分享 <br>  
   *支持图片分享 <br>  
   *添加分享应用过滤但功能未完善； <br>  



To get a Git project into your build:

Step 1. Add the JitPack repository to your build file Add it in your root build.gradle at the end of repositories
```java
  allprojects {
      repositories {
        ...
        maven { url 'https://jitpack.io' }
      }
    }
```
Step 2. Add the dependency
```java
    dependencies {
              implementation 'com.github.crazyniche1:Mshare:v1.0.2'
      }
```

for example：
```java
      ConcreteShareBuilder(this).create().shareText("nihao").show()
      ConcreteShareBuilder(this).create().shareImage("url").show()
```
