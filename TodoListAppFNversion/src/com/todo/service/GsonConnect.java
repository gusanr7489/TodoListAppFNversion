package com.todo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.todo.dao.TodoItem;

public class GsonConnect {
	private static Gson gson = new Gson();
	
	public static void writeJsonFile(ArrayList<TodoItem> l) {
		//데이터 생성
		String jsonstr = gson.toJson(l);

		//파일 저장
		try {
			FileWriter writer = new FileWriter("todolist.txt");
			writer.write(jsonstr);
			writer.close();
			System.out.println("파일에 저장되었습니다.");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<TodoItem> readJsonFile(String fileName) {
		// JSON String 객체로 가져오기
		String jsonstr = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			jsonstr = br.readLine();
			br.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		TodoItem[] array = gson.fromJson(jsonstr, TodoItem[].class);
		List<TodoItem> list = Arrays.asList(array);
		System.out.println("파일에서 데이터를 가져왔습니다.");
		return new ArrayList<TodoItem> (list);
	}
}
