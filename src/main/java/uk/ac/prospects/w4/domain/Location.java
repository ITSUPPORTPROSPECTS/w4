package uk.ac.prospects.w4.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * @author vasileiosl
 *         Date: 22/11/12
 *         Time: 12:06
 */
public class Location {

	private static Map<String, String> locationCodeMap = new HashMap<String, String>();
	private static Map<String, String[]> locationPostcodeMap = new HashMap<String, String[]>();

	static {
		locationCodeMap.put("CD", "Crown Dependencies");
		locationCodeMap.put("EM", "East Midlands");
		locationCodeMap.put("EE", "East of england");
		locationCodeMap.put("LO", "London");
		locationCodeMap.put("NEE", "North East England");
		locationCodeMap.put("NWE", "North West England");
		locationCodeMap.put("SCO", "Scotland");
		locationCodeMap.put("SEE", "South East England");
		locationCodeMap.put("SWE", "South West England");
		locationCodeMap.put("WAL", "Wales");
		locationCodeMap.put("WM", "West Midlands");
		locationCodeMap.put("YH", "Yorkshire and the Humber");

		locationPostcodeMap.put("CD", new String[]{"GY","IM","JE"});
		locationPostcodeMap.put("EM", new String[]{"CV","DE","DN","LE","LN","NG","NN","PE","S","SK"});
		locationPostcodeMap.put("EE", new String[]{"AL","CB","CM","CO","E","EN","HA","HP","IG","IP","LU","NR","PE","RM","SG","SS","WD"});
		locationPostcodeMap.put("LO", new String[]{"BR","CR","DA","E","EC","EN","HA","IG","KT","N","NW","RM","SE","SM"});
		locationPostcodeMap.put("NEE", new String[]{"DH","DL","NE","SR","TD","TS"});
		locationPostcodeMap.put("NWE", new String[]{"BB","BL","CA","CH","CW","FY","L","LA","M","OL","PR","SK","WA","WN"});
		locationPostcodeMap.put("SCO", new String[]{"AB","DD","DG","EH","FK","G","HS","IV","KA","KW","KY","ML","PA","PH","TD","ZE"});
		locationPostcodeMap.put("SEE", new String[]{"BN","BR","CR","CT","DA","GU","HP","KT","ME","MK","OX","PO","RG","RH"});
		locationPostcodeMap.put("SWE", new String[]{"BA","BH","BS","DT","EX","GL","NP","PL","SN","SP"});
		locationPostcodeMap.put("WAL", new String[]{"CF","CH","GL","HR","LD","LL","NP","SA","SY"});
		locationPostcodeMap.put("WM", new String[]{"B","CV","DY","HR","LD","NP","ST","SY","TF","WR","WS","WV"});
		locationPostcodeMap.put("YH", new String[]{"BD","DL","DN","HD","HG","HU","HX","LS","S","WF","YO"});
	}

	public static Map<String, String> getLocationCodeMap(){
		return locationCodeMap;
	}

	public static Map<String, String[]> getLocationPostcodeMap(){
		return locationPostcodeMap;
	}
}