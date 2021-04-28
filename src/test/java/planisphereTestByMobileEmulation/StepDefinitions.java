package planisphereTestByMobileEmulation;

import static org.junit.Assert.*;

import java.net.MalformedURLException;

import cucumber.api.java.ja.かつ;
import cucumber.api.java.ja.ならば;
import cucumber.api.java.ja.もし;
import cucumber.api.java.ja.前提;

public class StepDefinitions {
    private WebConnector connector = new WebConnector();

    private String reserveType;
    private String contactType;
    private int weekEnd;
    private int termValue;
    private int termValueWeekEnd;
    private String comment144 = "123456789ABC123456789ABC123456789ABC123456789ABC123456789ABC123456789ABC123456789ABC123456789ABC123456789ABC123456789ABC123456789ABC12345678";

//    public void WebSteps(WebConnector connector) {
//        this.connector = connector;
//    }

/**
 * 使用するWebドライバを指定する
 * @param browserType　Webドライバ名称
 * 　　　　　 "IE", "Edge", "FireFox", "Opera", "Chrome"
 * 　　ただし、Edge はまともに動きません
 * @throws InterruptedException
 * @throws MalformedURLException
 */
	@前提("^Webドライバは\"([^\"]*)\"を選択する$")
    public void select_webdriver(String browserType) throws InterruptedException, MalformedURLException {
//		System.out.println(browserType);
		connector.selectWebDriver(browserType);
    }

/**
 * 指定したURLを表示します
 * @param url　表示するURL
 * @throws InterruptedException
 */
    @前提("^ページ\"([^\"]*)\"を表示する$")
    public void display_url(String url) throws InterruptedException {
        connector.openAndWait(url);
    }

    @もし("Windowを最大化する$")
    public void window_maximized() throws InterruptedException {
        connector.setWindowMax();
    }

    @もし("ブラウザを英語モードにする$")
    public void englishMode() {
  	  connector.setLangEnglish();
    }

    @もし("ブラウザを日本語モードにする$")
    public void japaneseMode() {
  	  connector.setlangJapanese();
    }

    @もし("^ブラウザをMobile\"([^\"]*)\"モードで開く$")
    public void mobileMode(String mobile) {
    	int width = 0;
    	int height = 0;
    	double pixelratio = 0;

    	switch(mobile) {
    	case("Nexus 5X"):
    		width = 412;
    		height = 732;
    		pixelratio = 2.625;
    		break;
    	case("iPad Mini"):
    		width = 1024;
    		height = 768;
    		pixelratio = 2;
    		break;
    	case("iPhone X"):
    		width = 812;
    		height = 375;
    		pixelratio = 3;
    		break;
    	default:

    	}
    	connector.setMobileEmulator(mobile, width, height, pixelratio);
    }

    @ならば("画面更新$")
    public void refresh() throws InterruptedException {
    	connector.refresh();
    }

    @もし("\"([^\"]*)\"で指定できるカレンダー表示を消して")
    public void eraseCalendar(String name) throws InterruptedException {
    	connector.btnClickAndWait_X(name);
    }

    @もし("^ナビゲータボタンをクリックする$")
    public void naviButtonClick() throws InterruptedException {
    	String selector = "span.navbar-toggler-icon";

    	connector.btnClickAndWait_CSS(selector);
    }

    @もし("^HOME画面にもどる$")
    public void toHome() {
    	String href = "./index.html";

    	connector.clickHrefAndWait(href);
    }

    @もし("^ログインボタンをクリックする$")
    public void logIn() throws InterruptedException {
    	String selector = "login-button";

    	connector.btnClickAndWait_ID(selector);
    }

    @もし("^ログアウトする$")
    public void logOut() throws InterruptedException {
    	String selector = "#logout-form > button";

    	connector.btnClickAndWait_CSS(selector);
    }

    @もし("予約内容を記録して$")
    public void reserveRec() throws InterruptedException {
    	String selector1 = "username";
    	String selector2 = "contact";

    	contactType = connector.getReserveUser(selector1, selector2);

        if(contactType.equals("メールでのご連絡")) {
        	String selector3 = "email";
        	connector.email = connector.getText(selector3);
        }else if(contactType.equals("電話でのご連絡")) {
        	String selector4 = "tel";
        	connector.tel = connector.getText(selector4);
        }

    }

