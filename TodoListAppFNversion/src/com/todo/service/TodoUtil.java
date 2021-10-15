package com.todo.service;

import java.util.*;
import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc, category, due_date;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		//카테고리, 제목, 내용, 마감일 입력 받음
		System.out.print("<항목 추가>\n"
				+ "카테고리(취소:0) -> ");
		
		category = sc.next();
		sc.nextLine();
		
		//캔슬
		if(category.equals("0")) {
			System.out.println("취소되었습니다.");
			return;
		}
		
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
		
		System.out.print("중요도(별1~5) -> ");
		int priority = sc.nextInt();
		sc.nextLine();
		
		System.out.print("예상소요시간 -> ");
		String estimated_time = sc.nextLine();
		
		TodoItem t = new TodoItem(title, desc, category, due_date, priority,estimated_time);
		if(list.addItem(t)>0)
			System.out.println("추가되었습니다.");
	}

	public static void deleteItem(TodoList l) {
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "<항목 삭제>\n"
				+ "삭제할 항목의 번호를 입력하세요(취소:0) -> ");
		String indexes = sc.nextLine().trim();
		
		//캔슬
		if(indexes.contains("0"))
			return;
		
		indexes = indexes.replace(" ", "");
		StringTokenizer st = new StringTokenizer(indexes, ",");
		int index=0;
		String notice="";
		while(st.hasMoreTokens()) {
			index = Integer.parseInt(st.nextToken());
			if(l.deleteItem(index)>0) {
				notice = notice + index + "번 ";
			} else {
				System.out.println("항목을 찾을 수가 없습니다.");
				return;
			}	
		}
		
//		System.out.println(index + ". " + target.toString());
//		System.out.print("정말로 삭제하시겠습니까? (y/n) -> ");
//		String do_delete = sc.next();
		
		System.out.println(notice + "항목이 정상적으로 삭제되었습니다.");
		
	}


	public static void updateItem(TodoList l) {
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		System.out.print("<항목 수정>\n"
				+ "수정할 항목의 번호를 입력하세요(취소: 0) -> ");
		int index = sc.nextInt();
		
		//캔슬
		if(index==0) {
			System.out.println("취소되었습니다.");
			return;
		}
		
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
		
		System.out.print("새 중요도(별1~5)? -> ");
		int new_priority = sc.nextInt();
		sc.nextLine();
		
		System.out.print("새 예상소요시간 -> ");
		String new_estimated_time = sc.nextLine();
		
		TodoItem t = new TodoItem(new_title, new_description, new_category, new_due_date, new_priority,new_estimated_time);
		t.setId(index);
		if(l.updateItem(t)>0)
			System.out.println("항목이 수정되었습니다.");
	}
	
	public static void find(String target, TodoList l) {
		int count=0;
		for(TodoItem myitem : l.findList(target)) {
			System.out.println(myitem.toString());
			count++;
		}
		System.out.println("총 " + count + "개의 항목을 찾았습니다.");
	}
	
	public static void findCategory(String target, TodoList l) {
		int count=0;
		for(TodoItem item : l.getList("category", target)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.println("총 " + count + "개의 항목을 찾았습니다.");
	}
	
	public static void listDay(TodoList l) {
		for(String item: l.getColumn("due_date")) {
			System.out.println("\n<" + item + ">");
			for(TodoItem t: l.getList("due_date", item)) {
				System.out.println(t.toString());
			}
		}
	}
	
	public static void list(TodoList l, String target) {
		int count=0;
		for(String item: l.getColumn(target)) {
			System.out.print(item + " ");
			count++;
		}
		System.out.println("\n" + "총 " + count + "개가 등록되어 있습니다.");
	}

	public static void listAll(TodoList l) {
		System.out.println("<전체 목록, 총 " + l.getCount() + "개>");
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}
	
	public static void listIsCompleted(TodoList l, int order) {
		int count=0;
		for (TodoItem item : l.getOrderedList("due_date", 1)) {
			if(item.getIs_completed()==order) {
				System.out.println(item.toString());
				count++;
			}
		}
		System.out.println("총 " + count +"개의 항목이 출력되었습니다.");
	}
	
	public static void listAll(TodoList l, String orderBy, int ordering) {
		System.out.println("<전체 목록, 총 " + l.getCount() + "개>");
		for (TodoItem item : l.getOrderedList(orderBy, ordering)) {
			System.out.println(item.toString());
		}
	}
	
	public static void listPriority(TodoList l, String orderBy, int ordering) {
		int count=0;
		for (TodoItem item : l.getOrderedList(orderBy, ordering)) {
			if(item.getIs_completed()==0) {
				System.out.println(item.toString()); 
				count++;
			}
		}
		System.out.println("총 " + count +"개의 항목이 출력되었습니다.");
	}
	
	public static void completeItem(TodoList l, String indexes) {
		indexes = indexes.replace(" ", "");
		StringTokenizer st = new StringTokenizer(indexes, ",");
		while(st.hasMoreTokens()) {
			int index=0;
			index = Integer.parseInt(st.nextToken());
			if(l.setCompleted(index)<0) { 
				System.out.println("Error 발생!");
				return;
			}
		}
		System.out.println("완료 설정되었습니다.");
	}
	
	public static void insertJsonData(TodoList list) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		System.out.print("읽어드릴 파일 이름을 입력하세요(취소:0) -> ");
		String fileName = sc.nextLine();
		if(fileName.equals("0"))
			return;
		
		ArrayList<TodoItem> l = GsonConnect.readJsonFile(fileName);
		int count=0;
		for(TodoItem t: l) {
			if(list.addItem(t)>0) {
				count++;
				continue;
			}
		}
		System.out.println("총 " + count + "개의 항목이 추가되었습니다.");
	}
	
	public static void extractJsonData(TodoList list) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		System.out.print("데이터로 추출할 항목을 선택하세요(취소:0) -> ");
		String indexes = sc.nextLine().trim();
		if(indexes.equals("0"))
			return;
		
		indexes = indexes.replace(" ", "");
		StringTokenizer st = new StringTokenizer(indexes, ",");
		ArrayList<TodoItem> l = new ArrayList<TodoItem>();
		while(st.hasMoreTokens()) {
			TodoItem t = list.getItem(Integer.parseInt(st.nextToken()));
			l.add(t);
		}
		GsonConnect.writeJsonFile(l);
	}
}
