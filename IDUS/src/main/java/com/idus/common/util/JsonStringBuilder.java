package com.idus.common.util;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class JsonStringBuilder {
	
	private StringBuilder jsonBuilder;
	private DateTimeFormatter formatter;
	
	
	/* constructor */
	public JsonStringBuilder() {
		this.jsonBuilder = new StringBuilder();
		this.jsonBuilder.append("{");
		this.formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm");
	}
	
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	/* method */
	
	
	/** public <T> void addEntry(String key, Object object, Class<T> clazz)
	 * 
	 * 이 메소드는 DTO의 instance를 json string 형태로 serialize 하는데 사용 됩니다.
	 * 이 메소드를 호출하면 아래와 같은 형태의 엔트리가 추가 됩니다.
	 * 
	 *   - "key" : { "field1":value1, "field2":value2, ..... "fieldN":valueN }
	 * 
	 * @param key : JSON object의 key
	 * @param object : serialize 할 DTO instance
	 * @param clazz : DTO instance의 Class class instance
	 */
	public <T> void addEntry(String key, Object dto, Class<T> clazz) {
		
		Field[] fields = clazz.getDeclaredFields();
		jsonBuilder.append("\"" + key + "\":" + "{");
		
		for(Field f : fields) {
			try {
				f.setAccessible(true);
				addEntry(f.getName(), f.get(dto));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		jsonBuilder.deleteCharAt(jsonBuilder.lastIndexOf(","));
		jsonBuilder.append("},");
		
	}
	
	
	/** public void addEntry(String key, Object object)
	 * 
	 * 이 메소드는 Integer, Boolean, String, LocalDateTime, List object를 serialize하는데 사용됩니다.
	 * List object의 경우, 내부에서 다른 형태로 overload된 addEntry 메소드가 호출됩니다.
	 * 
	 * 이 메소드를 호출하면 아래와 같은 형태의 엔트리가 추가 됩니다.
	 * 
	 *   - 1) list가 아닐경우 : "key":value
	 *   - 2) list일 경우 : "key":[ {.......}, {.......}, ...... , {......} ] 
	 *   
	 * @param key : JSON object의 key
	 * @param object : Integer, Boolean, String, localDateTime,
	 * 
	 */
	public void addEntry(String key, Object object) {
		
		 if(object instanceof Integer) {
			jsonBuilder.append("\"" + key + "\":" + (Integer)object + ",");
		} else if(object instanceof Boolean) {
			jsonBuilder.append("\"" + key + "\":" + (Boolean)object + ",");
		} else if(object instanceof String) {
			jsonBuilder.append("\"" + key + "\":\"" + 
					((String)object).replaceAll("\"", "\\\\\"")
					.replaceAll("\r\n", "<br/>")
					.replaceAll("<script>", "&lt;script&gt;")
					.replaceAll("</script>", "&lt;/script&gt;") + "\",");
		} else if(object instanceof LocalDateTime) {
			jsonBuilder.append("\"" + key + "\":\"" + formatter.format((LocalDateTime)object) + "\",");
		} else if(object instanceof List<?>) {
			addEntry(key, (List<?>)object);
		}
		
	}
	
	
	/** public <T> void addEntry(String key, List<T> value)
	 * 
	 * 이 메소드는 DTO 객체가 담긴 List를 serialize하는데 사용됩니다.
	 * 이 메소드를 호출하면 아래와 같은 형태의 엔트리가 추가 됩니다.
	 * 
	 *   - 2) "key":[ {.......}, {.......}, ...... , {......} ]
	 * 
	 * @param key : Json object의 key
	 * @param value : DTO객체가 담긴 List
	 * 
	 */
	public <T> void addEntry(String key, List<T> value) {
		
		if(value.size() == 0) {
			jsonBuilder.append("\"" + key + "\":[],");
			return;
		}
		
		jsonBuilder.append("\"" + key + "\":[");
		
		if(value != null && !value.isEmpty()) {
			
			for(T obj : value) {
				
				Field[] fields = obj.getClass().getDeclaredFields();
				jsonBuilder.append("{");
				
				for(Field f : fields) {
					try {
						f.setAccessible(true);
						addEntry(f.getName(), f.get(obj));
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
				
				jsonBuilder.deleteCharAt(jsonBuilder.lastIndexOf(","));
				jsonBuilder.append("},");
				
			}
			
		}
		jsonBuilder.deleteCharAt(jsonBuilder.lastIndexOf(","));
		jsonBuilder.append("],");
	}
	
	
	/** public void addEntry(String key, JSONStringBuilder value)
	 * 
	 * 다른 JSONStringBuilder 객체의 내용을 entry로 추가합니다.
	 * 
	 * @param key
	 * @param value
	 */
	public void addEntry(String key, JsonStringBuilder value) {
		jsonBuilder.append("\"" + key + "\":" + value.toString() + ",");
	}
	
	
	/** public String toString()
	 * 
	 * JSONStringBuilder객체에 담긴 내용을 String으로 반환합니다.
	 * 이 메소드가 한번 호출 되면 해당 JsonStringbuilder 객체는 JsonString으로서의 기능을 잃게 됩니다.
	 * 즉 한번 이상 호출 할 수 없습니다.
	 * 
	 */
	@Override
	public String toString() {
		int index = jsonBuilder.lastIndexOf(",");
		if(index < 0) {
			return "{}";
		}
		jsonBuilder.deleteCharAt(index);
		jsonBuilder.append("}");
		return jsonBuilder.toString();
	}
	
	
	
	
}
