package com.my.crawler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONArray;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.my.crawler.model.County;
import com.my.crawler.model.Prefecture;
import com.my.crawler.model.Province;

public class Crawler {

	public static void main(String[] args)
			throws FailingHttpStatusCodeException, MalformedURLException, IOException, URISyntaxException {
		String urlStr = "http://xzqh.mca.gov.cn/map";

		final WebClient webClient = new WebClient();
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		final HtmlPage htmlPage = webClient.getPage(urlStr);
		ScriptResult result = htmlPage.executeJavaScript("JSON.stringify(json);");

		List<Province> provinces = JSONArray.parseArray(result.getJavaScriptResult().toString(), Province.class);
		for (Province province : provinces) {
			List<Prefecture> prefectures = getAllCitys(province.getNameAndAbbreviation());
			province.setPrefectures(prefectures);
			for (Prefecture prefecture : prefectures) {
				List<County> counties = getCounty(province.getNameAndAbbreviation(), prefecture.getPrefectureName());
				prefecture.setCounties(counties);
			}
		}
		webClient.close();

		System.out.println("-->>\n" + provinces);
		saveInFile(provinces);

	}

	private static void saveInFile(Object object) throws URISyntaxException, FileNotFoundException, IOException {
		File file = new File(Crawler.class.getResource("/").toURI());
		file = new File(file, "provinces.txt");
		if (!file.exists()) {
			file.createNewFile();
		}
		System.out.println("-->>" + file);
		ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
		outputStream.writeObject(object);
		outputStream.close();
	}

	private static List<Prefecture> getAllCitys(String provinceName)
			throws FailingHttpStatusCodeException, IOException {
		String url = "http://xzqh.mca.gov.cn/selectJson";

		HttpClient httpClient = HttpClientBuilder.create().build();
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("shengji", provinceName));
		HttpEntity httpEntity = new UrlEncodedFormEntity(list, "utf-8");

		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(httpEntity);

		HttpResponse response = httpClient.execute(httpPost);
		HttpEntity entity = response.getEntity();
		String result = EntityUtils.toString(entity, "UTF-8");
		List<Prefecture> prefectures = JSONArray.parseArray(result, Prefecture.class);
		return prefectures;
	}

	private static List<County> getCounty(String provinceName, String cityName)
			throws ClientProtocolException, IOException {
		String url = "http://xzqh.mca.gov.cn/selectJson";

		HttpClient httpClient = HttpClientBuilder.create().build();
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("shengji", provinceName));
		list.add(new BasicNameValuePair("diji", cityName));
		HttpEntity httpEntity = new UrlEncodedFormEntity(list, "utf-8");

		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(httpEntity);

		HttpResponse response = httpClient.execute(httpPost);
		HttpEntity entity = response.getEntity();
		String result = EntityUtils.toString(entity, "UTF-8");
		List<County> counties = JSONArray.parseArray(result, County.class);
		return counties;
	}

}