    @もし("シナリオを終了してブラウザを閉じる$")
    public void close() {
        connector.destroySelenium();
    }

/**　待機 */
    @ならば("^\"([^\"]*)\"秒待つ$")
    public void wait(int sec) {
        connector.sleep(sec);
    }

/**  Window切り替え **/
    @ならば("親子タブを取得する$")
    public void parentAndChild() {
    	connector.setWindow();
    }

    @ならば("^親タブに切り替える$")
    public void parent() {
    	connector.setParent();
    }

    @ならば("子タブに切り替える$")
    public void child() {
    	connector.setChild();
    }

/**
 *  クリックイベント各種
 */

/**
 * 予約内容を確認するボタン
 * @throws InterruptedException
 */
    @もし("^予約内容を確認するボタンをクリックする$")
    public void confirmReserveButton() throws InterruptedException {
    	String selector = "submit-button";

    	connector.btnClickAndWait_ID(selector);
    }

/**
 * この内容で確認するボタン
 * @throws InterruptedException
 */
    @もし("^この内容で予約するボタンをクリックする$")
    public void clickReserveButton() throws InterruptedException {
    	String selector = "//button[@type='button']";

    	connector.btnClickAndWait_X(selector);
    }

/**
 * 閉じるボタン
 * @throws InterruptedException
 */
    @もし("^閉じるボタンをクリックする$")
    public void clickCloseButton() throws InterruptedException {
    	String selector = "(//button[@type='button'])[3]";

    	connector.btnClickAndWait_X(selector);
    }

    /**
     * spanタグセレクタのクリックイベント
     * @param text spanタグで括られたテキストを指定する
     */
    @もし("ラベル\"([^\"]*)\"をクリックする$")
    public void span_click(String text) {
        connector.spanClickAndWait("span", text);
    }

    @もし("リンクテキスト\"([^\"]*)\"をクリックする$")
    public void link_click(String text) throws InterruptedException {
//		System.out.println(text);
        connector.linkClickAndWait(text);
    }

    /**
     * aタグセレクタのクリックイベント
     * @param href aタグの アンカー(href)を指定する
     */
    @もし("アンカー\"([^\"]*)\"をクリックする$")
    public void anchor_click(String href) {
        connector.clickHrefAndWait(href);
    }

    /**
     * input type="button"タグセレクタのクリックイベント
     * @param name ボタンのテキスト(value)を指定する
     */
    @もし("ボタン\"([^\"]*)\"をクリックする$")
    public void button_click(String name) {
        connector.btnClickAndWait(name);
    }

    /**
     * input type="button"タグセレクタのクリックイベント
     * @param name ボタンのテキスト(value)を指定する
     */
    @かつ("かつボタン\"([^\"]*)\"をクリックする$")
    public void and_button_click(String name) {
        connector.btnClickAndWait(name);
    }

    /**
     * 複数のinput type="submit" or "button"タグセレクタのnameの名前を持つ
     * ボタンクリックイベント
     * @param name ボタンのテキスト(value)を指定する
     * @param type ボタンのタイプ(button or submitを期待)を指定する
     */
    @もし("名前が\"([^\"]*)\"のボタン\"([^\"]*)\"をクリックする$")
    public void something_button_click(String name, String type) {
        connector.btnClickAndWait(type, name);
    }

    /**
     * 規則的に並ぶ複数のinput type="submit" or "button"タグセレクタのnameの名前を持つ
     * ボタンクリックイベント
     * @param name ボタンのテキスト(value)を指定する
     * @param type ボタンのタイプ(button or submitを期待)を指定する
     * @param index ボタンの配列の順番を指定する(1 origin)
     */
    @もし("名前が\"([^\"]*)\"の\"([^\"]*)\"番目のボタンをクリックする$")
    public void index_button_click(String value, String type, int index ) {
        connector.btnByblockClickAndWait(type, value, index-1);
    }

    @ならば("CSSセレクタ名が\"([^\"]*)\"のボタンをクリックする$")
    public void css_button_click(String commandLocater) throws InterruptedException {
    	connector.btnClickAndWait_CSS(commandLocater);
    }

