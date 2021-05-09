#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
#language: ja
フィーチャ: デバイス回転機能
	シナリオ: ブラウザ起動
				もし ブラウザをMobile"Nexus 5X"モードで開く
        前提 Webドライバは"Chrome"を選択する
#Hotel Planisphere TOPページを開く
        前提 ページ"https://hotel.testplanisphere.dev/ja/index.html"を表示する
        ならば "2"秒待つ
  シナリオ: デバイス横向き
  			もし デバイスを"横向き"にする
        ならば "5"秒待つ
  シナリオ: デバイス縦向き
  			もし デバイスを"縦向き"にする
        ならば "5"秒待つ
  シナリオ: テスト終了
        もし シナリオを終了してブラウザを閉じる
