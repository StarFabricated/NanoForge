# Nano Framework of Reverse Game Engineering
Now can launch game in IDEA

We have `Tweak`, `Mixin`, And `EventBus`, but no `ModLoader`.\
technically this project is equal to `(Neo)Forge` without `FML` and `AccessTransformer`, i guess.

You can make what's missing yourself :)

NEW: Now We Have `MixinLoader` ! put modid.mixins.json into your jar to use it.

ps: we only support 0.98a
### how to use
copy game jar to `lib/gameJar`\
copy `graphics` `data` `sounds`  dir to `assets`

run  `runVanilla` task\
or `runLanchWrapper` task
### ZH_CN
致敬传奇加载器FORGE，NanoForge现已抵达远行星号😁\
其实这项目本来应该是某种MCP，但是我没太多精力去折腾映射，所以目前纯玩具。\
谁要是闲着没事可以帮我写一下，欢迎PR。

使用LanchWrapper启动、有TweakClass、有Mixin、有事件系统，显然，这就是(Neo)Forge😋

现已支持加载纯Mixin，这玩意改一改再加点SPI啥的就是ModLoader了。