    @ならば("IDセレクタ名が\"([^\"]*)\"のボタンをクリックする$")
    public void css_button_clickID(String commandLocater) throws InterruptedException {
    	connector.btnClickAndWait_ID(commandLocater);
    }

    @もし("CSSセレクタ名が\"([^\"]*)\"のボタンをクリックしてポップアップ表示を出す$")
    public void css_buttonClickAndPopUp(String commandLocater) throws InterruptedException {
    	connector.cssButtonClickAndPopUp(commandLocater);
    }


/** 入力系 */

    @もし("宿泊プランを\"([^\"]*)\"にして$")
    public void planSelect(String plan) {
    	String commandLocater;
    	switch(plan) {
    	case("お得な特典付きプラン"):
    		commandLocater = "./reserve.html?plan-id=0";
    		connector.clickHrefAndWait(commandLocater);
    		reserveType = "お得な特典付きプラン";
    		break;
    	case("プレミアムプラン"):
    		commandLocater = "./reserve.html?plan-id=1";
    		connector.clickHrefAndWait(commandLocater);
    		reserveType = "プレミアムプラン";
    		break;
    	case("ディナー付きプラン"):
    		commandLocater = "./reserve.html?plan-id=2";
    		connector.clickHrefAndWait(commandLocater);
    		reserveType = "ディナー付きプラン";
    		break;
    	case("お得なプラン"):
    		commandLocater = "./reserve.html?plan-id=3";
    		connector.clickHrefAndWait(commandLocater);
    		reserveType = "お得なプラン";
    		break;
    	case("素泊まり"):
    		commandLocater = "./reserve.html?plan-id=4";
    		connector.clickHrefAndWait(commandLocater);
    		reserveType = "素泊まり";
    		break;
    	case("出張ビジネスプラン"):
    		commandLocater = "./reserve.html?plan-id=5";
    		connector.clickHrefAndWait(commandLocater);
    		reserveType = "出張ビジネスプラン";
    		break;
    	case("エステ・マッサージプラン"):
    		commandLocater = "./reserve.html?plan-id=6";
    		connector.clickHrefAndWait(commandLocater);
    		reserveType = "エステ・マッサージプラン";
    		break;
    	case("貸し切り露天風呂プラン"):
    		commandLocater = "./reserve.html?plan-id=7";
    		connector.clickHrefAndWait(commandLocater);
    		reserveType = "貸し切り露天風呂プラン";
    		break;
    	case("カップル限定プラン"):
    		commandLocater = "./reserve.html?plan-id=8";
    		connector.clickHrefAndWait(commandLocater);
    		reserveType = "カップル限定プラン";
    		break;
    	case("テーマパーク優待プラン"):
    		commandLocater = "./reserve.html?plan-id=9";
    		connector.clickHrefAndWait(commandLocater);
    		reserveType = "テーマパーク優待プラン";
    		break;
    	default:
    		commandLocater = "./reserve.html?plan-id=0";
    		connector.clickHrefAndWait(commandLocater);
    	}
    }

    @もし("宿泊初日の曜日を\"([^\"]*)\"として$")
    public void fromDay(String startDay) throws InterruptedException {
    	String commandLocater = "date";

    	switch(startDay) {
    	case("Sunday"):
            connector.sunday(commandLocater);
            weekEnd = 0;
            connector.dateFromSet();
            break;
    	case("Monday"):
            connector.monday(commandLocater);
            weekEnd = 0;
            connector.dateFromSet();
            break;
    	case("Tuesday"):
            connector.tuesday(commandLocater);
            weekEnd = 0;
            connector.dateFromSet();
            break;
    	case("Wednesday"):
            connector.wednesday(commandLocater);
            weekEnd = 0;
            connector.dateFromSet();
            break;
    	case("Thursday"):
            connector.thursday(commandLocater);
            weekEnd = 0;
            connector.dateFromSet();
            break;
    	case("Friday"):
            connector.friday(commandLocater);
            weekEnd = 0;
            connector.dateFromSet();
            break;
    	case("Saturday"):
            connector.saturday(commandLocater);
            weekEnd = 0;
            connector.dateFromSet();
            break;
        default:
    	}

    	String commandLocater2 = "//div[@id='ui-datepicker-div']/div[2]/button";
    	connector.btnClickAndWait_X(commandLocater2);
    }

