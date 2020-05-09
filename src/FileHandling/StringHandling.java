package FileHandling;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHandling {

	@SuppressWarnings("unused")
	public static String strFind(String full, String find)
	{
		String part ="";
		String str = full;
		Pattern pattern = Pattern.compile(find, Pattern.DOTALL);
		Matcher matcher = pattern.matcher(str);
		while (matcher.find())
		{
			System.out.println(matcher.group());
			part += matcher.group();

		}
		return part;
		
	}
	
	public static String getDetail (String str, String strDelimeter)
	{
		strDelimeter="\" : , ";
		StringTokenizer strToken = new StringTokenizer (str,strDelimeter) ;
		String t1 = strToken.nextToken();
		System.out.println(t1 +": this is from "+str);
		return t1;
	}
}
