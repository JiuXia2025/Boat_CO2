<font size=7><center>Boat Co2</center></font>
 <center>一個可在安卓設備上啟動Minecraft JE的啟動器</center>

 這是一個可以啟動Minecraft JE的啟動器，支持運行lwjgl2的Minecraft版本
 與[Boat Launcher](https://www.coolapk.com/game/com.jiuxia.boat.launcher)、[Boat Launcher Pro](https://www.coolapk.com/game/com.jiuxia.boat.launcher.pro)聯動，並支持Mods、遊戲版本管理

 本項目使用GPL-V3開源協議，使用時務必遵守其開源協議！  ！  ！

 語言： [English](https://github.com/JiuXia2025/Boat_CO2/blob/master/README.md)|[简体中文](https://github.com/JiuXia2025/Boat_CO2/blob/master/README-zh_CN.md)|[繁體中文](https://github.com/JiuXia2025/Boat_CO2/blob/master/README-zh_TW.md)
 
 # 捐贈

 用愛發電是走不遠的。 開發不易，如果您有能力可以捐贈一下本項目，我會將這些費用用於我的此類項目後續開發服務器支出費用。
 贊助鏈接：[鏈接](http://afdian.net/@JiuXia2025)

 # 依賴

 - Boat app project
 - GL4ES
 - OpenAL-soft
 - LWJGL 2 port for Boat
 - JNDCrash
 - Gson

 # 致謝

 向所有同類項目致敬
 - [Cosinemath/BoatApp](https://github.com/Cosinemath/BoatApp)
 - [NaCln4c1/Boat_H2O-v4](https://github.com/NaCln4c1/Boat_H2O-v4)
 - [AOF-Dev/MCinaBox](https://github.com/AOF-Dev/MCinaBox)
 - [zhuowei/Boardwalk](https://github.com/zhuowei/Boardwalk)
 - [PojavLauncherTeam/PojavLauncher](https://github.com/PojavLauncherTeam/PojavLauncher)

 感謝AOF軟件開發團隊為BoatApp開發所做的努力

 # 許可
 The GPL-V3 License

 # 如何使用？
 它的使用方法與BoatApp項目一樣，您也可以按照以下方法安裝
 ## 第一步：安裝遊戲包與運行環境
 ### 1.安裝遊戲包
 將含有Minecraft遊戲版本的遊戲包解壓至/sdcard/boat/文件夾中（這些遊戲包可在其他遊戲啟動器下載版本後壓縮製作也可以在互聯網上找到）
 ### 2.安裝運行庫
 將app_runtime文件夾複製到/data/data/cosine.boat/文件夾中。 它們包含busybox、openjdk等運行環境（這些文件同樣可在互聯網上找到，本項目不包含這些內容您必須自行安裝）
 ## 第二步：修改配置文件
 使用編輯器打開/sdcard/boat/config.txt，修改裡面的java運行內存、遊戲id、uuid、token等內容
 ## 第三步：啟動遊戲
 當您已準備好運行環境與遊戲文件時，您可以進入程序點擊“Play”按鈕進行遊戲。 加載遊戲可能需要一段時間，如遇到閃退請嘗試修改運行內存檢查文件是否完整