    @もし("連泊数を\"([^\"]*)\"にして$")
    public void termSetting(String termText) {
    	String commandLocater = "term";

        termValue = Integer.valueOf(termText) - weekEnd;
        termValueWeekEnd = weekEnd;
        int term = termValue + termValueWeekEnd;
        connector.inputAndWait(commandLocater, termText);
        connector.termSet(term);
    }

    @もし("宿泊人数を\"([^\"]*)\"にして$")
    public void headSetting(String headText) {
    	String commandLocater = "head-count";

    	connector.headCountValue = Integer.valueOf(headText);
    	connector.inputAndWait(commandLocater, headText);
    }

    @もし("朝食バイキング有無を\"([^\"]*)\"にして$")
    public void breakFastSetting(String breakfast) throws InterruptedException {
    	String commandLocater = "breakfast";

    	if(breakfast.equals("on")) {
    		connector.btnClickAndWait_ID(commandLocater);
    	}
    }

    @もし("昼からチェックインプランを\"([^\"]*)\"にして$")
    public void earlySetting(String earlyset) throws InterruptedException {
    	String commandLocater = "early-check-in";

    	if(earlyset.equals("on")) {
    		connector.btnClickAndWait_ID(commandLocater);
    	}
    }

    @もし("お得な観光プランを\"([^\"]*)\"にして$")
    public void sightSeeingSetting(String seeing) throws InterruptedException {
    	String commandLocater = "sightseeing";

    	if(seeing.equals("on")) {
    		connector.btnClickAndWait_ID(commandLocater);
    	}
    }

    @もし("^氏名を\"([^\"]*)\"として$")
    public void nameSetting(String name) throws InterruptedException {
    	String commandLocater = "username";

    	connector.inputAndWait(commandLocater, name);
    }

    @かつ("^連絡手段を\"([^\"]*)\"にしたら電話番号を\"([^\"]*)\"にするかメールアドレスを\"([^\"]*)\"にするかして$")
    public void contactSet(String contact, String tel, String email) throws InterruptedException {
    	String selector1 = "contact";
    	String selector2 = "tel";
    	String selector3 = "email";

    	connector.dropDownSelect(selector1, contact);
    	contactType = contact;
    	Thread.sleep(1000);
    	if(tel.length() != 0) {
    		connector.inputAndWait(selector2, tel);
    	}
    	if(email.length() != 0) {
    		connector.inputAndWait(selector3, email);
    	}
    }

    @かつ("連絡手段を\"([^\"]*)\"にして$")
    public void contactSetting(String contact) throws InterruptedException {
    	String commandLocater = "contact";

    	connector.dropDownSelect(commandLocater, contact);
    	contactType = contact;
    }

    @かつ("電話番号を\"([^\"]*)\"にして$")
    public void telSetting(String tel) {
    	String commandLocater = "tel";

    	connector.inputAndWait(commandLocater, tel);
    }

    @かつ("^メールアドレスに\"([^\"]*)\"を入力して$")
    public void mailSetting(String email) {
    	String selector = "email";

    	connector.inputAndWait(selector, email);
    }

    @かつ("^パスワードに\"([^\"]*)\"を入力して$")
    public void passSetting(String pass) {
    	String selector = "password";

    	connector.inputAndWait(selector, pass);
    }

    @もし("連絡事項を\"([^\"]*)\"にして$")
    public void comentSetting(String comment) {
    	String selector = "comment";

    	if(comment.equals("144文字")) {
    		comment = comment144;
        	connector.inputAndWait(selector, comment);
    	}
    }

    /**
     * id or nameのセレクタに文字を入力する
     * @param selector id or name セレクタ名
     * @param val 入力する値
     */
    @もし("\"([^\"]*)\"要素に\"([^\"]*)\"と入力する$")
    public void input_element(String selector, String val) {
        connector.inputAndWait(selector,val);
    }

    /**
     * 入力要素に Enter キーを入力する
     * @param selector id or name セレクタ名
     */
    @もし("\"([^\"]*)\"要素にEnterを入力する$")
    public void input_enter_element(String selector) {
        connector.inputEnterAndWait(selector);
    }

