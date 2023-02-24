package Software1;
import java.io.*;
import java.util.*;


public class Edgar{
	
		public static void main(String[] args) {
			StringBuilder builder = new StringBuilder();
			try {
				BufferedReader buffer= new BufferedReader( new FileReader("Poe.html"));
				String str;
				while((str=buffer.readLine())!= null){
					builder.append(str).append("\n");
				}
			String poem = builder.toString();
			
			int startindex=poem.indexOf("<h1>");
			int endindex=poem.indexOf("</div><!--end chapter-->");
			poem=poem.substring(startindex, endindex);
			while(poem.contains("<")) {
				//Find the first open bracket
				int fob = poem.indexOf("<");
				//Find the first closed bracket
				int fcb = poem.indexOf(">");
			   //If < index is 0
				if(fob==0)
					//poem= poem after first closed bracket
				{
					poem=poem.substring(fcb+1);
					
				}
				//else 
					//poem= poem (zero,first open) + " " + poem(first close +1)
				else {
					poem=poem.substring(0,fob)+ " " + poem.substring(fcb+1);
				}
			}
			poem=poem.replaceAll("[^a-zA-Z ]", " Pr").toLowerCase();
			String[] words =poem.split("\\s+");
			SortedMap<String,Integer> wordCount=new TreeMap <String, Integer>();
			for(String s:words) {
					wordCount.put(s, wordCount.getOrDefault(s, 0)+ 1);

			}
			Map<String, Integer> sortedMap= sortWords(wordCount);
			for(Map.Entry<String,Integer> entry: sortedMap.entrySet()) {
				System.out.println(entry.getKey() + " " + entry.getValue().toString());
			}
			} catch (Exception e) {
				
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		private static Map<String, Integer> sortWords (Map<String, Integer> map) {
			TreeMap<String, Integer> treeMap= new TreeMap<String, Integer>((a,b)->{
				if(map.get(a)!=map.get(b))
					return -Integer.compare(map.get(a),map.get(b));
				return a.compareTo(b);
			});
			treeMap.putAll(map);
			return treeMap;
		}
	}