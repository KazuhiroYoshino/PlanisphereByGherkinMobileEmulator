# PlanisphereByGherkinMobileEmulator
### Chrome Mobile Emulatorを用いたe2eテスト
### AndroidEmulatorで用意できないデバイスは、ChromeEmulatorでテストしたい。
### 確認したEmulatorは、
* BlackBerry S370
* Blackberry PlayBook（横向き）
* Kindle Fire HDX（横向き）
* Laptop with HiDPI screen（横向き）
* Laptop with touch（横向き）
* Microsoft Lumia 950
* Moto G4
* Nexus 10（横向き）
* Nexus 4
* Nexus 5
* Nexus 5X
* Nexus 6
* Nexus 6P
* Nexus 7（横向き）
* Nokia N9
* iPad Mini（横向き）
* Galaxy S5
* Pixel 2
* Pixel 2 XL
* iPhone 5/SE
* iPhone 6/7/8
* iPhone 6/7/8 Plus
* iPhone X
* iPad（横向き）
* iPad Pro（横向き）
https://chromium.googlesource.com/chromium/src/+/167a7f5e03f8b9bd297d2663ec35affa0edd5076/third_party/WebKit/Source/devtools/front_end/emulated_devices/module.json
の画面サイズを参照
### AppiumとAndroidEmulatorのように時々デバイスを回転させたりはできないので、向きは固定とする。