    /**
     * 入力要素に Enter キーを入力する
     * @param selector id or name セレクタ名
     */
    @かつ("かつ\"([^\"]*)\"要素にEnterを入力する$")
    public void and_enter_element(String selector) {
        connector.inputEnterAndWait(selector);
    }

    /**
     * id or nameのセレクタに文字を入力する
     * @param selector id or name セレクタ名
     * @param val 入力する値
     */
    @かつ("かつ\"([^\"]*)\"要素に\"([^\"]*)\"と入力する$")
    public void and_input_element(String selector, String val) {
        connector.inputAndWait(selector,val);
    }

    @もし("年月日要素\"([^\"]*)\"に\"([^\"]*)\"を入力する$")
    public void birthdayInput(String selector, String birthday) throws InterruptedException {
    	connector.birthdayInput(selector, birthday);
    }



/** チェックボックスのクリックイベント
 * @throws InterruptedException */
    @もし("チェックボックス\"([^\"]*)\"をクリックする$")
    public void checkBox_click(String check) throws InterruptedException {
    	connector.checkBoxClick(check);
    }

/** ドロップダウンメニュー */
    @もし("ドロップダウン\"([^\"]*)\"から\"([^\"]*)\"を選択する$")
    public void dropDown_select(String dropDown, String selText) throws InterruptedException {
    	connector.dropDownSelect(dropDown, selText);;
    }


/** スクリーンショット */
//    @ならば("ファイル名「([^\"]*)」でスクリーンショットを保存する$")
//    public void screen_shot(String filename) {
//        connector.getScreenShot(filename);
//    }

//    @もし("画面に「([^\"]*)」と表示されていなければ、ファイル名「([^\"]*)」でスクリーンショットを保存する$")
//    public void not_indicated_check(String pattern, String filename) {
//        if(!connector.isTextPresent(pattern)) {
//            connector.getScreenShot(filename);
//            connector.destroySelenium();
//        }
//    }

/**
 * 検証系
 */

/**
* 表示結果のチェック
     * @param pattern 検索するテキスト
 * @throws InterruptedException
     */
	@ならば("^氏名が\"([^\"]*)\"と表示されること$")
	public void checkName(String username) throws InterruptedException {
		String selector = "username";

		assertTrue(connector.testText(selector, username));
	}

	@ならば("^会員ランクが\"([^\"]*)\"と表示されること$")
	public void checkRank(String rank) throws InterruptedException {
		String selector = "rank";

		assertTrue(connector.testText(selector, rank));
	}

	@ならば("^住所が\"([^\"]*)\"と表示されること$")
	public void checkAddress(String address) throws InterruptedException {
		String selector = "address";

		if(address.length() != 0) {
			assertTrue(connector.testText(selector, address));
		}
	}

	@ならば("^電話番号が\"([^\"]*)\"と表示されること$")
	public void checkTel(String tel) throws InterruptedException {
		String selector = "tel";

		if(tel.length() != 0) {
			assertTrue(connector.testText(selector, tel));
		}
	}

	@ならば("^性別が\"([^\"]*)\"と表示されること$")
	public void checkGender(String gender) throws InterruptedException {
		String selector = "gender";

		if(gender.length() != 0) {
			assertTrue(connector.testText(selector, gender));
		}
	}

	@ならば("^生年月日が\"([^\"]*)\"と表示されること$")
	public void checkBirthday(String birthday) throws InterruptedException {
		String selector = "birthday";

		if(birthday.length() != 0) {
			assertTrue(connector.testText(selector, birthday));
		}
	}

	@ならば("^お知らせが\"([^\"]*)\"と表示されること$")
	public void checkNotice(String notice) throws InterruptedException {
		String selector = "notification";

		assertTrue(connector.testText(selector, notice));
	}

    @ならば("画面に\"([^\"]*)\"と表示されていること$")
    public void search_text(String pattern) {
        assertTrue(connector.isTextPresent(pattern));
    }

    @ならば("ポップアップ表示に\"([^\"]*)\"と表示される$")
    public void search_popUp(String pattern) throws InterruptedException {
    	assertTrue(connector.isPopUpPresent(pattern));
    }

    @ならば("ページタイトルが\"([^\"]*)\"である$")
    public void test_page_title(String title) {
  	  assertTrue(connector.isTitlePresent(title));
    }

