package survey;

import java.util.Collections;
import java.util.List;

public class Question {
	private String title;	//질문제목
	private List<String> options;	//답변옵션
	
	public Question(String title) {
		this(title, Collections.<String>emptyList());
	}
	
	public Question(String title, List<String> options) {
		this.title = title;
		this.options = options;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}
	
	public boolean isChoice() {
		//options리스트가 null이 아니고 빈값이 아니면 true반환
		return options != null && !options.isEmpty();
	}
	
}
