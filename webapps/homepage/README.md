このプロジェクトはspring bootでwebAppを実装するgradleプロジェクトです。

# webサービスを起動する

webサービスを起動するには、以下の手順を実行してください。

* このパス上でシェルからgradle buildを実行する
* ファイルの所有権を調整する
```
$ chmod u+x ./build/libs/homepage.jar
$ chmod o+r ./homepage.conf
```
* ./homepage.confのJAVA_HOMEを設定する
* ./homepage.confと./build/libs/homepage.jarとを任意のディレクトリに移動させる
* /etc/init.dに移動させたjarファイルへのシンボリックリンクを生成する
* serviceとして起動する

```
$ sudo ln - s {任意ディレクトリ}/homepage.jar /etc/init.d/homepage
$ sudo service homepage start
```

参考
https://docs.spring.io/spring-boot/docs/2.0.0.M7/reference/htmlsingle/#deployment-initd-service-securing
https://qiita.com/AkihiroTakamura/items/e1be1a70428c57b10f8b
http://progmemo.wp.xdomain.jp/archives/954