    @ならば("表示\"([^\"]*)\"のプラン名が\"([^\"]*)\"については表示\"([^\"]*)\"である$")
    public void test_ContentsList(String commandLocater, String planName, String hyouji) throws InterruptedException {
    	assertTrue(connector.checkContensList(commandLocater, planName, hyouji));
    }

    @ならば("合計金額は\"([^\"]*)\"となり$")
    public void testPrice(String price) {
    	String selector = "total-bill";

    	try {
        	assertTrue(connector.testPrice(selector, Integer.valueOf(price)));

    	}catch(Exception e) {
    		System.out.println("合計料金エラー");
    	}
    }

    @ならば("部屋タイプは\"([^\"]*)\"となり$")
    public void testRoomType(String roomType) throws InterruptedException {
    	String selector = "room-info";
    	String commandLocater;

        	switch(reserveType) {
        	case("お得な特典付きプラン"):
        		commandLocater = "room";
        		selector = "/html/body/div/div[1]/div/h5";
        		connector.switchFrame(commandLocater);
        		assertTrue(connector.testTextX(selector, roomType));
        		connector.switchDefaultFrame();
        		break;
        	case("プレミアムプラン"):
        		commandLocater = "room";
        		selector = "/html/body/div/div[1]/div/h5";
        		connector.switchFrame(commandLocater);
        		assertTrue(connector.testTextX(selector, roomType));
        		connector.switchDefaultFrame();
        		break;
        	case("ディナー付きプラン"):
        		commandLocater = "room";
        		assertTrue(connector.testText(selector, roomType));
        		break;
        	case("お得なプラン"):
        		commandLocater = "room";
        		assertTrue(connector.testText(selector, roomType));
        		break;
        	case("素泊まり"):
        		commandLocater = "room";
        		selector = "/html/body/div/div[1]/div/h5";
        		connector.switchFrame(commandLocater);
        		assertTrue(connector.testTextX(selector, roomType));
        		connector.switchDefaultFrame();
        		break;
        	case("出張ビジネスプラン"):
        		commandLocater = "room";
        		selector = "/html/body/div/div[1]/div/h5";
        		connector.switchFrame(commandLocater);
        		assertTrue(connector.testTextX(selector, roomType));
        		connector.switchDefaultFrame();
        		break;
        	case("エステ・マッサージプラン"):
        		commandLocater = "room";
        		assertTrue(connector.testText(selector, roomType));
        		break;
        	case("貸し切り露天風呂プラン"):
        		commandLocater = "room";
        		assertTrue(connector.testText(selector, roomType));
        		break;
        	case("カップル限定プラン"):
        		commandLocater = "room";
        		selector = "/html/body/div/div[1]/div/h5";
        		connector.switchFrame(commandLocater);
        		assertTrue(connector.testTextX(selector, roomType));
        		connector.switchDefaultFrame();
        		break;
        	case("テーマパーク優待プラン"):
        		commandLocater = "room";
        		assertTrue(connector.testText(selector, roomType));
        		break;
        	default:
        		commandLocater = "./reserve.html?plan-id=0";
        		connector.clickHrefAndWait(commandLocater);
        	}

    }

    @ならば("宿泊期間の表示が正しく$")
    public void testReserveTerm() throws InterruptedException {
    	String selector = "term";

    	try {
        	assertTrue(connector.testTerm(selector, termValue + termValueWeekEnd));
    	}catch(Exception e) {
    		System.out.println(connector.dateFrom);
    		System.out.println(connector.dateTo);
    		connector.destroySelenium();
    	}
    }

    @ならば("宿泊人数の表示が\"([^\"]*)\"名様となり$")
    public void testHeadCount(String headcount) throws InterruptedException {
    	String selector = "head-count";

    	headcount = headcount + "名様";
    	assertTrue(connector.testText(selector, headcount));
    }

