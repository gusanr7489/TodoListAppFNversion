package com.todo.service;

import java.util.*;
import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc, category, due_date;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		//ī�װ�, ����, ����, ������ �Է� ����
		System.out.print("<�׸� �߰�>\n"
				+ "ī�װ�(���:0) -> ");
		
		category = sc.next();
		sc.nextLine();
		
		//ĵ��
		if(category.equals("0")) {
			System.out.println("��ҵǾ����ϴ�.");
			return;
		}
		
		System.out.print("���� -> ");
		title = sc.next().trim();	
		
			// ���� �ߺ� Ȯ��
			if (list.isDuplicate(title)) {
				System.out.println("�׸� ������ �ߺ��˴ϴ�!");
				return;
			} 
			
		sc.nextLine();
		System.out.print("���� -> ");
		desc = sc.nextLine().trim();
		
		System.out.print("������ -> ");
		due_date = sc.next().trim();
		
		System.out.print("�߿䵵(��1~5) -> ");
		int priority = sc.nextInt();
		sc.nextLine();
		
		System.out.print("����ҿ�ð� -> ");
		String estimated_time = sc.nextLine();
		
		TodoItem t = new TodoItem(title, desc, category, due_date, priority,estimated_time);
		if(list.addItem(t)>0)
			System.out.println("�߰��Ǿ����ϴ�.");
	}

	public static void deleteItem(TodoList l) {
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "<�׸� ����>\n"
				+ "������ �׸��� ��ȣ�� �Է��ϼ���(���:0) -> ");
		String indexes = sc.nextLine().trim();
		
		//ĵ��
		if(indexes.contains("0"))
			return;
		
		indexes = indexes.replace(" ", "");
		StringTokenizer st = new StringTokenizer(indexes, ",");
		int index=0;
		String notice="";
		while(st.hasMoreTokens()) {
			index = Integer.parseInt(st.nextToken());
			if(l.deleteItem(index)>0) {
				notice = notice + index + "�� ";
			} else {
				System.out.println("�׸��� ã�� ���� �����ϴ�.");
				return;
			}	
		}
		
//		System.out.println(index + ". " + target.toString());
//		System.out.print("������ �����Ͻðڽ��ϱ�? (y/n) -> ");
//		String do_delete = sc.next();
		
		System.out.println(notice + "�׸��� ���������� �����Ǿ����ϴ�.");
		
	}


	public static void updateItem(TodoList l) {
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		System.out.print("<�׸� ����>\n"
				+ "������ �׸��� ��ȣ�� �Է��ϼ���(���: 0) -> ");
		int index = sc.nextInt();
		
		//ĵ��
		if(index==0) {
			System.out.println("��ҵǾ����ϴ�.");
			return;
		}
		
		System.out.print("�� ī�װ�? -> ");
		String new_category = sc.next().trim();
		
		System.out.print("�� ����? -> ");
		String new_title = sc.next().trim();
		
		if (l.isDuplicate(new_title, index)) {
			System.out.println("�׸� ������ �ߺ��˴ϴ�!");
			return;
		}
		sc.nextLine();
		
		System.out.print("�� ����? -> ");
		String new_description = sc.nextLine().trim();
		
		System.out.print("�� ������? -> ");
		String new_due_date = sc.nextLine().trim();
		
		System.out.print("�� �߿䵵(��1~5)? -> ");
		int new_priority = sc.nextInt();
		sc.nextLine();
		
		System.out.print("�� ����ҿ�ð� -> ");
		String new_estimated_time = sc.nextLine();
		
		TodoItem t = new TodoItem(new_title, new_description, new_category, new_due_date, new_priority,new_estimated_time);
		t.setId(index);
		if(l.updateItem(t)>0)
			System.out.println("�׸��� �����Ǿ����ϴ�.");
	}
	
	public static void find(String target, TodoList l) {
		int count=0;
		for(TodoItem myitem : l.findList(target)) {
			System.out.println(myitem.toString());
			count++;
		}
		System.out.println("�� " + count + "���� �׸��� ã�ҽ��ϴ�.");
	}
	
	public static void findCategory(String target, TodoList l) {
		int count=0;
		for(TodoItem item : l.getList("category", target)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.println("�� " + count + "���� �׸��� ã�ҽ��ϴ�.");
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
		System.out.println("\n" + "�� " + count + "���� ��ϵǾ� �ֽ��ϴ�.");
	}

	public static void listAll(TodoList l) {
		System.out.println("<��ü ���, �� " + l.getCount() + "��>");
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
		System.out.println("�� " + count +"���� �׸��� ��µǾ����ϴ�.");
	}
	
	public static void listAll(TodoList l, String orderBy, int ordering) {
		System.out.println("<��ü ���, �� " + l.getCount() + "��>");
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
		System.out.println("�� " + count +"���� �׸��� ��µǾ����ϴ�.");
	}
	
	public static void completeItem(TodoList l, String indexes) {
		indexes = indexes.replace(" ", "");
		StringTokenizer st = new StringTokenizer(indexes, ",");
		while(st.hasMoreTokens()) {
			int index=0;
			index = Integer.parseInt(st.nextToken());
			if(l.setCompleted(index)<0) { 
				System.out.println("Error �߻�!");
				return;
			}
		}
		System.out.println("�Ϸ� �����Ǿ����ϴ�.");
	}
	
	public static void insertJsonData(TodoList list) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		System.out.print("�о�帱 ���� �̸��� �Է��ϼ���(���:0) -> ");
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
		System.out.println("�� " + count + "���� �׸��� �߰��Ǿ����ϴ�.");
	}
	
	public static void extractJsonData(TodoList list) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		System.out.print("�����ͷ� ������ �׸��� �����ϼ���(���:0) -> ");
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
