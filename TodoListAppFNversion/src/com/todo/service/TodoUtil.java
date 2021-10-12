package com.todo.service;

import java.util.*;
import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);
		//카테고리, 제목, 내용, 마감일 입력 받음
		System.out.print("<항목 추가>\n"
				+ "카테고리 -> ");
		
		category = sc.next();
		sc.nextLine();
		
		System.out.print("제목 -> ");
		title = sc.next().trim();	
		
			// 제목 중복 확인
			if (list.isDuplicate(title)) {
				System.out.println("항목 제목이 중복됩니다!");
				return;
			} 
			
		sc.nextLine();
		System.out.print("내용 -> ");
		desc = sc.nextLine().trim();
		
		System.out.print("마감일 -> ");
		due_date = sc.next().trim();
		
		TodoItem t = new TodoItem(title, desc, category, due_date);
		if(list.addItem(t)>0)
			System.out.println("추가되었습니다.");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "<항목 삭제>\n"
				+ "삭제할 항목의 번호를 입력하세요 -> ");
		int index = sc.nextInt();
		
/*		if(target==null) {
			System.out.println("항목을 찾을 수가 없습니다.");
			return;
		}*/
		
//		System.out.println(index + ". " + target.toString());
//		System.out.print("정말로 삭제하시겠습니까? (y/n) -> ");
//		String do_delete = sc.next();
		
		if(l.deleteItem(index)>0) {
			System.out.println("삭제되었습니다.");
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("<항목 수정>\n"
				+ "수정할 항목의 번호를 입력하세요 -> ");
		int index = sc.nextInt();
		
		System.out.print("새 카테고리? -> ");
		String new_category = sc.next().trim();
		
		System.out.print("새 제목? -> ");
		String new_title = sc.next().trim();
		
		if (l.isDuplicate(new_title, index)) {
			System.out.println("항목 제목이 중복됩니다!");
			return;
		}
		sc.nextLine();
		
		System.out.print("새 내용? -> ");
		String new_description = sc.nextLine().trim();
		
		System.out.print("새 마감일? -> ");
		String new_due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(new_title, new_description, new_category, new_due_date);
		t.setId(index);
		if(l.updateItem(t)>0)
			System.out.println("항목이 수정되었습니다.");
	}
	
	public static void find(String target, TodoList l) {
		int count=0;
		for(TodoItem myitem : l.getList(target)) {
			System.out.println(myitem.toString());
			count++;
		}
		System.out.println("총 " + count + "개의 항목을 찾았습니다.");
	}
	
	public static void findCategory(String target, TodoList l) {
		int count=0;
		for(TodoItem item : l.getListCategory(target)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.println("총 " + count + "개의 항목을 찾았습니다.");
	}
	
	public static void listCategory(TodoList l) {
		int count=0;
		for(String item: l.getCategories()) {
			System.out.print(item + " ");
			count++;
		}
		System.out.println("\n" + "총 " + count + "개의 카테고리가 등록되어 있습니다.");
	}

	public static void listAll(TodoList l) {
		System.out.println("<전체 목록, 총 " + l.getCount() + "개>");
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}
	
	public static void listAll(TodoList l, int order) {
		int count=0;
		for (TodoItem item : l.getList()) {
			if(item.getIs_completed()==order) {
				System.out.println(item.toString());
				count++;
			}
		}
		System.out.println("총 " + count +"개의 항목이 완료되었습니다.");
	}
	
	public static void listAll(TodoList l, String orderBy, int ordering) {
		System.out.println("<전체 목록, 총 " + l.getCount() + "개>");
		for (TodoItem item : l.getOrderedList(orderBy, ordering)) {
			System.out.println(item.toString());
		}
	}
	
	public static void completeItem(TodoList l, int index) {
		if(l.setCompleted(index)>0) 
			System.out.println("완료 설정되었습니다.");
	}
	
}
