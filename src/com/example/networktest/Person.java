package com.example.networktest;

import android.os.Parcel;
import android.os.Parcelable;
/*
 * 
 * ʹ��Intent���ݶ��� Serializable �� Parcelable���л����� *
 * FirstActivity�����´���
 * Person person=new Person();
 * person.setName("Tom");
 * person.setAge(20);
 * Intent intent =new Intent(FirstActivity.this,SecondActivity.class);
 * intent.putExtra("person_data",person);
 * startActivity(intent);
 * 
 * Serializable��ʽ����
 * Person person=(Person)getIntent().getSerializableExtra("person_data");
 *  Parcelable��ʽ ��
 * SecondActivity�л�ȡ�����ʱ�����
 * Person person=(Person)getIntent().getParcelableExtra("person_data");
 * 
 */
public class Person implements Parcelable{
	private String name;
	private int age;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}	

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(name);
		dest.writeInt(age);		
	}
	public static final Parcelable.Creator<Person> CREATOR=new Parcelable.Creator<Person>() {

		@Override
		public Person createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			Person person=new Person();
			person.name=source.readString();
			person.age=source.readInt();
			return person;
		}

		@Override
		public Person[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Person[size];
		}
		
	};
	

}
