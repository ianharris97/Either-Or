
public class Student {

	private String name;
	private int[] choices = new int[50];
	int index = 0;
	
	//empty constructor
	public Student() {
		name = "";
		choices = null;
	}
	
	//non-empty constructor
	public Student(String Name, int[] Choices) {
		name = Name;
		choices = Choices;
	}
	
	//setters
	public void setName(String Name) {
		name = Name;
	}
	
	public void setChoices(int[] Choices) {
		choices = Choices;
	}
	
	//getters
	public String getName() {
		return name;
	}
	
	public int getChoices(int i) {
		return choices[i];
	}
	
}
