package model;

public abstract class MultipleItem implements Comparable<MultipleItem> {

	public abstract	long getId();
	public abstract String getDescription();
	public abstract MultipleItem create(String name);
}
