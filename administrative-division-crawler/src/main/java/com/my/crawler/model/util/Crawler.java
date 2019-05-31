package com.my.crawler.model.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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

	private List<Province> provinces;

	public Crawler() throws FailingHttpStatusCodeException, IOException {
		scanProvinces();
		scanPrefecturesAndCounties();
	}

	public List<Province> getProvinces() {
		return provinces;
	}

	private void scanProvinces() throws FailingHttpStatusCodeException, IOException {
		String urlStr = "http://xzqh.mca.gov.cn/map";

		try (WebClient webClient = new WebClient();) {
			webClient.getOptions().setCssEnabled(false);
			webClient.getOptions().setThrowExceptionOnScriptError(false);
			HtmlPage htmlPage = webClient.getPage(urlStr);
			ScriptResult result = htmlPage.executeJavaScript("JSON.stringify(json);");
			this.provinces = JSONArray.parseArray(result.getJavaScriptResult().toString(), Province.class);
		} catch (FailingHttpStatusCodeException | IOException e) {
			e.printStackTrace();
			throw e;
		}
	}

	private void scanPrefecturesAndCounties() throws ClientProtocolException, IOException {
		String url = "http://xzqh.mca.gov.cn/selectJson";

		HttpClient httpClient = HttpClientBuilder.create().build();
		for (Province province : provinces) {
			List<Prefecture> prefectures = scanPrefectures(httpClient, url, province.getNameAndAbbreviation());
			province.setPrefectures(prefectures);
			for (Prefecture prefecture : prefectures) {
				List<County> counties = scanCounties(httpClient, url, province.getNameAndAbbreviation(),
						prefecture.getPrefectureName());
				prefecture.setCounties(counties);
			}
		}
	}

	private List<Prefecture> scanPrefectures(HttpClient httpClient, String url, String nameAndAbbreviation)
			throws ClientProtocolException, IOException {
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("shengji", nameAndAbbreviation));
		HttpEntity httpEntity = new UrlEncodedFormEntity(list, "utf-8");

		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(httpEntity);

		HttpResponse response = httpClient.execute(httpPost);
		HttpEntity entity = response.getEntity();
		String result = EntityUtils.toString(entity, "UTF-8");
		List<Prefecture> prefectures = JSONArray.parseArray(result, Prefecture.class);

		return prefectures;
	}

	private List<County> scanCounties(HttpClient httpClient, String url, String nameAndAbbreviation,
			String prefectureName) throws ClientProtocolException, IOException {
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("shengji", nameAndAbbreviation));
		list.add(new BasicNameValuePair("diji", prefectureName));
		HttpEntity httpEntity = new UrlEncodedFormEntity(list, "utf-8");

		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(httpEntity);

		HttpResponse response = httpClient.execute(httpPost);
		HttpEntity entity = response.getEntity();
		String result = EntityUtils.toString(entity, "UTF-8");
		List<County> counties = JSONArray.parseArray(result, County.class);
		return counties;
	}

	public void saveInFile(File file) throws IOException {
		if (!file.exists()) {
			file.createNewFile();
		}
		System.out.println("-->>" + file);
		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));) {
			outputStream.writeObject(this.getProvinces());
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public static void main(String[] args) {
		File savedFile = new File("C:\\Users\\guo\\Desktop\\temp",Crawler.class.getCanonicalName());
		try {
			Crawler crawler = new Crawler();
			crawler.saveInFile(savedFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
