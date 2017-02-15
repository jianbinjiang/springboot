package httpclient;

public class Test {
	public static void main(String[] args) {
		String s = HttpClientService.doPost("http://localhost:9999/httpclient", "{'name':'12','a':'12'}");
		System.out.println(s);
	}

}
