<font size=7><center>Boat Co2</center></font>
<center>一个可在安卓设备上启动Minecraft JE的启动器</center>

这是一个可以启动Minecraft JE的启动器，支持运行lwjgl2的Minecraft版本
与[Boat Launcher](https://www.coolapk.com/game/com.jiuxia.boat.launcher)、[Boat Launcher Pro](https://www.coolapk.com/game/com.jiuxia.boat.launcher.pro)联动，并支持Mods、游戏版本管理

本项目使用GPL-V3开源协议，使用时务必遵守其开源协议！！！

语言：[English](https://github.com/JiuXia2025/Boat_CO2/blob/master/README.md)|[简体中文](https://github.com/JiuXia2025/Boat_CO2/blob/master/README-zh_CN.md)|[繁體中文](https://github.com/JiuXia2025/Boat_CO2/blob/master/README-zh_TW.md)

# 捐赠

用爱发电是走不远的。开发不易，如果您有能力可以捐赠一下本项目，我会将这些费用用于我的此类项目后续开发服务器支出费用。
赞助链接：[链接](http://afdian.net/@JiuXia2025)

# 依赖

- Boat app project
- GL4ES
- OpenAL-soft
- LWJGL 2 port for Boat
- JNDCrash
- Gson

# 致谢

向所有同类项目致敬
- [Cosinemath/BoatApp](https://github.com/Cosinemath/BoatApp)
- [NaCln4c1/Boat_H2O-v4](https://github.com/NaCln4c1/Boat_H2O-v4)
- [AOF-Dev/MCinaBox](https://github.com/AOF-Dev/MCinaBox)
- [zhuowei/Boardwalk](https://github.com/zhuowei/Boardwalk)
- [PojavLauncherTeam/PojavLauncher](https://github.com/PojavLauncherTeam/PojavLauncher)

感谢AOF软件开发团队为BoatApp开发所做的努力

# 许可
The GPL-V3 License

# 如何使用？
它的使用方法与BoatApp项目一样，您也可以按照以下方法安装
## 第一步：安装游戏包与运行环境
### 1.安装游戏包
将含有Minecraft游戏版本的游戏包解压至/sdcard/boat/文件夹中（这些游戏包可在其他游戏启动器下载版本后压缩制作也可以在互联网上找到）
### 2.安装运行库
将app_runtime文件夹复制到/data/data/cosine.boat/文件夹中。它们包含busybox、openjdk等运行环境（这些文件同样可在互联网上找到，本项目不包含这些内容您必须自行安装）
## 第二步：修改配置文件
使用编辑器打开/sdcard/boat/config.txt，修改里面的java运行内存、游戏id、uuid、token等内容
## 第三步：启动游戏
当您已准备好运行环境与游戏文件时，您可以进入程序点击“Play”按钮进行游戏。加载游戏可能需要一段时间，如遇到闪退请尝试修改运行内存检查文件是否完整