    /**
     * 追加プランの検証
     */
    @ならば("追加プランが\"([^\"]*)\"または\"([^\"]*)\"または\"([^\"]*)\"で正しく$")
    public void testOption1(String plan1, String plan2, String plan3) throws InterruptedException {
    	String planText;

    	String selector1 = "//*[@id=\"plans\"]";
    	String selector2 = "//*[@id=\"plans\"]/ul/li";
    	String selector3 = "//*[@id=\"plans\"]/ul/li[1]";
    	String selector4 = "//*[@id=\"plans\"]/ul/li[2]";
    	String selector5 = "//*[@id=\"plans\"]/ul/li[3]";

    	if((plan1.equals("off"))&&(plan2.equals("off"))&&(plan3.equals("off"))) {
    		planText = "なし";
    		assertTrue(connector.testTextX(selector1, planText));
    	}

    	if((plan1.equals("on"))&&(plan2.equals("off"))&&(plan3.equals("off"))) {
    		planText = "朝食バイキング";
    		assertTrue(connector.testTextX(selector2, planText));
    	}
    	if((plan1.equals("off"))&&(plan2.equals("on"))&&(plan3.equals("off"))) {
    		planText = "昼からチェックインプラン";
    		assertTrue(connector.testTextX(selector2, planText));
    	}
    	if((plan1.equals("off"))&&(plan2.equals("off"))&&(plan3.equals("on"))) {
    		planText = "お得な観光プラン";
    		assertTrue(connector.testTextX(selector2, planText));
    	}

    	if((plan1.equals("on"))&&(plan2.equals("on"))&&(plan3.equals("off"))) {
    		planText = "朝食バイキング";
    		assertTrue(connector.testTextX(selector3, planText));
    		planText = "昼からチェックインプラン";
    		assertTrue(connector.testTextX(selector4, planText));
    	}
    	if((plan1.equals("on"))&&(plan2.equals("off"))&&(plan3.equals("on"))) {
    		planText = "朝食バイキング";
    		assertTrue(connector.testTextX(selector3, planText));
    		planText = "お得な観光プラン";
    		assertTrue(connector.testTextX(selector4, planText));
    	}
    	if((plan1.equals("off"))&&(plan2.equals("on"))&&(plan3.equals("on"))) {
    		planText = "昼からチェックインプラン";
    		assertTrue(connector.testTextX(selector3, planText));
    		planText = "お得な観光プラン";
    		assertTrue(connector.testTextX(selector4, planText));
    	}

    	if((plan1.equals("on"))&&(plan2.equals("on"))&&(plan3.equals("on"))) {
    		planText = "朝食バイキング";
    		assertTrue(connector.testTextX(selector3, planText));
    		planText = "昼からチェックインプラン";
    		assertTrue(connector.testTextX(selector4, planText));
    		planText = "お得な観光プラン";
    		assertTrue(connector.testTextX(selector5, planText));
    	}

    }

    @ならば("氏名の表示が\"([^\"]*)\"様となり$")
    public void testUsername(String username) throws InterruptedException {
    	String selector = "username";

    	username = username + "様";
    	assertTrue(connector.testText(selector, username));
    }

    /**
     * 確認のご連絡検証
     */
    @ならば("^確認連絡の表示が\"([^\"]*)\"で\"([^\"]*)\"か\"([^\"]*)\"で正しく表示され$")
    public void testContact(String contactText, String telText, String emailText) throws InterruptedException {
    	String selector = "contact";

    	switch(contactText) {
    	case("希望しない"):
    		assertTrue(connector.testText(selector, contactText));
    		break;
    	case("電話でのご連絡"):
    		contactText = "電話" + "：" + telText;
			assertTrue(connector.testText(selector, contactText));
    		break;
    	case("メールでのご連絡"):
    		contactText = "メール" + "：" + emailText;
			assertTrue(connector.testText(selector, contactText));
    		break;
    	}
    }

    @ならば("連絡事項\"([^\"]*)\"が正しく$")
    public void testComment(String comment) throws InterruptedException {
    	String selector = "comment";

    	if(comment.equals("144文字")) {
    		comment = comment144;
    		assertTrue(connector.testText(selector, comment));
    	}
    }

    @ならば("ポップアップ表示に\"([^\"]*)\"と\"([^\"]*)\"が表示され$")
    public void testPopUp(String message1, String message2) throws InterruptedException {
    	String selector1 = "//div[@id='success-modal']/div/div/div/h5";
    	String selector2 = "//div[@id='success-modal']/div/div/div[2]/p";

    	assertTrue(connector.testTextX(selector1, message1));
    	assertTrue(connector.testTextX(selector2, message2));
    }

}
